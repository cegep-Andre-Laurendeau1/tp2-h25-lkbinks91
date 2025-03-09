package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.EmpruntDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpruntDAO implements IEmpruntDAO {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TP2-PU");


    @Override
    public Emprunt saveEmprunt(Emprunt emprunt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (emprunt.getId() == null) {
                em.persist(emprunt);
            } else {
                emprunt = em.merge(emprunt);
            }
            em.getTransaction().commit();
            return emprunt;
        } finally {
            em.close();
        }
    }

    @Override
    public EmpruntDetail saveEmpruntDetail(EmpruntDetail detail) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (detail.getId() == null) {
                em.persist(detail);
            } else {
                detail = em.merge(detail);
            }
            em.getTransaction().commit();
            return detail;
        } finally {
            em.close();
        }
    }

}
