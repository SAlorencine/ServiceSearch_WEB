package com.mycompany.pi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CellphoneProfissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String num;

    public CellphoneProfissional(int id, String num) {
        this.id = id;
        this.num = num;
    }

    public CellphoneProfissional() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    
    
}
