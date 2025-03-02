package ca.cal.tp1.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    private int userID;
    private String name;
    private String email;
    private String phoneNumber;
}
