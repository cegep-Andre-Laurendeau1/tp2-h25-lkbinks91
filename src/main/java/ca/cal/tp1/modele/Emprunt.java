package ca.cal.tp1.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emprunt {
    private int borrowID;
    private Date dateEmprunt;
    private String status;

    private List<EmpruntDetail> items = new ArrayList<>();

}