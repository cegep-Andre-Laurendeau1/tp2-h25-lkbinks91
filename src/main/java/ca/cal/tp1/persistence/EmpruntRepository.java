package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.EmpruntDetail;

public interface EmpruntRepository {

    Emprunt saveEmprunt(Emprunt emprunt);

    EmpruntDetail saveEmpruntDetail(EmpruntDetail detail);


    String getEmpruntStatus(Long empruntId);
}
