package com.Hotel;

public class TypeChambre {
        private String nom;
        private int nombreLit;
        private int nombreDouche;

    public TypeChambre(String nom, int nombreLit, int nombreDouche) {
        this.nom = nom;
        this.nombreLit = nombreLit;
        this.nombreDouche = nombreDouche;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreLit() {
        return nombreLit;
    }

    public void setNombreLit(int nombreLit) {
        this.nombreLit = nombreLit;
    }

    public int getNombreDouche() {
        return nombreDouche;
    }

    public void setNombreDouche(int nombreDouche) {
        this.nombreDouche = nombreDouche;
    }
}
