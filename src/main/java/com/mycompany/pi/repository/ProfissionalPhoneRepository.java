package com.mycompany.pi.repository;
import com.mycompany.pi.PhoneProfissionals;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalPhoneRepository extends JpaRepository<PhoneProfissionals, Integer> {
    
}