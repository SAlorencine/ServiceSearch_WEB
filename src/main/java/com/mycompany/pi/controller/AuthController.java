package com.mycompany.pi.controller;

import com.mycompany.pi.Profissional;
import com.mycompany.pi.Solicitation;
import com.mycompany.pi.Usuarios;
import com.mycompany.pi.repository.ProfissionalRepository;
import com.mycompany.pi.service.UsuarioService;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private com.mycompany.pi.repository.AdressProfissionalsRepository endProfRepo;
    @Autowired
    private com.mycompany.pi.repository.ProfessionalCelphonesRepository celProfRepo;
    @Autowired
    private com.mycompany.pi.repository.ProfissionalPhoneRepository telProfRepo;
    
    @Autowired
    private ProfissionalRepository profissionalRepo;
    
    @Autowired
    private com.mycompany.pi.repository.ClientRepository clientRepo;
    
    @Autowired
    private com.mycompany.pi.repository.SolicitationRepository solicitationRepo;
    @Autowired
    private com.mycompany.pi.repository.AdressClientRepository enderecoCliRepo;
    @Autowired
    private com.mycompany.pi.repository.ClientCelphoneRepository celularCliRepo;
    

    
    @GetMapping("/perfil-profissional")
    public String verPerfilProfissional(@RequestParam int id, Model model) {
        Profissional prof = profissionalRepo.findById(id).orElse(null);
        if (prof == null) return "redirect:/dashboard_cliente.html";


        com.mycompany.pi.AddressProfissionals endereco = null;
        if (prof.getEndereco() != null) {
            endereco = prof.getEndereco();
        }

        com.mycompany.pi.CellphoneProfissional celular = null;
        if (prof.getCelular()!= null) {
            celular = prof.getCelular();
        }

        com.mycompany.pi.PhoneProfissionals telefone = null;
        if (prof.getTelefone() != null) {
            telefone = prof.getTelefone();
        }

        model.addAttribute("prof", prof);
        model.addAttribute("usuario", prof.getUsuario());
        model.addAttribute("endereco", endereco);
        model.addAttribute("celular", celular);
        model.addAttribute("telefone", telefone);

        return "ver_perfil_profissional";
    }
    
    @GetMapping("/ver-pedido")
public String verDetalhesPedido(@RequestParam int id, Model model) {
    Solicitation sol = solicitationRepo.findById(id).orElse(null);
    if (sol == null) return "redirect:/dashboard_profissional.html";

    com.mycompany.pi.Client cli = sol.getClient();
    
    com.mycompany.pi.AddressClient end = null;
    if (cli.getEndereco() != null) {
        end = cli.getEndereco();
    }
    
    com.mycompany.pi.CelphoneClients cel = null;
    if (cli.getCelular() != null) {
        cel = cli.getCelular();
    }
    

    model.addAttribute("pedido", sol);
    model.addAttribute("cliente", cli);
    model.addAttribute("endereco", end);
    model.addAttribute("celular", cel);
    
    return "detalhes_pedido";
}

    @PostMapping("/atualizar-pedido")
