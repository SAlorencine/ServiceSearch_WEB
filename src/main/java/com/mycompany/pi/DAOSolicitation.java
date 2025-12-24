package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOSolicitation {
    
    public static void cadastrar(Solicitation s) throws Exception {
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

    public static List<Solicitation> buscarPorId(int filtro) {
        EntityManager manager = JPAUtil.conectar();
        List<Solicitation> lista = new ArrayList<>();
        try {
            TypedQuery<Solicitation> query = manager.createQuery(
                "SELECT p FROM Solicitation p WHERE p.id = :id", Solicitation.class);
            query.setParameter("id", filtro);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return lista;
    }

    public static List<Solicitation> listar(){
        EntityManager manager = JPAUtil.conectar();
        List<Solicitation> lista = new ArrayList<>();
        try{
            Query sql = manager.createQuery("SELECT p FROM Solicitation p");
            lista = sql.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }
        return lista;
    }

    public static void alterarFinalizado(int id, boolean novoValor) throws Exception {
        EntityManager manager = JPAUtil.conectar();
        try {
            manager.getTransaction().begin();
            Solicitation s = manager.find(Solicitation.class, id);

            if (s != null) {
                s.setFinalizada(novoValor);
                manager.merge(s); 
            } else {
                throw new Exception("Solicitação não encontrada para o ID: " + id);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            if(manager.getTransaction().isActive()) manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }
    }
}