package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.CD;
import ca.cal.tp1.modele.DVD;
import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Livre;

import java.util.List;

public interface IDocumentDAO {
    public Long getexemplairesDisponibles(Long documentId);
    void save(Document document);
    Document get(Long id);
    List<Document> getAll();
    void update(Document document);

    List<Livre> searchLivreByTitre(String motCle);

    List<Livre> searchLivreByAuteur(String auteur);

    List<Livre> searchLivreByAnnee(Long annee);

    List<CD> searchCDByTitre(String motCle);

    List<CD> searchCDByArtiste(String artiste);

    List<DVD> searchDVDByTitre(String motCle);
    List<DVD> searchDVDByArtiste(String artiste);
}
