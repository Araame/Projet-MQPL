package org.example;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Distributeur {
    private Stock stockBoissons;
    private List<Transaction> transactions;
    private Portefeuille portefeuille;

    public Distributeur(){
        this.stockBoissons = new Stock();
        this.portefeuille = new Portefeuille();
        this.transactions = new ArrayList<>();

    }

    //Methode pour consulter liste boissons
    public Map<Boisson, Integer> consulterBoissons(){
        Map<Boisson, Integer> boissons = stockBoissons.getBoissonsDisponibles();
        if (boissons == null){
            System.out.println("Aucune boisson disponible !");
        }

        return boissons;


    }

    //Methode pour acheter une boisson
    public Transaction acheterBoisson(Boisson boisson, int quantite, int montantInseree) throws Exception{
        if (!stockBoissons.contientBoisson(boisson) || stockBoissons.getQuantite(boisson) < quantite) {
            throw new Exception("Boisson non disponible ou en rupture de stock.");
        }
        int cout = boisson.getPrix();
        if (cout >= montantInseree){
            throw new Exception("Montant insuffisant !");
        }



        stockBoissons.retirerBoissons(boisson, quantite);
        portefeuille.ajouterMontant(cout);
        int monnaie = portefeuille.calculerMonnaie(cout, montantInseree);
        Transaction transaction = new Transaction( montantInseree,boisson, monnaie);
        transactions.add(transaction);
        return transaction;

    }

    public void rechargerStock(Boisson boisson, int quantite){
        stockBoissons.ajouterBoissons(boisson,quantite);

    }

    public List<Transaction> afficherJournalVentes(){
        if (transactions == null){
            System.out.println("Aucune transaction pour l'instant");
        }

        return new ArrayList<>(transactions);

    }
}
