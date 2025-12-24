package com.mycompany.pi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Solicitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int idClient;
    int idProfissional;
    String descr;
    boolean finalizado;

    public boolean isFinalizada() {
        return finalizado;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizado = finalizada;
    }
    
    

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return descr;
    }

    public void setDesc(String desc) {
        this.descr = desc;
    }

    public Solicitation() {
    }
    
    
}
