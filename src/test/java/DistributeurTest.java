import org.example.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class DistributeurTest {

    private Distributeur distributeur;
    private Boisson coca;
    private Boisson fanta;
    private Boisson eau;

    @BeforeEach
    void setUp() {
        distributeur = new Distributeur();
        coca = new Boisson("B1", "Coca-Cola", 350);
        fanta = new Boisson("B2", "Fanta", 300);
        eau = new Boisson("3", "Eau", 200);
    }

    @Test
    void testConsulterBoissonsApresStockage() {
        distributeur.rechargerStock(coca, 5);
        distributeur.rechargerStock(fanta, 3);

        Map<Boisson, Integer> boissonsDisponibles = distributeur.consulterBoissons();
        assertNotNull(boissonsDisponibles);
        assertEquals(2, boissonsDisponibles.size());
        assertEquals(5, boissonsDisponibles.get(coca));
    }

    @Test
    void testAcheterBoissonSuccess() throws Exception {
        distributeur.rechargerStock(coca, 2);
        Transaction transaction = distributeur.acheterBoisson(coca, 1,400);
        assertNotNull(transaction);
        assertEquals(coca, transaction.getBoissonAchetee());

        Map<Boisson, Integer> stockApresAchat = distributeur.consulterBoissons();
        assertEquals(1, stockApresAchat.get(coca));
    }

    @Test
    void testAcheterBoissonMontantInsuffisant() throws Exception {
        distributeur.rechargerStock(coca, 1);
        assertThrows(Exception.class, () -> {
            distributeur.acheterBoisson(coca, 1, 200);
        });
    }

    @Test
    void testAcheterBoissonRuptureDeStock() throws Exception{
        distributeur.rechargerStock(fanta, 1);

        assertThrows(Exception.class, () -> {
            distributeur.acheterBoisson(fanta, 2    , 300);
        });
    }

    @Test
    void testAcheterBoissonNonExistante() throws Exception {
        assertThrows(Exception.class, () -> {
            distributeur.acheterBoisson(eau, 1, 200);
        });
    }

    @Test
    void testRechargerStock() {
        distributeur.rechargerStock(eau, 10);
        Map<Boisson, Integer> boissonsApresRecharge = distributeur.consulterBoissons();
        assertTrue(boissonsApresRecharge.containsKey(eau));
    }

    @Test
    void testAfficherJournalVentesApresAchat() throws Exception {
        distributeur.rechargerStock(coca, 2);
        distributeur.rechargerStock(fanta, 1);

        distributeur.acheterBoisson(coca, 1, 500);
        distributeur.acheterBoisson(fanta, 1, 1000);

        List<Transaction> journalVentes = distributeur.afficherJournalVentes();
        assertNotNull(journalVentes);
        assertEquals(2, journalVentes.size());
    }

}
