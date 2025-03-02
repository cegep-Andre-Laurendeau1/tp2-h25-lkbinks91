package ca.cal.tp1.service;

import ca.cal.tp1.modele.Emprunteur;
import ca.cal.tp1.persistence.EmprunteurDAO;

public class EmprunteurService {
    private final EmprunteurDAO emprunteurDAO;

    public EmprunteurService(EmprunteurDAO emprunteurDAO) {
        this.emprunteurDAO = emprunteurDAO;
    }

    public void ajouteEmprunteur(Emprunteur emprunteur) {
        emprunteurDAO.save(emprunteur);
    }

    public Emprunteur getEmprunteur(int id) {
        return emprunteurDAO.getById(id);
    }
}
