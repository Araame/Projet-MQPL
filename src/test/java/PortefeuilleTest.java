import org.example.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
public class PortefeuilleTest {
    private Portefeuille portefeuille;

    @BeforeEach
    void setUp() {
        portefeuille = new Portefeuille();
    }

    @Test
    void testPortefeuilleInitialSolde() {
        assertEquals(0, portefeuille.getSolde());
    }
    @Test
    void testAjouterMontant() throws Exception {

        portefeuille.ajouterMontant(200);
        assertEquals(200, portefeuille.getSolde());
    }

    @Test
    void testRetirerMontantSuffisant() throws Exception {
        portefeuille.ajouterMontant(200);
        portefeuille.retirerMontant(100);
        assertEquals(100, portefeuille.getSolde());

    }

    @Test
    void testRetirerMontantInsuffisant() throws Exception {
        portefeuille.ajouterMontant(200);
        assertThrows(Exception.class, () -> {
            portefeuille.retirerMontant(3000);
        });
    }

    @Test
    void testCalculerMonnaie() {
      assertEquals(100,portefeuille.calculerMonnaie(300,200));
    }
}
