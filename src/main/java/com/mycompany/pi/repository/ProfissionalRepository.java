package com.mycompany.pi.repository;

import com.mycompany.pi.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
    
    // Busca profissional pelo ID do Usuário vinculado (Chave Estrangeira lógica)
    Profissional findByUsuarioId(int usuarioId);
    
    // Se precisar buscar pelo CPF:
    Profissional findByCpf(String cpf);
}