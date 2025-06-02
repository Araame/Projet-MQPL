package org.example;


import java.util.Objects;

public class Boisson {
    private String id;
    private String nom;
    private double prix;
    private String description;

    public Boisson(String id, String nom, double prix) {
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

    public double getPrix() {
        return prix;
    }




}