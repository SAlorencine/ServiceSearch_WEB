package com.mycompany.pi.service;

import com.mycompany.pi.DAOProfissional;
import com.mycompany.pi.DAOService;
import com.mycompany.pi.Profissional;
import com.mycompany.pi.Service;
import com.mycompany.pi.UsuarioDAO;
import com.mycompany.pi.Usuarios;

public class UsuarioService {

    public void registrarProfissional(String nome, String email, String senha, String cpf, String rg, String dataNasc) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setLogin(email);
        usuario.setSenha(senha);
        usuario.setNivel("profissional");
        usuario.setTipo("profissional");
        
        Profissional profissional = new Profissional();
        profissional.setNome(nome);
        profissional.setCpf(cpf);
        profissional.setRg(rg);
        profissional.setData(dataNasc);
        
        UsuarioDAO.cadastrar(usuario); 
        
        if(usuario.getId() == 0) {
            Usuarios salvo = UsuarioDAO.validarUsuario(usuario);
            if(salvo != null) usuario.setId(salvo.getId());
        }
        
        profissional.setIdUser(usuario.getId()); 
        DAOProfissional.cadastrar(profissional);
        
        System.out.println("Profissional cadastrado com sucesso: " + nome);
    }
    
    public void adicionarServicoAoProfissional(int idUsuarioProfissional, String tipoServico, String descricao) throws Exception {
        Service s = new Service();
        s.setTipo(tipoServico);
        s.setDescricao(descricao);
        DAOService.cadastrar(s);

        java.util.List<Profissional> lista = DAOProfissional.buscarPorId(idUsuarioProfissional);
        if(!lista.isEmpty()){
            Profissional prof = lista.get(0);
            prof.setIdService(s.getId());
            prof.setDesc(descricao);
            DAOProfissional.atualizar(prof);

            System.out.println("Serviço vinculado ao profissional ID: " + prof.getId());
        } else {
            throw new Exception("Profissional não encontrado para o ID de Usuário: " + idUsuarioProfissional);
        }
    }
}