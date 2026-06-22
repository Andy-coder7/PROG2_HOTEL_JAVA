package com.Hotel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nom;
    private String addresse;
    private String telephone;
    private String email;
    private List<Chambre> listeChambre;
     private List<Reservation> listeReservation;

    public Hotel(String nom, String addresse, String telephone, String email, List<Chambre> listeChambre) {
        this.nom = nom;
        this.addresse = addresse;
        this.telephone = telephone;
        this.email = email;
        this.listeChambre = listeChambre;
        this.listeReservation = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Chambre> getListeChambre() {
        return listeChambre;
    }

    public void setListeChambre(List<Chambre> listeChambre) {
        this.listeChambre = listeChambre;
    }

    public List<Reservation> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }

    public void ajouterReservation(Reservation reservation) {
        this.listeReservation.add(reservation);
    }

    public boolean annulerReservation(Reservation reservation) {
        return this.listeReservation.remove(reservation);
    }

    public List<Chambre> chambresLibres(int nombrePersonne, LocalDateTime debutResa, LocalDateTime finResa) {
        List<Chambre> resultat = new ArrayList<>();

        for (Chambre chambre : listeChambre) {
            if (chambre.getQuantitePersonne() < nombrePersonne) {
                continue;
            }

            boolean estLibre = true;
            for (Reservation reservation : listeReservation) {
                if (reservation.getChambre().equals(chambre) && reservation.chevauche(debutResa, finResa)) {
                    estLibre = false;
                    break;
                }
            }

            if (estLibre) {
                resultat.add(chambre);
            }
        }

        return resultat;
    }


}