package org.example;

import java.util.Date;
import java.util.UUID; // Pour générer un ID unique

public class Transaction {
    private String id;
    private Date date;
    private double montantPaye;
    private Boisson boissonAchetee;
    private double monnaieRendue;

    public Transaction(double montantPaye, Boisson boissonAchetee, double monnaieRendue) {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.montantPaye = montantPaye;
        this.boissonAchetee = boissonAchetee;
        this.monnaieRendue = monnaieRendue;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public Boisson getBoissonAchetee() {
        return boissonAchetee;
    }

    public double getMonnaieRendue() {
        return monnaieRendue;
    }


}