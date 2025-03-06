package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Livre;

import java.util.List;

public interface IDocumentDAO {
    void save(Document document);
    Document get(int id);
    List<Document> getAll();
    void update(Document document);

    List<Livre> searchLivreByTitre(String motCle);

    List<Livre> searchLivreByAuteur(String auteur);
}
