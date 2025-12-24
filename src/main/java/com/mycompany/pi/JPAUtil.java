
package com.mycompany.pi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT = "Pi";
    
    private static EntityManager gerente;
    private static EntityManagerFactory fabrica;
    
    public static EntityManager conectar(){
        
        if(fabrica == null || !fabrica.isOpen()){
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        
        if(gerente == null || !gerente.isOpen()){
            gerente = fabrica.createEntityManager();
        }
        System.out.println("Criando EntityManager com unidade: " + PERSISTENCE_UNIT);
        return gerente;
    
    }
    
    public static void desconecta(){
    
        if(gerente != null && gerente.isOpen()){
            gerente.close();
            fabrica.close();
        }
    }
}