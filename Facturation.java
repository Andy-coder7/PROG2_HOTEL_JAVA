package com.Hotel;

public class Facturation {
    private Reservation reservation;
    private double montantTotal;
    private ModePaiement modePaiement;
    private boolean paye;

    public Facturation(Client client, Reservation reservation, Chambre chambre) {
        this.reservation = reservation;
        this.montantTotal = reservation.calculePrixAPayer();
        this.paye = false;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public boolean isPaye() {
        return paye;
    }

    public void payer(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
        this.paye = true;
    }
}
