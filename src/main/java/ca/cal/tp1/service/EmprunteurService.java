package ca.cal.tp1.service;

import ca.cal.tp1.modele.*;
import ca.cal.tp1.persistence.EmprunteurDAO;
import ca.cal.tp1.persistence.IDocumentDAO;
import ca.cal.tp1.persistence.IEmpruntDAO;
import ca.cal.tp1.persistence.IEmprunteurDAO;
import ca.cal.tp1.service.dto.EmpruntDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmprunteurService {
    private final IEmprunteurDAO emprunteurDAO;
    private final IEmpruntDAO empruntDAO;

    private final IDocumentDAO documentDAO;

    public EmprunteurService(IEmprunteurDAO emprunteurDAO, IEmpruntDAO empruntDAO, IDocumentDAO documentDAO) {
        this.emprunteurDAO = emprunteurDAO;
        this.empruntDAO = empruntDAO;
        this.documentDAO = documentDAO;
    }

    public void ajouteEmprunteur(Emprunteur emprunteur) {
        emprunteurDAO.save(emprunteur);
    }

    public Emprunteur getEmprunteur(Long id) {
        return emprunteurDAO.get(id);
    }

    public EmpruntDTO emprunterDocument(Long emprunteurId, Long documentId) {
        Emprunteur emp = emprunteurDAO.get(emprunteurId);
        if (emp == null) {
            throw new IllegalArgumentException("Emprunteur introuvable : " + emprunteurId);
        }
        Document doc = documentDAO.get(documentId);
        if (doc == null) {
            throw new IllegalArgumentException("Document introuvable : " + documentId);
        }
        Long dispo = documentDAO.getexemplairesDisponibles(doc.getDocumentID());
        if (dispo == 0) {
            throw new IllegalStateException("Aucun exemplaire disponible pour : " + doc.getTitre());
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setStatus("En cours");
        emprunt.setEmprunteur(emp);
        emprunt = empruntDAO.saveEmprunt(emprunt);

        EmpruntDetail empruntDetail = new EmpruntDetail();
        empruntDetail.setEmprunt(emprunt);
        empruntDetail.setDocument(doc);
        empruntDetail.setStatus("Emprunté");

        LocalDate now = LocalDate.now();
        LocalDate dateRetourPrevue;
        if (doc instanceof Livre) {
            dateRetourPrevue = now.plusDays(21);
        } else if (doc instanceof CD) {
            dateRetourPrevue = now.plusDays(14);
        } else if (doc instanceof DVD) {
            dateRetourPrevue = now.plusDays(7);
        } else {
            throw new IllegalArgumentException("Type de document non supporté : " + doc.getClass().getName());
        }
        empruntDetail.setDateRetourPrevue(dateRetourPrevue);

        empruntDetail = empruntDAO.saveEmpruntDetail(empruntDetail);

        String dateEmpruntStr = emprunt.getDateEmprunt().toString();
        String dateRetourStr = (empruntDetail.getDateRetourPrevue() != null) ? empruntDetail.getDateRetourPrevue().toString() : "null";

        return EmpruntDTO.fromEntity(emprunt.getId(), dateEmpruntStr, dateRetourStr, emp.getId(), doc.getDocumentID());
    }

    public List<EmpruntDTO> documentsEmprunter(Long emprunteurId, List<Long> documentIds) {
        List<EmpruntDTO> emprunts = new ArrayList<>();
        for (Long documentId : documentIds) {
            EmpruntDTO empruntDTO = emprunterDocument(emprunteurId, documentId);
            emprunts.add(empruntDTO);
        }
        return emprunts;
    }
}
