package org.example;

import java.util.Date;
import java.util.UUID; // Pour générer un ID unique

public class Transaction {
    private String id;
    private Date date;
    private int montantPaye;
    private Boisson boissonAchetee;
    private int monnaieRendue;

    public Transaction(int montantPaye, Boisson boissonAchetee, int monnaieRendue) {
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

    public int getMontantPaye() {
        return montantPaye;
    }

    public Boisson getBoissonAchetee() {
        return boissonAchetee;
    }

    public int getMonnaieRendue() {
        return monnaieRendue;
    }


}