public String atualizarPedido(@RequestParam int idPedido, @RequestParam String acao) {
    Solicitation sol = solicitationRepo.findById(idPedido).orElse(null);
    
    if (sol != null) {
        if ("aceitar".equals(acao)) {
            sol.setStatus("EM_ANDAMENTO");
        } else if ("finalizar".equals(acao)) {
            sol.setStatus("FINALIZADO");
            sol.setFinalizado(true);  
        }
        solicitationRepo.save(sol);
    }
    
    return "redirect:/ver-pedido?id=" + idPedido;
}
    
    @GetMapping({"/", "/login", "/login.html"})
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("/solicitar-servico")
public String solicitarServico(@RequestParam int idProfissional, HttpSession session) {
    Usuarios user = (Usuarios) session.getAttribute("usuarioLogado");
    if (user == null) return "redirect:/login.html";

    com.mycompany.pi.Client cliente = clientRepo.findByUsuarioId(user.getId());
    
    Profissional profissional = profissionalRepo.findById(idProfissional).orElse(null);

    if (cliente != null && profissional != null) {
        Solicitation solicitacao = new Solicitation();
        solicitacao.setClient(cliente);
        solicitacao.setProfissional(profissional); 
        solicitacao.setDesc("Solicitação de orçamento pendente"); 
        solicitacao.setFinalizado(false);

        solicitationRepo.save(solicitacao);
    }

    return "redirect:/dashboard_cliente.html";
}
    
    @GetMapping("/cadastro.html")
    public String cadastroPage() {
        return "cadastro";
    }
    
    @GetMapping("/selecao_tipo.html")
    public String selecaoPage() {
        return "selecao_tipo";
    }
    
    @GetMapping("/dashboard_profissional.html")
    public String dashProf(Model model, HttpSession session) {
        Usuarios user = (Usuarios) session.getAttribute("usuarioLogado");
        if (user == null) return "redirect:/login.html";

        Profissional p = profissionalRepo.findByUsuarioId(user.getId());

        if(p != null) {
            List<Solicitation> pedidos = solicitationRepo.findByProfissional_Id(p.getId());
            model.addAttribute("listaPedidos", pedidos);
        }

        return "dashboard_profissional";
    }
    
    @GetMapping("/dashboard_cliente.html")
    public String dashCli(@RequestParam(required = false) String busca, Model model) {
        List<Profissional> profissionais;

        if (busca != null && !busca.isEmpty()) {

           profissionais = profissionalRepo.findAll().stream()
            .filter(p -> p.getService() != null &&
                         p.getService().getTipo() != null && 
                         p.getService().getTipo().toLowerCase().contains(busca.toLowerCase()))
            .collect(java.util.stream.Collectors.toList());
        } else {
            profissionais = profissionalRepo.findAll();
        }

        model.addAttribute("listaProfissionais", profissionais);
        return "dashboard_cliente"; 
    }

    @GetMapping("/detalhes_servico.html")
    public String detalhesPage() { return "detalhes_servico"; }


    @PostMapping("/logar")
    public String realizarLogin(@RequestParam String login, 
                                @RequestParam String senha, 
                                Model model, 
                                HttpSession session) { 

        Usuarios user = usuarioService.validarLogin(login, senha);
        if (user != null) {
            session.setAttribute("usuarioLogado", user); 

            if ("profissional".equalsIgnoreCase(user.getTipo())) {
                return "redirect:/dashboard_profissional.html";
            } else {
                return "redirect:/dashboard_cliente.html";
            }
        }
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login";
    }

   @PostMapping("/salvar-cliente")
    public String salvarCliente(@RequestParam String nome, @RequestParam String email, 
                                @RequestParam String senha, @RequestParam String cpf, 
                                @RequestParam String rg, @RequestParam String dataNasc,
                                @RequestParam String celular,
                                @RequestParam String endereco, @RequestParam int numero,
                                @RequestParam String uf, @RequestParam(required=false) String complemento) {
        
        usuarioService.registrarCliente(nome, email, senha, cpf, rg, dataNasc, celular, endereco, numero, uf, complemento);
        return "redirect:/login.html";
    }

    @PostMapping("/salvar-profissional-passo1")
    public String salvarProfissionalP1(@RequestParam String nome, @RequestParam String email, 
                                       @RequestParam String senha, @RequestParam String cpf, 
                                       @RequestParam String rg, @RequestParam String dataNasc,
                                       @RequestParam String celular,
                                       @RequestParam String endereco, @RequestParam int numero,
                                       @RequestParam String uf, @RequestParam(required=false) String complemento,
                                       Model model) {
        
        Profissional p = usuarioService.registrarProfissionalInicio(nome, email, senha, cpf, rg, dataNasc, celular, endereco, numero, uf, complemento);
        model.addAttribute("idProfissional", p.getId());
        return "detalhes_servico";
    }
    
    @GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate(); 
    return "redirect:/login.html";
}

    @PostMapping("/salvar-profissional-passo2")
    public String salvarProfissionalP2(@RequestParam int idProfissional,
                                       @RequestParam String titulo, 
                                       @RequestParam String preco, 
                                       @RequestParam String descricao, 
                                       @RequestParam String experiencia) {
        
        usuarioService.adicionarServicoAoProfissional(idProfissional, titulo, preco, descricao, experiencia);
        return "redirect:/login.html";
    }
}