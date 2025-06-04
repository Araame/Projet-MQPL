import org.example.*;
import org.junit.jupiter.api.*;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
public class TransactionTest {
    private Boisson fanta;

    @BeforeEach
    void setUp(){
        fanta = new Boisson("B1","Fanta",300);
    }

    @Test
    void testVerificationInfoTransaction(){
        int montantPaye = 500;
        int monnaieRendue = 50;

        Transaction transaction = new Transaction(montantPaye, fanta, monnaieRendue);
        assertNotNull(transaction.getId());
        assertNotNull(transaction.getDate());
        assertTrue(transaction.getDate() instanceof Date);
        assertEquals(montantPaye, transaction.getMontantPaye());
        assertEquals(fanta, transaction.getBoissonAchetee());
        assertEquals(monnaieRendue, transaction.getMonnaieRendue());
    }

}
