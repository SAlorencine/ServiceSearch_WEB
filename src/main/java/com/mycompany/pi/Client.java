package com.mycompany.pi;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String data;
    
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_adress")
    private AddressClient endereco;
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_celphone")
    private CelphoneClients celular;
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_phone")
    private PhoneClients telefone;
    
    @OneToOne
    @JoinColumn(name = "idUser") 
    private Usuarios usuario;

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }

    public Client() {
    }

    public AddressClient getEndereco() {
        return endereco;
    }

    public void setEndereco(AddressClient endereco) {
        this.endereco = endereco;
    }

    public CelphoneClients getCelular() {
        return celular;
    }

    public void setCelular(CelphoneClients celular) {
        this.celular = celular;
    }

    public PhoneClients getTelefone() {
        return telefone;
    }

    public void setTelefone(PhoneClients telefone) {
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
}