package com.mycompany.pi;

import com.mycompany.pi.service.UsuarioService;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTE DO SISTEMA (BACKEND) ===");
        
        UsuarioService service = new UsuarioService();
        
        System.out.println("\n-> Testando Cadastro de Profissional...");
        try {
            service.registrarProfissional(
                "João Silva", 
                "joao@email.com", 
                "123456", 
                "111.222.333-44", 
                "12.345.678-9", 
                "01/01/1990"
            );
        } catch (Exception e) {
            System.out.println("ERRO no cadastro: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n-> Testando Login...");
        Usuarios usuarioTeste = new Usuarios();
        usuarioTeste.setLogin("joao@email.com");
        usuarioTeste.setSenha("123456");
        
        Usuarios logado = UsuarioDAO.validarUsuario(usuarioTeste);
        
        if(logado != null){
            System.out.println("SUCESSO: Usuário logado! ID: " + logado.getId() + " - Nível: " + logado.getNivel());
            
            System.out.println("\n-> Testando Adição de Serviço...");
            try {
                service.adicionarServicoAoProfissional(logado.getId(), "Eletricista", "Reparos residenciais gerais");
            } catch (Exception e) {
                System.out.println("ERRO ao adicionar serviço: " + e.getMessage());
            }
            
        } else {
            System.out.println("FALHA: Login incorreto ou usuário não encontrado.");
        }
    }
}