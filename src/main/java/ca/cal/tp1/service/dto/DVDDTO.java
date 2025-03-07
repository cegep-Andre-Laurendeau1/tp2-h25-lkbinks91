package ca.cal.tp1.service.dto;

import ca.cal.tp1.modele.DVD;

public record DVDDTO(String director, Long duree, String rating, String titre,  String artiste, Long nombreExemplaires) {
    public static DVDDTO fromEntity(DVD dvd) {
        return new DVDDTO(dvd.getDirector(), dvd.getDuree(), dvd.getRating(), dvd.getTitre(), dvd.getArtiste(), dvd.getNombreExemplaires());
    }
}
