package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class UsuarioDAO {
    
    public static Usuarios validarUsuario(Usuarios u){
        EntityManager manager = JPAUtil.conectar();
        try {
            Query consulta = manager.createQuery(
                "SELECT u FROM Usuarios u WHERE u.login = :login AND u.senha = :senha"
            );
            consulta.setParameter("login", u.getLogin());
            consulta.setParameter("senha", u.getSenha());

            List<Usuarios> listaUser = consulta.getResultList();
            if (!listaUser.isEmpty()) {
                return listaUser.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return null;
    }

    public static void cadastrar(Usuarios p) throws Exception {
        EntityManager manager = JPAUtil.conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(p);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) manager.getTransaction().rollback();
            throw e; 
        } finally {
            manager.close();
        }
    }
}