package com.mycompany.pi;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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
    private Double valorHora;
    
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_adress")
    private AddressProfissionals endereco;
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_celphone")
    private CellphoneProfissional celular;
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_phone")
    private PhoneProfissionals telefone;
    
    
     @ManyToOne
    @JoinColumn(name = "idService")
    private Service service;
    
    @OneToOne
    @JoinColumn(name = "idUser")
    private Usuarios usuario;

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }


    
    public void setService(Service service){
    this.service = service;
    }
    public Service getService() {
        return service;
    }
    
    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Profissional() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AddressProfissionals getEndereco() {
        return endereco;
    }

    public void setEndereco(AddressProfissionals endereco) {
        this.endereco = endereco;
    }

    public CellphoneProfissional getCelular() {
        return celular;
    }

    public void setCelular(CellphoneProfissional celular) {
        this.celular = celular;
    }

    public PhoneProfissionals getTelefone() {
        return telefone;
    }

    public void setTelefone(PhoneProfissionals telefone) {
        this.telefone = telefone;
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