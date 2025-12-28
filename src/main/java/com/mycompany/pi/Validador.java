package com.mycompany.pi;



public class Validador {
    
    // Regra de Negócio 1: A senha deve ter no mínimo 6 caracteres
    public boolean validarSenha(String senha) {
        if (senha == null) {
            return false;
        }
        return senha.length() >= 6;
    }

    // Regra de Negócio 2: Validação simples de formato de e-mail (contém @ e .)
    public boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
    
    // Regra de Negócio 3: Verifica se o texto não está vazio (útil para Nome, Descrição)
    public boolean validarTextoObrigatorio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
}