package com.mycompany.pi.service;

import com.mycompany.pi.AddressClient;
import com.mycompany.pi.AddressProfissionals;
import com.mycompany.pi.CellphoneProfissional;
import com.mycompany.pi.CelphoneClients;
import com.mycompany.pi.Client;
import com.mycompany.pi.Profissional;
import com.mycompany.pi.Service;
import com.mycompany.pi.Usuarios;
import com.mycompany.pi.repository.AdressClientRepository;
import com.mycompany.pi.repository.AdressProfissionalsRepository;
import com.mycompany.pi.repository.ClientCelphoneRepository;
import com.mycompany.pi.repository.ClientRepository;
import com.mycompany.pi.repository.ProfessionalCelphonesRepository;
import com.mycompany.pi.repository.ProfissionalRepository;
import com.mycompany.pi.repository.ServiceRepository;
import com.mycompany.pi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private ProfissionalRepository profissionalRepo;
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private ServiceRepository serviceRepo;
    @Autowired 
    private ClientCelphoneRepository celClientRepo;
    @Autowired 
    private AdressClientRepository endClientRepo;
    @Autowired 
    private ProfessionalCelphonesRepository celProfRepo;
    @Autowired 
    private AdressProfissionalsRepository endProfRepo;

    public Usuarios validarLogin(String email, String senha) {
        return usuarioRepo.findByLoginAndSenha(email, senha);
    }

    @Transactional
    public void registrarCliente(String nome, String email, String senha, String cpf, String rg, 
                                 String dataNasc, String celularNum, 
                                 String rua, int numero, String uf, String compl) {
        Usuarios u = new Usuarios();
        u.setLogin(email);
        u.setSenha(senha);
        u.setNivel("cliente");
        u.setTipo("cliente");
        u = usuarioRepo.save(u);

        CelphoneClients cel = new CelphoneClients();
        cel.setNum(celularNum);
        cel = celClientRepo.save(cel);

        AddressClient end = new AddressClient();
        end.setEndereco(rua);
        end.setNum(numero);
        end.setUf(uf);
        end.setCompl(compl);
        end.setTipo("Residencial"); 
        end = endClientRepo.save(end);

        Client c = new Client();
        c.setNome(nome);
        c.setCpf(cpf);
        c.setRg(rg);
        c.setData(dataNasc);
        c.setUsuario(u);
        c.setCelular(cel);
        c.setEndereco(end);
        
        clientRepo.save(c);
    }

    @Transactional
    public Profissional registrarProfissionalInicio(String nome, String email, String senha, String cpf, String rg, 
                                                    String dataNasc, String celularNum,
                                                    String rua, int numero, String uf, String compl) {
        Usuarios u = new Usuarios();
        u.setLogin(email);
        u.setSenha(senha);
        u.setNivel("profissional");
        u.setTipo("profissional");
        u = usuarioRepo.save(u);

        CellphoneProfissional cel = new CellphoneProfissional();
        cel.setNum(celularNum);
        cel = celProfRepo.save(cel); 

        AddressProfissionals end = new AddressProfissionals();
        end.setEndereco(rua);
        end.setNum(numero);
        end.setUf(uf);
        end.setCompl(compl);
        end.setTipo("Comercial");
        end = endProfRepo.save(end);

        Profissional p = new Profissional();
        p.setNome(nome);
        p.setCpf(cpf);
        p.setRg(rg);
        p.setData(dataNasc);
        p.setUsuario(u);
        p.setCelular(cel);
        p.setEndereco(end);
        
        return profissionalRepo.save(p);
    }

    @Transactional
    public void adicionarServicoAoProfissional(int idProfissional, String titulo, String precoStr, String descricao, String experiencia) {
        
        Service s = new Service();
        s.setTipo(titulo); 
        s.setDescricao(descricao);
        s = serviceRepo.save(s);

        Profissional p = profissionalRepo.findById(idProfissional).orElse(null);
        
        if (p != null) {
            p.setService(s);
            p.setDesc(descricao);     
            
            
           if (precoStr != null && !precoStr.isEmpty()) {
                
                String apenasNumeros = precoStr.replaceAll("[^0-9,]", "");
                String valorFinal = apenasNumeros.replace(",", ".");
                try {
                    p.setValorHora(Double.parseDouble(valorFinal));
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter valor: " + e.getMessage());
                    p.setValorHora(0.0);
                }
            }
            
            profissionalRepo.save(p);
        }
    }
}