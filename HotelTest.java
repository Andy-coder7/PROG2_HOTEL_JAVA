package com.Hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelProjectTest {

    private Client client;
    private TypeChambre typeStandard;
    private TypeChambre typeSuite;
    private Chambre chambre1;
    private Chambre chambre2;
    private Chambre chambre3;
    private Hotel hotel;
    private LocalDateTime debut;
    private LocalDateTime fin;

    @BeforeEach
    void setUp() {
        client = new Client("Jean", "Dupont", "0341234567", "1234567890123456", "Antananarivo", "CIN123");

        typeStandard = new TypeChambre("Standard", 2, 1);
        typeSuite = new TypeChambre("Suite", 4, 2);

        chambre1 = new Chambre(1, true, 100.0, typeStandard, 2);
        chambre2 = new Chambre(2, true, 150.0, typeStandard, 2);
        chambre3 = new Chambre(3, true, 300.0, typeSuite, 4);

        List<Chambre> listeChambre = new ArrayList<>(List.of(chambre1, chambre2, chambre3));
        hotel = new Hotel("Hotel Test", "Adresse Test", "0340000000", "test@hotel.com", listeChambre);

        debut = LocalDateTime.of(2026, 7, 1, 14, 0);
        fin = LocalDateTime.of(2026, 7, 5, 11, 0);
    }


    @Test
    void calculePrixAPayer_PlusieursNuits() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        assertEquals(400.0, reservation.calculePrixAPayer());
    }

    @Test
    void calculePrixAPayer_uneSeuleNuit() {
        Reservation reservation = new Reservation(client, chambre1, LocalDateTime.of(2026, 7, 1, 14, 0),             LocalDateTime.of(2026, 7, 2, 11, 0));
        assertEquals(100.0, reservation.calculePrixAPayer());
    }


    @Test
    void chevauche_PeriodesIdentiques() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        assertTrue(reservation.chevauche(debut, fin));
    }

    @Test
    void chevauche_PeriodesPartiellementCroisees() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        LocalDateTime nouveauDebut = LocalDateTime.of(2026, 7, 3, 14, 0);
        LocalDateTime nouveauFin = LocalDateTime.of(2026, 7, 10, 11, 0);
        assertTrue(reservation.chevauche(nouveauDebut, nouveauFin));
    }



    @Test
    void chevauche_PeriodeEnglobee() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        LocalDateTime nouveauDebut = LocalDateTime.of(2026, 7, 2, 14, 0);
        LocalDateTime nouveauFin = LocalDateTime.of(2026, 7, 3, 11, 0);
        assertTrue(reservation.chevauche(nouveauDebut, nouveauFin));
    }


    @Test
    void chevauche_PeriodeApres() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        LocalDateTime nouveauDebut = LocalDateTime.of(2026, 8, 1, 14, 0);
        LocalDateTime nouveauFin = LocalDateTime.of(2026, 8, 5, 11, 0);
        assertFalse(reservation.chevauche(nouveauDebut, nouveauFin));
    }

    @Test
    void chevauche_PeriodeContigueSansChevauchement() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        LocalDateTime nouveauDebut = fin;
        LocalDateTime nouveauFin = LocalDateTime.of(2026, 7, 10, 11, 0);
        assertFalse(reservation.chevauche(nouveauDebut, nouveauFin));
    }


    @Test
    void ajouterReservation_ajouteBienALaListe() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        hotel.ajouterReservation(reservation);

        assertEquals(1, hotel.getListeReservation().size());
        assertTrue(hotel.getListeReservation().contains(reservation));
    }


    @Test
    void annulerReservation_retourneVraiEtRetireDeLaListe() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        hotel.ajouterReservation(reservation);

        boolean resultat = hotel.annulerReservation(reservation);

        assertTrue(resultat);
        assertTrue(hotel.getListeReservation().isEmpty());
    }

    @Test
    void annulerReservation_retourneFauxQuandReservationInexistante() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        // jamais ajoutée
        boolean resultat = hotel.annulerReservation(reservation);
        assertFalse(resultat);
    }

    @Test
    void annulerReservation_libereLaChambrePourChambresLibres() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        hotel.ajouterReservation(reservation);

        assertFalse(hotel.chambresLibres(2, debut, fin).contains(chambre1));

        hotel.annulerReservation(reservation);

        assertTrue(hotel.chambresLibres(2, debut, fin).contains(chambre1));
    }


    @Test
    void chambresLibres_retourneToutesLesChambresSansReservation() {
        List<Chambre> resultat = hotel.chambresLibres(2, debut, fin);
        assertEquals(3, resultat.size());
    }

    @Test
    void chambresLibres_excluLaChambreReservee() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        hotel.ajouterReservation(reservation);

        List<Chambre> resultat = hotel.chambresLibres(2, debut, fin);

        assertFalse(resultat.contains(chambre1));
        assertTrue(resultat.contains(chambre2));
    }

    @Test
    void chambresLibres_filtreParCapacitePersonnes() {
        List<Chambre> resultat = hotel.chambresLibres(4, debut, fin);

        assertEquals(1, resultat.size());
        assertTrue(resultat.contains(chambre3));
    }


    @Test
    void chambresLibres_avecPlusieursReservationsSimultanees() {
        Reservation reservation1 = new Reservation(client, chambre1, debut, fin);
        Reservation reservation2 = new Reservation(client, chambre2,
                LocalDateTime.of(2026, 7, 2, 14, 0),
                LocalDateTime.of(2026, 7, 3, 11, 0));
        hotel.ajouterReservation(reservation1);
        hotel.ajouterReservation(reservation2);

        List<Chambre> resultat = hotel.chambresLibres(2, debut, fin);

        assertFalse(resultat.contains(chambre1));
        assertFalse(resultat.contains(chambre2));
        assertTrue(resultat.contains(chambre3));
    }


    @Test
    void facturation_calculeLeMontantTotalALaCreation() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        Facturation facturation = new Facturation(client, reservation, chambre1);
        // 4 nuits * 100.0 = 400.0
        assertEquals(400.0, facturation.getMontantTotal());
    }

    @Test
    void facturation_nonPayeeParDefaut() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        Facturation facturation = new Facturation(client, reservation, chambre1);

        assertFalse(facturation.isPaye());
        assertNull(facturation.getModePaiement());
    }

    @Test
    void facturation_payerParCarte() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        Facturation facturation = new Facturation(client, reservation, chambre1);

        facturation.payer(ModePaiement.CARTE);

        assertTrue(facturation.isPaye());
        assertEquals(ModePaiement.CARTE, facturation.getModePaiement());
    }

    @Test
    void facturation_payerParEspece() {
        Reservation reservation = new Reservation(client, chambre1, debut, fin);
        Facturation facturation = new Facturation(client, reservation, chambre1);

        facturation.payer(ModePaiement.ESPECE);

        assertTrue(facturation.isPaye());
        assertEquals(ModePaiement.ESPECE, facturation.getModePaiement());
    }


    @Test
    void modePaiement_contientCarteEtEspece() {
        assertEquals(2, ModePaiement.values().length);
        assertNotNull(ModePaiement.valueOf("CARTE"));
        assertNotNull(ModePaiement.valueOf("ESPECE"));
    }

}