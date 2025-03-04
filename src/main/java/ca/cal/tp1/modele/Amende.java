package ca.cal.tp1.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amende {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fineID;
    private double montant;
    private Date dateCreation;
    private boolean status;
    @ManyToOne
    private Emprunteur emprunteur;
}