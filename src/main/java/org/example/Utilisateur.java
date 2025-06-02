package org.example;


import java.util.UUID;

public class Utilisateur {
    private String id;
    private String nom;
    private TypeUtilisateur type;

    public Utilisateur (String nom, TypeUtilisateur type){
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.type = type;
    }
    public Transaction effectuerAchat (Distributeur distributeur, Boisson boisson, int montant, int quantite){

        if (this.type == TypeUtilisateur.CLIENT) {
            return distributeur.acheterBoisson(boisson, quantite, montant);
        }
        else {
            System.out.println("Cet utilisateur n'est pas autorisé à acheter du café");
            return null;
        }
    }

    public void rechargerDistributeur(Distributeur distributeur, Boisson boisson, int quantite){

        if (this.type == TypeUtilisateur.PERSONNEL) {
            distributeur.rechargerStock(boisson, quantite);
            System.out.println(this.nom + " (" + this.type + ") a rechargé " + quantite + " unités de " + boisson.getNom() + " dans le distributeur " + ".");
        }
        else{
            System.out.println("Accès refusé : L'utilisateur '" + this.nom + "' (" + this.type + ") n'est pas autorisé à recharger les distributeurs.");
        }



    }
}
