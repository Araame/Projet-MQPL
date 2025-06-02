package org.example;


public class Boisson {
    private String id;
    private String nom;
    private int prix;
    private String description;

    public Boisson(String id, String nom, int prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;

    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }




}