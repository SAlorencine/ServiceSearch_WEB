package com.mycompany.pi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddressProfissionals{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    String endereco;
    String uf;
    int num;
    String compl;
    String tipo;

    public AddressProfissionals() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
