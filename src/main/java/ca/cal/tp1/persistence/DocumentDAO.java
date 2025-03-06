package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.CD;
import ca.cal.tp1.modele.DVD;
import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DocumentDAO implements IDocumentDAO {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TP2-PU");

    @Override
    public void save(Document document) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(document);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Document get(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Document.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Document> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT d FROM Document d", Document.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Document document) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(document);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Livre> searchLivreByTitre(String motCle){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livre l WHERE LOWER(l.titre) LIKE LOWER(CONCAT('%', :motCle, '%'))", Livre.class)
                    .setParameter("motCle", "%" + motCle + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Livre> searchLivreByAuteur(String auteur){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livre l WHERE LOWER(l.auteur) LIKE LOWER(CONCAT('%', :auteur, '%'))", Livre.class)
                    .setParameter("auteur", "%" + auteur + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Livre> searchLivreByAnnee(Long annee){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livre l WHERE l.annee = :annee", Livre.class)
                    .setParameter("annee", annee)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<CD> searchCDByTitre(String motCle){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM CD c WHERE LOWER(c.titre) LIKE LOWER(CONCAT('%', :motCle, '%'))", CD.class)
                    .setParameter("motCle", "%" + motCle + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<CD> searchCDByArtiste(String artiste){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM CD c WHERE LOWER(c.artiste) LIKE LOWER(CONCAT('%', :artiste, '%'))", CD.class)
                    .setParameter("artiste", "%" + artiste + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<DVD> searchDVDByTitre(String motCle){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT d FROM DVD d WHERE LOWER(d.titre) LIKE LOWER(CONCAT('%', :motCle, '%'))", DVD.class)
                    .setParameter("motCle", "%" + motCle + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<DVD> searchDVDByArtiste(String artiste){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT d FROM DVD d WHERE LOWER(d.artiste) LIKE LOWER(CONCAT('%', :artiste, '%'))", DVD.class)
                    .setParameter("artiste", "%" + artiste + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
