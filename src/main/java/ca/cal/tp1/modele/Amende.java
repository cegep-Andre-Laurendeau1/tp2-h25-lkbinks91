package ca.cal.tp1.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amende {
    private int fineID;
    private double montant;
    private Date dateCreation;
    private boolean status;
}