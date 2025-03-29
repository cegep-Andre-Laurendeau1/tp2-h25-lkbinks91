package ca.cal.tp1;

import ca.cal.tp1.exception.DocumentNotFoundException;
import ca.cal.tp1.exception.EmprunteurNotFoundException;
import ca.cal.tp1.exception.NoAvailableCopiesException;
import ca.cal.tp1.exception.UnsupportedDocumentTypeException;
import ca.cal.tp1.persistence.*;
import ca.cal.tp1.service.EmprunteurService;
import ca.cal.tp1.service.PreposeService;
import ca.cal.tp1.service.dto.*;
import ca.cal.tp1.utils.TcpServer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        TcpServer.startTcpServer();

        EmprunteurRepository emprunteurDAO = new EmprunteurDAO();
        EmpruntRepository empruntDAO = new EmpruntDAO();
        DocumentRepository documentDAO = new DocumentDAO();

        EmprunteurService emprunteurService = new EmprunteurService(emprunteurDAO, empruntDAO, documentDAO);
        PreposeService preposeService = new PreposeService(documentDAO);

        EmprunteurDTO nouvelEmprunteur = new EmprunteurDTO(
                0L,
                "LeonardoVinci",
                "LeonDivin@Tp1.com",
                "439-001-1122"
        );

        Long emprunteurId = emprunteurService.ajouteEmprunteur(nouvelEmprunteur);
        System.out.println(" Emprunteur ajouté avec ID : " + emprunteurId);

        EmprunteurDTO retrievedEmprunteur = emprunteurService.getEmprunteur(emprunteurId);
        if (retrievedEmprunteur != null) {
            System.out.println(" Emprunteur récupéré : " + retrievedEmprunteur.name());
        } else {
            System.out.println(" Emprunteur non trouvé.");
        }

        CDDTO cdDTO = new CDDTO(
                "Sticky fingers",
                "Rolling stones",
                60L,
                "Rock",
                5L
        );
        Long cdId = preposeService.ajouterDocument(cdDTO);

        Long disponible = documentDAO.getexemplairesDisponibles(cdId);
        System.out.println("Nombre d'exemplaires disponibles : " + disponible);

        LivreDTO livreDTO = new LivreDTO(
                "Le seigneur des anneaux",
                "978-2-266-11156-0",
                "J.R.R. Tolkien",
                "Christian Bourgois",
                1000L,
                2019L,
                3L
        );
        Long livreId =  preposeService.ajouterDocument(livreDTO);
        System.out.println("Livre ajouté avec ID : " + livreId);

        LivreDTO livre2DTO = new LivreDTO(
                "Le magicien du monde",
                "978-2-266-11156-1",
                "Magicien d'Oz",
                "Christian Bourgois",
                200L,
                2019L,
                20L
        );
        Long livre2Id = preposeService.ajouterDocument(livre2DTO);
        System.out.println("Livre ajouté avec ID : " + livre2Id);

        DVDDTO dvdDTO = new DVDDTO(
                "Peter Jackson",
                180L,
                "PG-13",
                "Harry Potter",
                "Daniel Radcliffe",
                15L
        );
        Long dvdId = preposeService.ajouterDocument(dvdDTO);

        Long disponible2 = documentDAO.getexemplairesDisponibles(dvdId);
        System.out.println("Nombre d'exemplaires disponibles : " + disponible2);

        List<LivreDTO> livresTitre = documentDAO.searchLivreByTitre("anneaux").stream()
                .map(LivreDTO::fromEntity)
                .toList();
        System.out.println("Recherche par titre avec mot-clé 'anneaux' : ");
        livresTitre.forEach(l -> System.out.println("Livre trouvé : " + l.titre()));

        List<LivreDTO> livresAuteur = documentDAO.searchLivreByAuteur("Oz").stream()
                .map(LivreDTO::fromEntity)
                .toList();
        System.out.println("Recherche par auteur avec mot-clé 'Oz' : ");
        livresAuteur.forEach(l -> System.out.println("Livre trouvé : " + l.titre()));

        List<LivreDTO> livresAnnee = documentDAO.searchLivreByAnnee(2019L).stream()
                .map(LivreDTO::fromEntity)
                .toList();
        System.out.println("Recherche par année 2019 : ");
        livresAnnee.forEach(l -> System.out.println("Livre trouvé : " + l.titre()));

        List<CDDTO> cdTitre = documentDAO.searchCDByTitre("Sticky").stream()
                .map(CDDTO::fromEntity)
                .toList();
        System.out.println("Recherche de CD par titre avec mot-clé 'Sticky' : ");
        cdTitre.forEach(c -> System.out.println("CD trouvé : " + c.titre()));

        List<CDDTO> cdArtiste = documentDAO.searchCDByArtiste("Stones").stream()
                .map(CDDTO::fromEntity)
                .toList();
        System.out.println("Recherche de CD par artiste avec mot-clé 'Stones' : ");
        cdArtiste.forEach(c -> System.out.println("CD trouvé : " + c.titre()));

        List<DVDDTO> dvdTitre = documentDAO.searchDVDByTitre("harry").stream()
                .map(DVDDTO::fromEntity)
                .toList();
        System.out.println("Recherche de DVD par titre avec mot-clé 'harry' : ");
        dvdTitre.forEach(d -> System.out.println("DVD trouvé : " + d.titre()));

        List<DVDDTO> dvdArtiste = documentDAO.searchDVDByArtiste("Radcliffe").stream()
                .map(DVDDTO::fromEntity)
                .toList();
        System.out.println("Recherche de DVD par artiste avec mot-clé 'Radcliffe' : ");
        dvdArtiste.forEach(d -> System.out.println("DVD trouvé : " + d.titre()));

        try {
            System.out.println("\nTest d'emprunt de plusieurs documents :");
            List<Long> documentIds = Arrays.asList(dvdId, cdId);
            List<EmpruntDTO> emprunts = emprunteurService.documentsEmprunter(emprunteurId, documentIds);
            System.out.println("Emprunteur : " + retrievedEmprunteur.name());
            emprunts.forEach(emprunt -> System.out.println("Emprunt effectué : " + emprunt));
        } catch (EmprunteurNotFoundException | DocumentNotFoundException | NoAvailableCopiesException |
                 UnsupportedDocumentTypeException e) {
            System.out.println("Erreur lors de l'emprunt multiple : " + e.getMessage());
        }


        Thread.currentThread().join();
    }
}