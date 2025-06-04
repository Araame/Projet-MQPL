import org.example.Boisson;
import org.example.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class StockTest {
    private Stock stock;
    private Boisson coca;
    private Boisson fanta;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        coca = new Boisson("B1", "coca", 350);
        fanta = new Boisson("B2", "fanta", 300);
    }

    @Test
    void testAjouterBoissons() {
        stock.ajouterBoissons(coca, 5);
        assertEquals(5, stock.getQuantite(coca));
    }

    @Test
    void testAjouterBoissonsExistantes() {
        stock.ajouterBoissons(coca, 5);
        stock.ajouterBoissons(coca, 3);
        assertEquals(8, stock.getQuantite(coca));
    }

    @Test
    void testRetirerBoissonsSuffisantes() throws Exception {
        stock.ajouterBoissons(coca, 5);
        stock.retirerBoissons(coca, 3);
        assertEquals(2, stock.getQuantite(coca));
    }

    @Test
    void testRetirerBoissonsInsuffisantes() {
        stock.ajouterBoissons(coca, 2);
        assertThrows(Exception.class, () -> {
            stock.retirerBoissons(coca, 3);
        });

    }

    @Test
    void testContientBoissonTrue() {
        stock.ajouterBoissons(coca, 5);
        assertTrue(stock.contientBoisson(coca));
    }

    @Test
    void testGetQuantite() {
        stock.ajouterBoissons(coca, 10);
        assertEquals(10, stock.getQuantite(coca));
    }

    @Test
    void testGetBoissonsDisponibles() {
        stock.ajouterBoissons(coca, 5);
        stock.ajouterBoissons(fanta, 3);
        Map<Boisson, Integer> boissonsDisponibles = stock.getBoissonsDisponibles();
        assertEquals(2, boissonsDisponibles.size());

        assertEquals(5, stock.getQuantite(coca));
    }
}
