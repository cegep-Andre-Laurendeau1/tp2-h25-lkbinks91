package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmprunteurDAO implements EmprunteurRepository {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TP2-PU");

    @Override
    public void save(Emprunteur emprunteur) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(emprunteur);
            em.getTransaction().commit();
        }
    }

    @Override
    public Emprunteur get(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Emprunteur.class, id);
        }
    }
}
