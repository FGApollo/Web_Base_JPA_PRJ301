/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thinhnhn.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author FGApollo
 */
public class RegistrationBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MVC2SE1819_JPAPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Registration checkLogin(String username, String password) {
        Registration result = null;
        EntityManager em = this.emf.createEntityManager();
        String jpql = "Select r "
                + "From Registration r "
                + "Where r.username = :username "
                + "And r.password = :password";

        try {
            Query query = em.createQuery(jpql);

            query.setParameter("username", username);
            query.setParameter("password", password);
            result = (Registration) query.getSingleResult();

        } catch(NoResultException ex) {
            System.out.println("NoResultException :" + ex);
        } finally {
            em.close();
        }

        return result;
    }

    public List<Registration> searchLastName(String searchValue) {
        List<Registration> result = null;
        EntityManager em = this.emf.createEntityManager();
        String jpql = "Select r "
                + "From Registration r "
                + "Where r.lastname Like :lastname ";

        try {
            Query query = em.createQuery(jpql);
            query.setParameter("lastname", "%" + searchValue + "%");
            result = query.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    public boolean deleteAccount(String username) {
        boolean result = false;
        EntityManager em = this.emf.createEntityManager();

        try {
            Registration reg = em.find(Registration.class, username);

            if (reg != null) {
                em.getTransaction().begin();
                em.remove(reg);
                em.getTransaction().commit();
                result = true;
            }//existed

        } finally {
            em.close();
        }

        return result;
    }

    public boolean updateAccount(String username, String password, boolean role) {
        boolean result = false;
        EntityManager em = this.emf.createEntityManager();

        try {
            Registration reg = em.find(Registration.class, username);

            if (reg != null) {
                reg.setPassword(password);
                reg.setIsAdmin(role);
                em.getTransaction().begin();
                em.merge(reg);
                em.getTransaction().commit();
                result = true;
            }

        } finally {
            em.close();
        }

        return result;
    }

    public boolean createAccount(Registration accounts) {
        boolean result = false;
        EntityManager em = this.emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(accounts);
            em.getTransaction().commit();
            result = true;

        } finally {
            em.close();
        }
        return result;
    }
}
