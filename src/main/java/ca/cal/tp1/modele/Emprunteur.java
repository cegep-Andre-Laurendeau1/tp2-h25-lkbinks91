package ca.cal.tp1.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Emprunteur extends Utilisateur {
    private List<Emprunt> emprunts = new ArrayList<>();
    private List<Amende> amendes = new ArrayList<>();

    public Emprunteur(int userID, String name, String email, String phoneNumber) {
        super(userID, name, email, phoneNumber);
    }
}