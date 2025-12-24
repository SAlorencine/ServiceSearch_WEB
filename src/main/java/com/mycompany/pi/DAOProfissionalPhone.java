
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOProfissionalPhone {
    public static void cadastrar(PhoneProfissionals p){
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
    
    public static List<PhoneProfissionals> buscarPorId(int filtro) {
                EntityManager manager = JPAUtil.conectar();
                List<PhoneProfissionals> lista = new ArrayList<>();
            try {
                TypedQuery<PhoneProfissionals> query = manager.createQuery(
                "SELECT c FROM PhoneProfissionals c WHERE c.id = :id", PhoneProfissionals.class);
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
