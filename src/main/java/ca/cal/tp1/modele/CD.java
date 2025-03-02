package ca.cal.tp1.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CD extends Document {
    private String artiste;
    private int duree;
    private String genre;
}