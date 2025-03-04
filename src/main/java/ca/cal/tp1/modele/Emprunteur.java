package ca.cal.tp1.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints = {@jakarta.persistence.UniqueConstraint(columnNames = {"name", "email"})})
public class Emprunteur extends Utilisateur {
    @OneToMany(mappedBy = "emprunteur")
    private List<Emprunt> emprunts = new ArrayList<>();
    @OneToMany(mappedBy = "emprunteur")
    private List<Amende> amendes = new ArrayList<>();

    public Emprunteur(int userID, String name, String email, String phoneNumber) {
        super(userID, name, email, phoneNumber);
    }
}