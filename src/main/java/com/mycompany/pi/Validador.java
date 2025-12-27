package com.mycompany.pi;

public class Validador {
    
    public boolean validarSenha(String senha) {
        if (senha == null) {
            return false;
        }
        return senha.length() >= 6;
    }

    public boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
    
    public boolean validarTextoObrigatorio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
}