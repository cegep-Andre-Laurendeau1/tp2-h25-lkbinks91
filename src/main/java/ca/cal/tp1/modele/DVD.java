package ca.cal.tp1.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DVD extends Document {
    private String director;
    private int duree;
    private String rating;
}
