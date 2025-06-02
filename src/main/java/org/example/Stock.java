package org.example;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private static Map<Boisson, Integer> quantiteParBoisson;

    public Stock() {
        this.quantiteParBoisson = new HashMap<>();
    }



    public int getQuantite(Boisson boisson) {
        for (Map.Entry<Boisson, Integer> entry : quantiteParBoisson.entrySet()) {
            if (entry.getKey().equals(boisson)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    public void ajouterBoissons(Boisson boisson, int quantite) {
        int quantiteRestante = getQuantite(boisson);
        quantiteParBoisson.put(boisson, quantite + quantiteRestante);
    }

    public boolean retirerBoissons(Boisson boisson, int quantite){
        int quantiteRestante = getQuantite(boisson);
        quantiteParBoisson.put(boisson, quantiteRestante - quantite);
        return true;
    }

    public  Map<Boisson, Integer> getBoissonsDisponibles(){
        for (Map.entry)
    }
}
