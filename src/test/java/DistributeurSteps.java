import io.cucumber.java8.En;

import java.util.*;

public class DistributeurSteps implements En {

    private static class Client {
        int solde;

        Client(int solde) {
            this.solde = solde;
        }
    }

    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, Integer> prix = new HashMap<>();
    private final List<String> journalVentes = new ArrayList<>();
    private final Map<String, Integer> stockAvantAchat = new HashMap<>();
    private final List<Client> clients = new ArrayList<>();
    private Client clientActuel;
    private boolean dernierAchatAccepte = false;
    private int portefeuilleDistributeur = 0;
    private boolean derniereOperationRefusee = false;

    public DistributeurSteps() {

        Given("un client a {int}", (Integer montant) -> {
            Client client = new Client(montant);
            clients.add(client);
            clientActuel = client;
        });

        When("un autre client tente d'acheter la boisson {string}", (String boisson) -> {
            if (clients.size() < 2) {
                throw new AssertionError("Il faut deux clients pour ce test.");
            }
            clientActuel = clients.get(1);
            acheterBoisson(boisson);
        });

        And("la boisson {string} coûte {int} et est en stock", (String boisson, Integer prixBoisson) -> {
            prix.put(boisson, prixBoisson);
            stock.put(boisson, stock.getOrDefault(boisson, 1));
        });

        And("la boisson {string} coûte {int} euros et est en stock avec {int} unité", (String boisson, Integer prixBoisson, Integer qte) -> {
            prix.put(boisson, prixBoisson);
            stock.put(boisson, qte);
        });

        Given("la boisson {string} coûte {int} et est en rupture de stock", (String boisson, Integer prixBoisson) -> {
            prix.put(boisson, prixBoisson);
            stock.put(boisson, 0);
        });

        Given("le stock de {string} est à {int}", (String boisson, Integer qte) -> {
            stock.put(boisson, qte);
        });

        Given("plusieurs boissons sont en stock", () -> {
            stock.put("Coca", 3);
            prix.put("Coca", 3);
            stock.put("Pepsi", 2);
            prix.put("Pepsi", 2);
        });

        Given("une boisson {string} a été achetée", (String boisson) -> {
            journalVentes.add(boisson);
        });

        Given("aucune boisson n'a été achetée", () -> {
            journalVentes.clear();
        });

        Given("la boisson {string} n'existe pas dans le stock", (String boisson) -> {
            stock.remove(boisson);
            prix.remove(boisson);
        });

        When("le client tente d'acheter la boisson {string}", (String boisson) -> {
            stockAvantAchat.put(boisson, stock.getOrDefault(boisson, 0));
            acheterBoisson(boisson);
        });

        When("le personnel recharge {int} unités de {string}", (Integer qte, String boisson) -> {
            stock.put(boisson, stock.getOrDefault(boisson, 0) + qte);
            derniereOperationRefusee = false;
        });

        When("un client tente de recharger le stock de {string} avec {int} unités", (String boisson, Integer qte) -> {

            derniereOperationRefusee = true;
        });

        When("un client consulte la liste des boissons", () -> {

        });

        When("le personnel consulte le journal des ventes", () -> {

        });

        When("le personnel tente de retirer {int} du portefeuille", (Integer montant) -> {
            if (montant < 0 || montant > portefeuilleDistributeur) {
                derniereOperationRefusee = true;
            } else {
                portefeuilleDistributeur -= montant;
                derniereOperationRefusee = false;
            }
        });

        When("le personnel tente d'ajouter {int} au portefeuille", (Integer montant) -> {
            if (montant < 0) {
                derniereOperationRefusee = true;
            } else {
                portefeuilleDistributeur += montant;
                derniereOperationRefusee = false;
            }
        });

        Then("l'achat est accepté", () -> {
            if (!dernierAchatAccepte) throw new AssertionError("Achat attendu comme accepté mais a été refusé");
        });

        Then("l'achat est refusé", () -> {
            if (dernierAchatAccepte) throw new AssertionError("Achat attendu comme refusé mais a été accepté");
        });

        Then("le solde du client reste {int}", (Integer soldeAttendu) -> {
            if (clientActuel.solde != soldeAttendu) {
                throw new AssertionError("Solde attendu: " + soldeAttendu + " mais obtenu: " + clientActuel.solde);
            }
        });

        Then("le stock de {string} est réduit de 1", (String boisson) -> {
            int ancien = stockAvantAchat.getOrDefault(boisson, -1);
            int actuel = stock.getOrDefault(boisson, -1);
            if (ancien - 1 != actuel) {
                throw new AssertionError("Stock non réduit correctement pour " + boisson);
            }
        });

        Then("le stock de {string} reste inchangé", (String boisson) -> {
            int ancien = stockAvantAchat.getOrDefault(boisson, -1);
            int actuel = stock.getOrDefault(boisson, -1);
            if (ancien != actuel) {
                throw new AssertionError("Le stock de " + boisson + " a changé alors qu'il devait rester inchangé.");
            }
        });

        Then("le stock de {string} est de {int}", (String boisson, Integer attendu) -> {
            if (!stock.getOrDefault(boisson, 0).equals(attendu)) {
                throw new AssertionError("Stock attendu: " + attendu + " mais obtenu: " + stock.getOrDefault(boisson, 0));
            }
        });

        Then("la liste des boissons disponibles est affichée", () -> {
            if (stock.isEmpty()) throw new AssertionError("Aucune boisson disponible");
        });

        Then("l'achat de {string} est listé dans le journal", (String boisson) -> {
            if (!journalVentes.contains(boisson)) {
                throw new AssertionError("La boisson " + boisson + " est absente du journal");
            }
        });

        Then("le journal est vide", () -> {
            if (!journalVentes.isEmpty()) throw new AssertionError("Journal attendu vide mais contient : " + journalVentes);
        });

        Then("le portefeuille du distributeur contient {int}", (Integer montant) -> {
            if (portefeuilleDistributeur != montant) {
                throw new AssertionError("Portefeuille attendu: " + montant + " mais obtenu: " + portefeuilleDistributeur);
            }
        });

        Then("l'opération est refusée", () -> {
            if (!derniereOperationRefusee) {
                throw new AssertionError("L'opération devait être refusée mais a été acceptée");
            }
        });
    }

    private void acheterBoisson(String boisson) {
        Integer p = prix.get(boisson);
        Integer s = stock.getOrDefault(boisson, 0);
        if (p == null || s == 0 || clientActuel.solde < p) {
            dernierAchatAccepte = false;
        } else {
            dernierAchatAccepte = true;
            clientActuel.solde -= p;
            stock.put(boisson, s - 1);
            portefeuilleDistributeur += p;
            journalVentes.add(boisson);
        }
    }
}
