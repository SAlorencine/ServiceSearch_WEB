
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOClientCelphone {
    public static void cadastrar(CelphoneClients c){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(c);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
    }
    public static List<CelphoneClients> buscarPorId(int filtro) {
                EntityManager manager = JPAUtil.conectar();
                List<CelphoneClients> lista = new ArrayList<>();
            try {
                TypedQuery<CelphoneClients> query = manager.createQuery(
                "SELECT c FROM CelphoneClients c WHERE c.id = :id", CelphoneClients.class);
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
