package ca.cal.tp1.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livre extends Document {
    private String ISBN;
    private String auteur;
    private String editeur;
    private Long nombrePages;
}