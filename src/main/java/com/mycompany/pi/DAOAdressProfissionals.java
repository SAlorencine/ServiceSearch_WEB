/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOAdressProfissionals {
    public static void cadastrar(AddressProfissionals p){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(p);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
    }
    public static List<AddressProfissionals> buscarPorId(int filtro) {
                EntityManager manager = JPAUtil.conectar();
                List<AddressProfissionals> lista = new ArrayList<>();
            try {
                TypedQuery<AddressProfissionals> query = manager.createQuery(
                "SELECT c FROM AddressProfissionals c WHERE c.id = :id", AddressProfissionals.class);
                query.setParameter("id", filtro);
                lista = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JPAUtil.desconecta();
            }           
    return lista;
}
}
