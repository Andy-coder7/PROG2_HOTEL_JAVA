package com.Hotel;

import java.util.Objects;

public class Chambre {
    private int id;
    private boolean statut;
    private double prix;
    private TypeChambre typeChambre;
    private int quantitePersonne;

    public Chambre(int id, boolean statut, double prix, TypeChambre typeChambre, int quantitePersonne) {
        this.id = id;
        this.statut = statut;
        this.prix = prix;
        this.typeChambre = typeChambre;
        this.quantitePersonne = quantitePersonne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public int getQuantitePersonne() {
        return quantitePersonne;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chambre chambre = (Chambre) o;
        return id == chambre.id && statut == chambre.statut && Double.compare(prix, chambre.prix) == 0 && typeChambre == chambre.typeChambre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statut, prix, typeChambre);
    }
}