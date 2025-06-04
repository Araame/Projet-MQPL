package org.example;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{


        Boisson cafe = new Boisson("B001", "Café", 200);
        Boisson the = new Boisson("B002", "Thé", 150);
        Boisson eau = new Boisson("B003", "Eau", 100);


        Distributeur monDistributeur = new Distributeur();


        Utilisateur client1 = new Utilisateur("Alice", TypeUtilisateur.CLIENT);
        Utilisateur personnel1 = new Utilisateur("Bob", TypeUtilisateur.PERSONNEL);


        System.out.println("--- Rechargement du stock par le personnel ---");
        personnel1.rechargerDistributeur(monDistributeur, cafe, 10);
        personnel1.rechargerDistributeur(monDistributeur, the, 5);
        personnel1.rechargerDistributeur(monDistributeur, eau, 20);
        System.out.println("---------------------------------------------\n");


        System.out.println("--- Boissons disponibles à la consultation ---");
        Map<Boisson, Integer> boissonsDisponibles = monDistributeur.consulterBoissons();
        boissonsDisponibles.forEach((boisson, quantite) ->
                System.out.println("- " + boisson.getNom() + " : " + quantite + " unités (Prix : " + boisson.getPrix() + " F CFA)"));
        System.out.println("----------------------------------------------\n");


        System.out.println("--- Tentative d'achat par le client ---");
        Transaction transaction1 = null;
        try {

            transaction1 = client1.effectuerAchat(monDistributeur, cafe, 500, 1);
            if (transaction1 != null) {
                System.out.println("Achat de " + transaction1.getBoissonAchetee().getNom() + " réussi ! Monnaie rendue : " + transaction1.getMonnaieRendue() + " F CFA.");
            }
        } catch (Exception e) {

            System.out.println("Échec de l'achat : " + e.getMessage());
        }
        System.out.println("-------------------------------------\n");
    }

}