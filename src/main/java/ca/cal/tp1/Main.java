package ca.cal.tp1;

import ca.cal.tp1.modele.Emprunteur;
import ca.cal.tp1.persistence.EmprunteurDAO;
import ca.cal.tp1.persistence.IEmprunteurDAO;
import ca.cal.tp1.service.EmprunteurService;
import ca.cal.tp1.utils.TcpServer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        // Votre script qui utilise votre API ici
        TcpServer.startTcpServer();
        IEmprunteurDAO emprunteurDAO = new EmprunteurDAO();
        EmprunteurService emprunteurService = new EmprunteurService(emprunteurDAO);


        Emprunteur emprunteur = new Emprunteur(1, "LeonardoVinci", "LeonDivin@Tp1.com", "439-001-1122");
        emprunteurService.ajouteEmprunteur(emprunteur);
        System.out.println(" Emprunteur ajouté avec ID : " + emprunteur.getUserID());


        Emprunteur retrievedEmprunteur = emprunteurService.getEmprunteur(emprunteur.getUserID());
        if (retrievedEmprunteur != null) {
            System.out.println(" Emprunteur récupéré : " + retrievedEmprunteur.getName());
        } else {
            System.out.println(" Emprunteur non trouvé.");
        }

        Thread.currentThread().join();
    }
}

