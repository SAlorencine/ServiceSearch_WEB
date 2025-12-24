/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author loren
 */
public class DAOAdressClient {
    public static void cadastrar(AddressClient a){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(a);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
    }
    public static List<AddressClient> buscarPorId(int filtro) {
                EntityManager manager = JPAUtil.conectar();
                List<AddressClient> lista = new ArrayList<>();
            try {
                TypedQuery<AddressClient> query = manager.createQuery(
                "SELECT c FROM AddressClient c WHERE c.id = :id", AddressClient.class);
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
