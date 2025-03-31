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
@DiscriminatorValue("DVD")
public class DVD extends Document {
    private static final Long DUREE_EMPRUNT_JOURS = 7L;
    private String director;
    private Long duree;
    private String rating;
    private String artiste;

    @Override
    public Long calculerDureeEmprunt() {
        return DUREE_EMPRUNT_JOURS;
    }
}
