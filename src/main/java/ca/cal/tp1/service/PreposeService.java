package ca.cal.tp1.service;

import ca.cal.tp1.modele.Document;
import ca.cal.tp1.persistence.IDocumentDAO;

public class PreposeService {
    private final IDocumentDAO documentDAO;

    public PreposeService(IDocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public void ajouterDocument(Document document) {
        documentDAO.save(document);
    }

    public void updateDocument(Document document) {
        documentDAO.update(document);
    }

}
