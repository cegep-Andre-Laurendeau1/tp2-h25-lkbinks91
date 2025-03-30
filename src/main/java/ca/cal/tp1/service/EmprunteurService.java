package ca.cal.tp1.service;

import ca.cal.tp1.exception.DocumentNotFoundException;
import ca.cal.tp1.exception.EmprunteurNotFoundException;
import ca.cal.tp1.exception.NoAvailableCopiesException;
import ca.cal.tp1.exception.UnsupportedDocumentTypeException;
import ca.cal.tp1.modele.*;
import ca.cal.tp1.persistence.DocumentRepository;
import ca.cal.tp1.persistence.EmpruntRepository;
import ca.cal.tp1.persistence.EmprunteurRepository;
import ca.cal.tp1.service.dto.EmpruntDTO;
import ca.cal.tp1.service.dto.EmprunteurDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprunteurService {
    private final EmprunteurRepository emprunteurDAO;
    private final EmpruntRepository empruntDAO;
    private final DocumentRepository documentDAO;

    public EmprunteurService(EmprunteurRepository emprunteurDAO, EmpruntRepository empruntDAO, DocumentRepository documentDAO) {
        this.emprunteurDAO = emprunteurDAO;
        this.empruntDAO = empruntDAO;
        this.documentDAO = documentDAO;
    }

    public Long ajouteEmprunteur(EmprunteurDTO dto) {
        Emprunteur emprunteur = new Emprunteur();
        emprunteur.setId(dto.id());
        emprunteur.setName(dto.name());
        emprunteur.setEmail(dto.email());
        emprunteur.setPhoneNumber(dto.phoneNumber());
        emprunteurDAO.save(emprunteur);
        return emprunteur.getId();
    }

    public EmprunteurDTO getEmprunteur(Long id) {
        Emprunteur emprunteur = emprunteurDAO.get(id);
        return emprunteur != null ? EmprunteurDTO.fromEntity(emprunteur) : null;
    }

    public List<EmpruntDTO> documentsEmprunter(Long emprunteurId, List<Long> documentIds) throws EmprunteurNotFoundException, DocumentNotFoundException, NoAvailableCopiesException, UnsupportedDocumentTypeException {
        Emprunteur emp = emprunteurDAO.get(emprunteurId);
        if (emp == null) {
            throw new EmprunteurNotFoundException("Emprunteur introuvable : " + emprunteurId);
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setEmprunteur(emp);
        emprunt = empruntDAO.saveEmprunt(emprunt);

        List<EmpruntDTO> empruntDTOs = new ArrayList<>();

        for (Long documentId : documentIds) {
            Document doc = documentDAO.get(documentId);
            if (doc == null) {
                throw new DocumentNotFoundException("Document introuvable : " + documentId);
            }

            Long dispo = documentDAO.getexemplairesDisponibles(doc.getDocumentID());
            if (dispo == null || dispo <= 0) {
                throw new NoAvailableCopiesException("Aucun exemplaire disponible pour : " + doc.getTitre());
            }

            EmpruntDetail empruntDetail = new EmpruntDetail();
            empruntDetail.setEmprunt(emprunt);
            empruntDetail.setDocument(doc);
            empruntDetail.setDateRetourPrevue(calculateReturnDate(doc));

            empruntDetail = empruntDAO.saveEmpruntDetail(empruntDetail);

            String status = empruntDAO.getEmpruntStatus(emprunt.getId());

            empruntDTOs.add(EmpruntDTO.fromEntity(
                    emprunt.getId(),
                    emprunt.getDateEmprunt().toString(),
                    empruntDetail.getDateRetourPrevue().toString(),
                    emp.getId(),
                    doc.getDocumentID(),
                    status
            ));
        }

        return empruntDTOs;
    }

    private LocalDate calculateReturnDate(Document doc) throws UnsupportedDocumentTypeException {
        LocalDate now = LocalDate.now();
        if (doc instanceof Livre) {
            return now.plusDays(21);
        } else if (doc instanceof CD) {
            return now.plusDays(14);
        } else if (doc instanceof DVD) {
            return now.plusDays(7);
        }
        throw new UnsupportedDocumentTypeException("Type de document non supporté : " + doc.getClass().getName());
    }
}