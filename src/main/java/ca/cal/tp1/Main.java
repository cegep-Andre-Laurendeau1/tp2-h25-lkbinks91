package ca.cal.tp1;

import ca.cal.tp1.modele.CD;
import ca.cal.tp1.modele.Emprunteur;
import ca.cal.tp1.persistence.DocumentDAO;
import ca.cal.tp1.persistence.EmprunteurDAO;
import ca.cal.tp1.persistence.IDocumentDAO;
import ca.cal.tp1.persistence.IEmprunteurDAO;
import ca.cal.tp1.service.EmprunteurService;
import ca.cal.tp1.service.PreposeService;
import ca.cal.tp1.utils.TcpServer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        // Votre script qui utilise votre API ici
        TcpServer.startTcpServer();
        IEmprunteurDAO emprunteurDAO = new EmprunteurDAO();
        EmprunteurService emprunteurService = new EmprunteurService(emprunteurDAO);


        Emprunteur emprunteur = new Emprunteur(0, "LeonardoVinci", "LeonDivin@Tp1.com", "439-001-1122");
        emprunteurService.ajouteEmprunteur(emprunteur);
        System.out.println(" Emprunteur ajouté avec ID : " + emprunteur.getUserID());


        Emprunteur retrievedEmprunteur = emprunteurService.getEmprunteur(emprunteur.getUserID());
        if (retrievedEmprunteur != null) {
            System.out.println(" Emprunteur récupéré : " + retrievedEmprunteur.getName());
        } else {
            System.out.println(" Emprunteur non trouvé.");
        }

        IDocumentDAO documentDAO = new DocumentDAO();
        PreposeService preposeService = new PreposeService(documentDAO);

        CD cd = new CD();
        cd.setTitre("Sticky fingers");
        cd.setArtiste("Rolling stones");
        cd.setDuree(60);
        cd.setGenre("Rock");
        cd.setNombreExemplaires(5);

        preposeService.ajouterDocument(cd);


        Thread.currentThread().join();
    }
}

