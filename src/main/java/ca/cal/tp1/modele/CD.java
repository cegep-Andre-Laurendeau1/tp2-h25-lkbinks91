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
@DiscriminatorValue("CD")
public class CD extends Document {
    private static final Long DUREE_EMPRUNT_JOURS = 14L;
    private String artiste;
    private Long duree;
    private String genre;

    @Override
    public Long calculerDureeEmprunt() {
        return DUREE_EMPRUNT_JOURS;
    }
}