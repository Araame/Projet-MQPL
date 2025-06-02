package org.example;


public class Portefeuille {
    private int solde;

    public Portefeuille() {
        this.solde = 0;
    }

    public int getSolde() {
        return solde;
    }

    public  boolean ajouterMontant(int montant) {
        if (montant > 0) {
            this.solde += montant;
            System.out.println("Montant ajouté : " + String.format("%.2f", montant) + "Francs");
            return true;
        }
        System.out.println("Impossible d'ajouter un montant négatif ou nul.");
        return false;
    }


    public boolean retirerMontant(int montant) {
        if (montant > 0 && this.solde >= montant) {
            this.solde -= montant;
            System.out.println("Montant retiré : " + String.format("%.2f", montant) + "francs");
            return true;
        }
        System.out.println("Impossible de retirer : solde insuffisant ou montant invalide.");
        return false;
    }


    public int calculerMonnaie(int montantPaye, int prixArticle) {
        if (montantPaye >= prixArticle) {
            return montantPaye - prixArticle;
        }
        return 0;
    }
}
