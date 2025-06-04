import static org.junit.jupiter.api.Assertions.*;
import org.example.Boisson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BoissonTest {
    private Boisson boisson1;
    private Boisson boisson2;
    private Boisson boisson3;

    @BeforeEach
    void setUp() {
        boisson1 = new Boisson("B1", "coca", 350);
        boisson2 = new Boisson("B1", "coca", 350);
        boisson3 = new Boisson("B2", "fanta", 300);
    }

    @Test
    void testGetId() {
        assertEquals("B1", boisson1.getId());
    }

    @Test
    void testGetNom() {
        assertEquals("coca", boisson1.getNom());
    }

    @Test
    void testGetPrix() {
        assertEquals(350, boisson1.getPrix());
    }

    @Test
    void testEgaliteMemeBoisson() {
        assertTrue(boisson1.equals(boisson2));
    }

    @Test
    void testEgaliteDifferentBoisson() {
        assertFalse(boisson1.equals(boisson3));
    }
}