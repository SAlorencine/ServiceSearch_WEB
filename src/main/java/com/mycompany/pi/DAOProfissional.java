package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOProfissional {
    
    // CORREÇÃO: throws Exception adicionado
    public static void cadastrar(Profissional p) throws Exception {
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(p);
            manager.getTransaction().commit();
        }catch(Exception e){
            if(manager.getTransaction().isActive()) manager.getTransaction().rollback();
            throw e; 
        }finally{
            manager.close();
        }
    }

    public static void atualizar(Profissional p) throws Exception {
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.merge(p);
            manager.getTransaction().commit();
        }catch(Exception e){
            if(manager.getTransaction().isActive()) manager.getTransaction().rollback();
            throw e; 
        }finally{
            manager.close();
        }
    }

    public static List<Profissional> listar(){
        EntityManager manager = JPAUtil.conectar();
        List<Profissional> lista = new ArrayList<>();
        try{
            Query sql = manager.createQuery("SELECT p FROM Profissional p");
            lista = sql.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }
        return lista;
    }

    public static List<Profissional> buscarPorId(int filtro) {
        EntityManager manager = JPAUtil.conectar();
        List<Profissional> lista = new ArrayList<>();
        try {
            TypedQuery<Profissional> query = manager.createQuery(
                "SELECT p FROM Profissional p WHERE p.idUser = :idUser", Profissional.class);
            query.setParameter("idUser", filtro);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return lista;
    }

    public static List<Profissional> buscarPorIdProprio(int filtro) {
        EntityManager manager = JPAUtil.conectar();
        List<Profissional> lista = new ArrayList<>();
        try {
            TypedQuery<Profissional> query = manager.createQuery(
                "SELECT p FROM Profissional p WHERE p.id = :id", Profissional.class);
            query.setParameter("id", filtro);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return lista;
    }
}