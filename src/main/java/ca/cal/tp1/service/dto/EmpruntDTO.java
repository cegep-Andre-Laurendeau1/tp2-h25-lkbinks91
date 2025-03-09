package ca.cal.tp1.service.dto;

public record EmpruntDTO(Long id, String dateEmprunt, String dateRetour, Long idEmprunteur, Long idDocument) {
    public static EmpruntDTO fromEntity(Long id, String dateEmprunt, String dateRetour, Long idEmprunteur, Long idDocument) {
        return new EmpruntDTO(id, dateEmprunt, dateRetour, idEmprunteur, idDocument);
    }
}
