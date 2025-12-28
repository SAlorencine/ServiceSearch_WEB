package com.mycompany.pi.repository;
import com.mycompany.pi.CellphoneProfissional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalCelphonesRepository extends JpaRepository<CellphoneProfissional, Integer> {
    
}