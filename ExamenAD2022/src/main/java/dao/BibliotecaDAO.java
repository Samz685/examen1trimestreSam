package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ejemplar;
import models.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {
    
    private static SessionFactory sessionFactory;
    
    static{
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Conexión realizada con éxito");

        }catch(Exception ex){
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public void saveLibro(Libro e) {

        if (e != null) {

            try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
                Transaction t = s.beginTransaction();
                s.save(e);
                t.commit();

            }

        }

        System.out.println("Método saveLibro no implementado");

    }

    public HashSet<Libro> findByEstado(String estado) {

        HashSet<Libro> salida = new HashSet<Libro>();
        /* 
         Devuelve el conjunto de libros que tenga el estado indicado      
         */

        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("SELECT ej.libro FROM Ejemplar ej where estado =: param");
            q.setParameter("param", estado);

            for (Object lib : q.list()) {
                salida.add((Libro) lib);
            }

        }

        return salida;

    }
    
    public void printInfo(){
        
        /* 
          Muestra por consola todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.
          
          Debe imprimirlo de esta manera:
        
          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...
        
        */
        
         HashSet<Libro> salida = new HashSet<Libro>();
        /* 
         Devuelve el conjunto de libros que tenga el estado indicado      
         */

        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("SELECT ej.libro, COUNT(ej) FROM Ejemplar ej");
   
            System.out.println(q.list());
            

        }

        
        
        
        System.out.println("Método printInfo no implementado");
        
    }
    

    

    
}
