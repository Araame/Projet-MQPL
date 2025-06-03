package org.example;




public class Main {
    public static void main(String[] args) {
        // Création du distributeur
        Distributeur distributeur = new Distributeur();

        // Création de boissons
        Boisson cafe = new Boisson("B001", "Café", 100);
        Boisson the = new Boisson("B002", "Thé", 80);
        Boisson jus = new Boisson("B003", "Jus de fruits", 150);

        // Rechargement du stock par un utilisateur de type PERSONNEL
        Utilisateur personnel = new Utilisateur("Alice", TypeUtilisateur.PERSONNEL);
        personnel.rechargerDistributeur(distributeur, cafe, 10);
        personnel.rechargerDistributeur(distributeur, the, 5);
        personnel.rechargerDistributeur(distributeur, jus, 7);

        System.out.println("--- Stock après rechargement ---");
        distributeur.consulterBoissons().forEach((boisson, quantite) ->
                System.out.println(boisson.getNom() + ": " + quantite)
        );
        System.out.println("---------------------------------");


        // Création d'utilisateurs
        Utilisateur client1 = new Utilisateur("Bob", TypeUtilisateur.CLIENT);
        Utilisateur client2 = new Utilisateur("Charlie", TypeUtilisateur.CLIENT);

        // Achats par les clients
        System.out.println("\n--- Achats des clients ---");
        client1.effectuerAchat(distributeur, cafe, 200, 1); // Achat réussi
        client2.effectuerAchat(distributeur, the, 50, 1); // Montant insuffisant
        client1.effectuerAchat(distributeur, jus, 150, 2); // Quantité insuffisante ou boisson non disponible
        client2.effectuerAchat(distributeur, cafe, 100, 1); // Achat réussi

        System.out.println("\n--- Stock après achats ---");
        distributeur.consulterBoissons().forEach((boisson, quantite) ->
                System.out.println(boisson.getNom() + ": " + quantite)
        );
        System.out.println("----------------------------");


        // Affichage du journal des ventes
        System.out.println("\n--- Journal des ventes ---");
        if (distributeur.afficherJournalVentes().isEmpty()) {
            System.out.println("Aucune transaction pour l'instant.");
        } else {
            distributeur.afficherJournalVentes().forEach(transaction -> {
                System.out.println("ID Transaction: " + transaction.getId());
                System.out.println("Date: " + transaction.getDate());
                System.out.println("Boisson achetée: " + transaction.getBoissonAchetee().getNom());
                System.out.println("Montant payé: " + transaction.getMontantPaye());
                System.out.println("Monnaie rendue: " + transaction.getMonnaieRendue());
                System.out.println("---");
            });
        }
    }
}