package org.example;


public class Portefeuille {
    private int solde;

    public Portefeuille() {
        this.solde = 0;
    }

    public int getSolde() {
        return solde;
    }

    public  boolean ajouterMontant(int montant) throws Exception{
        if (montant <= 0) {
            throw new Exception("Impossible d'ajouter un montant négatif ou nul.");

        }
        this.solde += montant;
        System.out.println("Montant ajouté : " + montant+ "Francs");
        return true;

    }


    public boolean retirerMontant(int montant) throws Exception {
        if (montant <= 0) {
            throw new Exception("Impossible de retirer un montant négatif ou nul");

        }
        if (this.solde < montant) {

            throw new Exception("Impossible de retirer ce montant ,solde insuffisant");
        }
        this.solde -= montant;
        System.out.println("Montant retiré : " + montant + "Francs");
        return true;

    }


    public int calculerMonnaie(int montantPaye, int prixArticle) {
        if (montantPaye >= prixArticle) {
            return montantPaye - prixArticle;
        }
        return 0;
    }
}
