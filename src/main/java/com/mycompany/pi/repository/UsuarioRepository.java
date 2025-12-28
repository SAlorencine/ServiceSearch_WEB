package com.mycompany.pi.repository;

import com.mycompany.pi.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    
    // O Spring gera o SQL automaticamente baseado no nome do método!
    // Equivalente a: SELECT * FROM Usuarios WHERE login = ? AND senha = ?
    Usuarios findByLoginAndSenha(String login, String senha);
    
    // O método 'save()' para cadastrar já existe por padrão no JpaRepository.
}