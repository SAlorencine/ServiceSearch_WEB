package com.mycompany.pi.repository;
import com.mycompany.pi.PhoneClients;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPhoneRepository extends JpaRepository<PhoneClients, Integer> {
    
}