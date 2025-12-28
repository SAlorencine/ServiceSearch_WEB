package com.mycompany.pi.repository;
import com.mycompany.pi.CelphoneClients;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCelphoneRepository extends JpaRepository<CelphoneClients, Integer> {
    
}