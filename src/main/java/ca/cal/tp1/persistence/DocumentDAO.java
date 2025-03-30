package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.CD;
import ca.cal.tp1.modele.DVD;
import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DocumentDAO implements DocumentRepository {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TP2-PU");

    @Override
    public void save(Document document) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(document);
            em.getTransaction().commit();
        }
    }

    @Override
    public Document get(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Document.class, id);
        }
    }

    public List<Livre> searchLivreByTitre(String motCle){
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select l FROM Livre l WHERE LOWER(l.titre) LIKE LOWER(CONCAT('%', :motCle, '%'))", Livre.class)
                    .setParameter("motCle", "%" + motCle + "%")
                    .getResultList();
        }
    }

    public List<Livre> searchLivreByAuteur(String auteur){
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select l FROM Livre l WHERE LOWER(l.auteur) LIKE LOWER(CONCAT('%', :auteur, '%'))", Livre.class)
                    .setParameter("auteur", "%" + auteur + "%")
                    .getResultList();
        }
    }

    public List<Livre> searchLivreByAnnee(Long annee){
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select l FROM Livre l WHERE l.annee = :annee", Livre.class)
                    .setParameter("annee", annee)
                    .getResultList();
        }
    }

    public List<CD> searchCDByTitre(String motCle){
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select c FROM CD c WHERE LOWER(c.titre) LIKE LOWER(CONCAT('%', :motCle, '%'))", CD.class)
                    .setParameter("motCle", "%" + motCle + "%")
                    .getResultList();
        }
    }

    public List<CD> searchCDByArtiste(String artiste){
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select c FROM CD c WHERE LOWER(c.artiste) LIKE LOWER(CONCAT('%', :artiste, '%'))", CD.class)
                    .setParameter("artiste", "%" + artiste + "%")
                    .getResultList();
        }
    }

    public List<DVD> searchDVDByTitre(String motCle){
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select d FROM DVD d WHERE LOWER(d.titre) LIKE LOWER(CONCAT('%', :motCle, '%'))", DVD.class)
                    .setParameter("motCle", "%" + motCle + "%")
                    .getResultList();
        }
    }

    public List<DVD> searchDVDByArtiste(String artiste){
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select d FROM DVD d WHERE LOWER(d.artiste) LIKE LOWER(CONCAT('%', :artiste, '%'))", DVD.class)
                    .setParameter("artiste", "%" + artiste + "%")
                    .getResultList();
        }
    }

    @Override
    public Long getexemplairesDisponibles(Long documentId) {
        try(EntityManager em = emf.createEntityManager()) {
            Document doc = em.find(Document.class, documentId);
            if (doc == null) {
                return 0L;
            }
            Long totalExemplaires = doc.getNombreExemplaires();

            Long nbEmpruntes = em.createQuery(
                            "select COUNT(ed) FROM EmpruntDetail ed " +
                                    "WHERE ed.document.documentID = :docId " +
                                    "AND ed.dateRetourActuelle IS NULL",
                            Long.class
                    )
                    .setParameter("docId", documentId)
                    .getSingleResult();

            return totalExemplaires - nbEmpruntes.intValue();
        }
    }
}
