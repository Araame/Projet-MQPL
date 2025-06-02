package org.example;


import java.util.Objects;

public class Boisson {
    private String id;
    private String nom;
    private int prix;


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

    public  int getPrix() {
        return prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boisson boisson = (Boisson) o;
        return Objects.equals(id, boisson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}