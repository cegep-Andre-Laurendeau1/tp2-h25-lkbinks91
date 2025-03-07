package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunteur;

public interface IEmprunteurDAO {
    void save(Emprunteur emprunteur);
    Emprunteur get(Long id);
}
