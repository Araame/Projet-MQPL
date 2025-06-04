import org.example.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
public class UtilisateurTest {

    private Distributeur distributeur;
    private Boisson coca;
    private Boisson fanta;
    private Stock stock;
    private Portefeuille portefeuille;
    private Utilisateur client1;

    @BeforeEach
    void setUp(){
        stock = new Stock();
        portefeuille = new Portefeuille();
        distributeur = new Distributeur();

        coca = new Boisson("B1", "Coca-Cola", 350);
        fanta = new Boisson("B2", "Fanta", 300);
        distributeur.rechargerStock(coca, 5);
        distributeur.rechargerStock(fanta, 3);
        client1 =new Utilisateur("Momo", TypeUtilisateur.CLIENT);

    }

    @Test
    void testEffectuerAchatClientSuccess() throws Exception {
        Transaction transaction = client1.effectuerAchat(distributeur, coca, 2000, stock.getQuantite(coca));
        assertNotNull(transaction);
        assertEquals(coca, transaction.getBoissonAchetee());
    }

    @Test
    void testEffectuerAchatClientSoldeInsuffisant() throws Exception {
        assertThrows(Exception.class, () -> {
            client1.effectuerAchat(distributeur, coca, 100, 1);
        });
        assertEquals(5, stock.getQuantite(coca));
    }

    @Test
    void testEffectuerAchatNonPermis() throws Exception {
        Utilisateur personnel = new Utilisateur("Fallou", TypeUtilisateur.PERSONNEL);
        assertThrows(Exception.class, () -> {
            personnel.effectuerAchat(distributeur, coca, 1000, 1);
        });
    }

    @Test
    void testRechargerDistributeurPersonnelSucces() throws Exception {
        Utilisateur personnel = new Utilisateur("Fallou", TypeUtilisateur.PERSONNEL);
        personnel.rechargerDistributeur(distributeur, fanta, 10);
        assertEquals(13,stock.getQuantite(fanta));
    }

    @Test
    void testRechargerDistributeurClientNonPermis() throws Exception {
        assertThrows(Exception.class, () -> {
            client1.rechargerDistributeur(distributeur, coca, 3);
        });
        assertEquals(5,stock.getQuantite(coca));
    }
}
