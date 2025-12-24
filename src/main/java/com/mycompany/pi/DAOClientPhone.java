
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class DAOClientPhone {
   public static void cadastrar(PhoneClients p){
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
   public static List<PhoneClients> buscarPorId(int filtro) {
                EntityManager manager = JPAUtil.conectar();
                List<PhoneClients> lista = new ArrayList<>();
            try {
                TypedQuery<PhoneClients> query = manager.createQuery(
                "SELECT c FROM PhoneClients c WHERE c.id = :id", PhoneClients.class);
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
