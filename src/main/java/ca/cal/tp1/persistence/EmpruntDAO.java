package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpruntDAO implements IEmpruntDAO {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TP2-PU");

    public void save(Emprunt emprunt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emprunt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
