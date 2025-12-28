package com.mycompany.pi.repository;
import com.mycompany.pi.AddressClient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressClientRepository extends JpaRepository<AddressClient, Integer> {
    
}