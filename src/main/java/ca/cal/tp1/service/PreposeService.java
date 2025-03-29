package ca.cal.tp1.service;

import ca.cal.tp1.modele.CD;
import ca.cal.tp1.modele.DVD;
import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Livre;
import ca.cal.tp1.persistence.DocumentRepository;
import ca.cal.tp1.service.dto.CDDTO;
import ca.cal.tp1.service.dto.DVDDTO;
import ca.cal.tp1.service.dto.LivreDTO;

public class PreposeService {
    private final DocumentRepository documentDAO;

    public PreposeService(DocumentRepository documentDAO) {
        this.documentDAO = documentDAO;
    }

    public Long ajouterDocument(CDDTO cdDTO) {
        CD cd = new CD();
        cd.setTitre(cdDTO.titre());
        cd.setArtiste(cdDTO.artiste());
        cd.setDuree(cdDTO.duree());
        cd.setGenre(cdDTO.genre());
        cd.setNombreExemplaires(cdDTO.nombreExemplaires());
        documentDAO.save(cd);
        return cd.getDocumentID();
    }

    public Long ajouterDocument(LivreDTO livreDTO) {
        Livre livre = new Livre();
        livre.setISBN(livreDTO.ISBN());
        livre.setAuteur(livreDTO.auteur());
        livre.setEditeur(livreDTO.editeur());
        livre.setNombrePages(livreDTO.nombrePages());
        livre.setAnnee(livreDTO.annee());
        livre.setTitre(livreDTO.titre());
        livre.setNombreExemplaires(livreDTO.nombreExemplaires());
        documentDAO.save(livre);
        return livre.getDocumentID();
    }

    public Long ajouterDocument(DVDDTO dvdDTO) {
        DVD dvd = new DVD();
        dvd.setDirector(dvdDTO.director());
        dvd.setDuree(dvdDTO.duree());
        dvd.setRating(dvdDTO.rating());
        dvd.setTitre(dvdDTO.titre());
        dvd.setArtiste(dvdDTO.artiste());
        dvd.setNombreExemplaires(dvdDTO.nombreExemplaires());
        documentDAO.save(dvd);
        return dvd.getDocumentID();
    }

}
