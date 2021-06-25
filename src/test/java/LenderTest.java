import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LenderTest {

    Lender lender;

    @BeforeEach
    public void setLender(){
        lender = new Lender();
    }

    @Test
    public void testGetAvailableFunds(){

        // SEAT

        // Setup (no action)

        // Execution (no action)

        // Assertion
        Assertions.assertEquals(0, lender.getAvailableFunds());

        // Teardown

    }

    @Test
    public void testAddAdditionalFunds() {

        // SEAT

        // Setup (no action)

        final double depositOne = 100;
        final double testTwo_depositOne = 50;
        final double testTwo_depositTwo = 200;
        // depositOne is already in Lender's Available funds
        final double testTwo_expected = (testTwo_depositOne + testTwo_depositTwo) + depositOne;

        // Execution (no action)
        lender.depositFunds(depositOne);

        // Assertion
        Assertions.assertEquals(depositOne, lender.getAvailableFunds());

        // Execution (no action)
        lender.depositFunds(testTwo_depositOne);
        lender.depositFunds(testTwo_depositTwo);

        // Assertion
        Assertions.assertEquals(testTwo_expected, lender.getAvailableFunds());

        // Teardown
    }
}
