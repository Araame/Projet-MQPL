Feature: Distributeur de Boissons

  Scenario: Achat Réussi d'une Boisson par un Client
    Given un client a 500
    And la boisson "Coca" coûte 300 et est en stock
    When le client tente d'acheter la boisson "Coca"
    Then l'achat est accepté
    And le solde du client reste 200
    And le stock de "Coca" est réduit de 1

  Scenario: Achat avec Montant Insuffisant
    Given un client a 100
    And la boisson "Coca" coûte 300 et est en stock
    When le client tente d'acheter la boisson "Coca"
    Then l'achat est refusé
    And le solde du client reste 100
    And le stock de "Coca" reste inchangé

  Scenario: Achat d'une Boisson en Rupture de Stock
    Given un client a 500
    And la boisson "Coca" coûte 300 et est en rupture de stock
    When le client tente d'acheter la boisson "Coca"
    Then l'achat est refusé
    And le solde du client reste 500
    And le stock de "Coca" reste inchangé

  Scenario: Recharge du Stock par le Personnel
    Given le stock de "Coca" est à 2
    When le personnel recharge 3 unités de "Coca"
    Then le stock de "Coca" est de 5

  Scenario: Un Client Tente de Recharger le Stock (Échec)
    Given le stock de "Coca" est à 2
    When un client tente de recharger le stock de "Coca" avec 3 unités
    Then l'opération est refusée
    And le stock de "Coca" est de 2

  Scenario: Consultation des Boissons Disponibles
    Given plusieurs boissons sont en stock
    When un client consulte la liste des boissons
    Then la liste des boissons disponibles est affichée

  Scenario: Affichage du Journal des Ventes (Après Achat)
    Given une boisson "Coca" a été achetée
    When le personnel consulte le journal des ventes
    Then l'achat de "Coca" est listé dans le journal

  Scenario: Affichage du Journal des Ventes (Aucun Achat)
    Given aucune boisson n'a été achetée
    When le personnel consulte le journal des ventes
    Then le journal est vide

  Scenario: Achat d'une Boisson à Prix Exact
    Given un client a 3000
    And la boisson "Coca" coûte 300 et est en stock
    When le client tente d'acheter la boisson "Coca"
    Then l'achat est accepté
    And le solde du client reste 0
    And le stock de "Coca" est réduit de 1

  Scenario: Portefeuille du Distributeur après Multiples Ventes
    Given un client a 500
    And un client a 300
    And la boisson "Coca" coûte 300 et est en stock
    When le client tente d'acheter la boisson "Coca"
    And un autre client tente d'acheter la boisson "Coca"
    Then le portefeuille du distributeur contient 600

  Scenario: Tentative de Retrait de Montant Négatif du Portefeuille
    When le personnel tente de retirer -500 du portefeuille
    Then l'opération est refusée

  Scenario: Tentative d'Ajout de Montant Négatif au Portefeuille
    When le personnel tente d'ajouter -500 au portefeuille
    Then l'opération est refusée

  Scenario: Achat d'une Boisson après Déplétion Complète du Stock
    Given un client a 500
    And la boisson "Coca" coûte 3 euros et est en stock avec 1 unité
    When le client tente d'acheter la boisson "Coca"
    Then l'achat est accepté
    And le stock de "Coca" est de 0
    When un autre client tente d'acheter la boisson "Coca"
    Then l'achat est refusé

  Scenario: Recharge de Stock d'une Nouvelle Boisson
    Given la boisson "Pepsi" n'existe pas dans le stock
    When le personnel recharge 5 unités de "Pepsi"
    Then le stock de "Pepsi" est de 5


  Scenario: Vérification de la Consistance du Solde du Client après un Achat Échoué
    Given un client a 200
    And la boisson "Coca" coûte 300 et est en stock
    When le client tente d'acheter la boisson "Coca"
    Then l'achat est refusé
    And le solde du client reste 200
