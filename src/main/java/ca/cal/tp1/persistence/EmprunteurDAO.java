package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmprunteurDAO implements IEmprunteurDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TP2-PU");

    @Override
    public void save(Emprunteur emprunteur) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emprunteur);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Emprunteur get(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Emprunteur.class, id);
        } finally {
            em.close();
        }
    }
}
