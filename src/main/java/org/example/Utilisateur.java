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
    public Transaction effectuerAchat (Distributeur distributeur, Boisson boisson, int montant, int quantite) throws Exception {

        if (this.type == TypeUtilisateur.CLIENT) {
            return distributeur.acheterBoisson(boisson, quantite, montant);
        }
        else {
            throw new Exception("Cet utilisateur n'est pas autorisé à acheter cette boisson");

        }
    }

    public void rechargerDistributeur(Distributeur distributeur, Boisson boisson, int quantite) throws Exception{

        if (this.type == TypeUtilisateur.PERSONNEL) {
            distributeur.rechargerStock(boisson, quantite);
        }
        else{
            throw new Exception("L'utilisateur n'est pas autorisé à recharger le stock");
        }



    }
}
