package ca.cal.tp1.modele;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@DiscriminatorValue("Prepose")
@Data
@AllArgsConstructor
public class Prepose extends Utilisateur {
}