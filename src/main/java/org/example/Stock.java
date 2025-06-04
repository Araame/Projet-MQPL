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
        int quantiteActuelle = getQuantite(boisson);
        quantiteParBoisson.put(boisson, quantite + quantiteActuelle);
    }

    public void retirerBoissons(Boisson boisson, int quantite) throws Exception{
        if (!contientBoisson(boisson)) {
            throw new Exception("La boisson  n'est pas présente dans le stock");
        }

        int quantiteActuelle = getQuantite(boisson);
        if (quantiteActuelle < quantite) {
            throw new Exception("Stock insuffisant");
        }
        quantiteParBoisson.put(boisson, quantiteActuelle - quantite);

    }

    public Map<Boisson, Integer> getBoissonsDisponibles() {
        return new HashMap<>(this.quantiteParBoisson);
    }
    public boolean contientBoisson(Boisson boisson) {
        return quantiteParBoisson.containsKey(boisson);
    }
}
