package ca.cal.tp1.service;

import ca.cal.tp1.modele.Emprunteur;
import ca.cal.tp1.persistence.EmprunteurDAO;
import ca.cal.tp1.persistence.IEmprunteurDAO;

public class EmprunteurService {
    private final IEmprunteurDAO emprunteurDAO;

    public EmprunteurService(IEmprunteurDAO emprunteurDAO) {
        this.emprunteurDAO = emprunteurDAO;
    }

    public void ajouteEmprunteur(Emprunteur emprunteur) {
        emprunteurDAO.save(emprunteur);
    }

    public Emprunteur getEmprunteur(int id) {
        return emprunteurDAO.get(id);
    }
}
