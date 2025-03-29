package ca.cal.tp1.service.dto;

import ca.cal.tp1.modele.Livre;

public record LivreDTO(String titre, String ISBN, String auteur, String editeur, Long nombrePages, Long annee, Long nombreExemplaires) {
    public static LivreDTO fromEntity(Livre livre) {
        return new LivreDTO( livre.getTitre() ,livre.getISBN(), livre.getAuteur(), livre.getEditeur(), livre.getNombrePages(), livre.getAnnee(), livre.getNombreExemplaires());
    }

}
