package com.mycompany.pi.repository;

import com.mycompany.pi.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    // Apenas os métodos padrões (findAll, save, findById) já são suficientes
}