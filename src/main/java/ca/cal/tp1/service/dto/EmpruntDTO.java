package ca.cal.tp1.service.dto;

public record EmpruntDTO(Long id, String dateEmprunt, String dateRetour, Long idEmprunteur, Long idDocument) {
    public static EmpruntDTO fromEntity(Long id, String dateEmprunt, String dateRetour, Long idEmprunteur, Long idDocument) {
        return new EmpruntDTO(id, dateEmprunt, dateRetour, idEmprunteur, idDocument);
    }
    @Override
    public String toString() {
        return "EmpruntDTO{" +
                "id=" + id +
                ", dateEmprunt='" + dateEmprunt + '\'' +
                ", dateRetour='" + dateRetour + '\'' +
                ", idEmprunteur=" + idEmprunteur +
                ", idDocument=" + idDocument +
                '}';
    }
}
