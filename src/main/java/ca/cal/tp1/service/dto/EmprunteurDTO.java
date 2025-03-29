package ca.cal.tp1.service.dto;

import ca.cal.tp1.modele.Emprunteur;

public record EmprunteurDTO(Long id, String name, String email, String phoneNumber) {
    public static EmprunteurDTO fromEntity(Emprunteur emprunteur) {
        return new EmprunteurDTO(
                emprunteur.getId(),
                emprunteur.getName(),
                emprunteur.getEmail(),
                emprunteur.getPhoneNumber()
        );
    }
}