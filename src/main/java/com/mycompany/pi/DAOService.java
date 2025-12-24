package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOService {
    
    public static void cadastrar(Service s) throws Exception {
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(s);
            manager.getTransaction().commit();
        }catch(Exception e){
            if(manager.getTransaction().isActive()) manager.getTransaction().rollback();
            throw e;
        }finally{
            manager.close();
        }
    }

    public static List<Service> buscarPorId(int filtro) {
        EntityManager manager = JPAUtil.conectar();
        List<Service> lista = new ArrayList<>();
        try {
            TypedQuery<Service> query = manager.createQuery(
                "SELECT p FROM Service p WHERE p.id = :id", Service.class);
            query.setParameter("id", filtro);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return lista;
    }

    public static List<Service> listar(){
        EntityManager manager = JPAUtil.conectar();
        List<Service> lista = new ArrayList<>();
        try{
            Query sql = manager.createQuery("SELECT p FROM Service p");
            lista = sql.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }
        return lista;
    }
}