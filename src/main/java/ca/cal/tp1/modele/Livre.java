package ca.cal.tp1.modele;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Livre")
public class Livre extends Document {
    private static final Long DUREE_EMPRUNT_JOURS = 21L;
    private String ISBN;
    private String auteur;
    private String editeur;
    private Long nombrePages;
    private Long annee;
    private Long nombreExemplaires;

    @Override
    public Long calculerDureeEmprunt() {
        return DUREE_EMPRUNT_JOURS;
    }
}