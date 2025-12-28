package com.mycompany.pi.repository;

import com.mycompany.pi.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation, Integer> {
    
    List<Solicitation> findByClient_Id(int idClient);

    List<Solicitation> findByProfissional_Id(int idProfissional);
}