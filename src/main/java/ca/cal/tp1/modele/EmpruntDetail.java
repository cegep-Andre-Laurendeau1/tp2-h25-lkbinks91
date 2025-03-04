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
public class EmpruntDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lineItemID;
    private Date dateRetourPrevue;
    private Date dateRetourActuelle;
    private String status;

    @ManyToOne
    private Emprunt emprunt;


    @ManyToOne
    private Document document;
}