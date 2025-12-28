package com.mycompany.pi.repository;
import com.mycompany.pi.AddressProfissionals;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressProfissionalsRepository extends JpaRepository<AddressProfissionals, Integer> {
    
}