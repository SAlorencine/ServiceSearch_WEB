package com.mycompany.pi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profissional {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nome;
    private String cpf;
    private String rg;
    private String data;
    private String descricao;
    
    // Mudança aqui: de int para Integer para permitir NULL
    private Integer idAdress;
    private Integer idCelphone;
    private Integer idPhone;
    private Integer idService;
    
    private int idUser; // Este continua int pois sempre teremos um usuário vinculado

    public Profissional() {
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public Integer getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(Integer idAdress) {
        this.idAdress = idAdress;
    }

    public Integer getIdCelphone() {
        return idCelphone;
    }

    public void setIdCelphone(Integer idCelphone) {
        this.idCelphone = idCelphone;
    }

    public Integer getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(Integer idPhone) {
        this.idPhone = idPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDesc() {
        return descricao;
    }

    public void setDesc(String desc) {
        this.descricao = desc;
    }
}