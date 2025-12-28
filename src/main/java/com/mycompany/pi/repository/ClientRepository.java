package com.mycompany.pi.repository;

import com.mycompany.pi.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    // Busca cliente pelo ID do Usuário vinculado
    Client findByUsuarioId(int usuarioId);
}