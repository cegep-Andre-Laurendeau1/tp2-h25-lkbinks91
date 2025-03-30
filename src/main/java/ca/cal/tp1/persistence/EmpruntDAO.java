package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.EmpruntDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpruntDAO implements EmpruntRepository {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TP2-PU");


    @Override
    public Emprunt saveEmprunt(Emprunt emprunt) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (emprunt.getId() == null) {
                em.persist(emprunt);
            } else {
                emprunt = em.merge(emprunt);
            }
            em.getTransaction().commit();
            return emprunt;
        }
    }

    @Override
    public EmpruntDetail saveEmpruntDetail(EmpruntDetail detail) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (detail.getId() == null) {
                em.persist(detail);
            } else {
                detail = em.merge(detail);
            }
            em.getTransaction().commit();
            return detail;
        }
    }

    @Override
    public String getEmpruntStatus(Long empruntId) {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT COUNT(ed) FROM EmpruntDetail ed " +
                    "WHERE ed.emprunt.id = :empruntId " +
                    "AND ed.dateRetourActuelle IS NULL";

            Long nonRetournes = em.createQuery(jpql, Long.class)
                    .setParameter("empruntId", empruntId)
                    .getSingleResult();

            return nonRetournes > 0 ? "EN_COURS" : "RETOURNE";
        }
    }
}
