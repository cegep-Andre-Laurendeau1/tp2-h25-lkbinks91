package ca.cal.tp1;

import ca.cal.tp1.modele.CD;
import ca.cal.tp1.modele.DVD;
import ca.cal.tp1.modele.Emprunteur;
import ca.cal.tp1.modele.Livre;
import ca.cal.tp1.persistence.DocumentDAO;
import ca.cal.tp1.persistence.EmprunteurDAO;
import ca.cal.tp1.persistence.IDocumentDAO;
import ca.cal.tp1.persistence.IEmprunteurDAO;
import ca.cal.tp1.service.EmprunteurService;
import ca.cal.tp1.service.PreposeService;
import ca.cal.tp1.utils.TcpServer;

import java.sql.SQLException;
import java.util.List;

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

        Livre livre = new Livre();
        livre.setISBN("978-2-266-11156-0");
        livre.setAuteur("J.R.R. Tolkien");
        livre.setEditeur("Christian Bourgois");
        livre.setNombrePages(1000L);
        livre.setAnnee(2012L);
        livre.setTitre("Le seigneur des anneaux");

        preposeService.ajouterDocument(livre);

        DVD dvd = new DVD();
        dvd.setDirector("Peter Jackson");
        dvd.setDuree(180);
        dvd.setRating("PG-13");

        preposeService.ajouterDocument(dvd);

        List<Livre> found = documentDAO.searchLivreByTitre("anneaux");
        System.out.println("recherche 'anneaux' : ");
        for (Livre l : found) {
            System.out.println("Livre trouvé : " + l.getTitre());
        }






        Thread.currentThread().join();
    }
}

