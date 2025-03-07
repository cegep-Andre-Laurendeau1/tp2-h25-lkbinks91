package ca.cal.tp1.service.dto;

import ca.cal.tp1.modele.CD;

public record CDDTO(String titre, String artiste, Long duree, String genre, Long nombreExemplaires) {
    public static CDDTO fromEntity(CD cd) {
        return new CDDTO(cd.getTitre(), cd.getArtiste(), cd.getDuree(), cd.getGenre(), cd.getNombreExemplaires());
    }
}
