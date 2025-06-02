package org.example;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static java.io.IO.print;

public class Distributeur {
    Stock stockBoissons;
    List<Transaction> transactions;
    Portefeuille portefeuille;
    public Distributeur(){
        this.stockBoissons = new Stock();
        this.portefeuille = new Portefeuille();
        this.transactions = new ArrayList<>();

    }

    //Methode pour consulter liste boissons
    public Map<Boisson, Integer> consulterBoisson(){
        Map<Boisson, Integer> boissons = Stock.getBoissonsDisponibles();
        if (boissons == null){
            System.out.println("Aucune boisson disponible !");
        }
        else {
            System.out.println("Voici les boissons disponibles :");
            return boissons;
        }

    }

    //Methode pour acheter une boisson
    public Transaction acheterBoisson(Boisson boisson, int quantite, int montantInseree){
        Integer cout = Boisson.getPrix();
        if (cout < montantInseree){
            System.out.println("Achat validé !");
            Stock.retirerBoissons(boisson, quantite);
            Portefeuille.ajouterMontant(cout);
            int monnaie = Portefeuille.calculerMonnaie(cout, montantInseree);
            Transaction transaction = new Transaction(cout, monnaie, montantInseree);
            transactions.add(transaction);
            return transaction;
        }
        else if (cout == montantInseree){
            System.out.println("Achat validé !");
            Stock.retirerBoissons(boisson, quantite);
            Portefeuille.ajouterMontant(cout);
            Transaction transaction = new Transaction(cout,montantInseree);
            transactions.add(transaction);
            return transaction;
        }
        else{
            System.out.println("Montant insuffisant !");
        }
    }

    public void rechargerStock(Boisson boisson, int quantite){
        Stock.ajouterBoisson(boisson,quantite);

    }

    public List<Transaction> afficherJournalVente(){
        if (transactions == null){
            System.out.println("Aucune transaction pour l'instant");
        }
        else {
            return new ArrayList<>(transactions);
        }
    }
}
