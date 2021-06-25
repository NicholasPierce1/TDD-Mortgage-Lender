import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        // Teardown (no action)
    }

    @Test
    public void testGetLoanApprovalStatus(){

        // SEAT

        // Setup (no action)

        // enumerates input fields (loan amount & available funds)

        // test 1 (approved by margin)
        final double testOneLoanAmount = 125000;
        final double testOneAvailableFunds = 200000;
        final Lender.LoanStatus testOneLoanStatus = Lender.LoanStatus.APPROVED;

        // Execution
        lender.depositFunds(testOneAvailableFunds);

        // Assertion
        assertEquals(testOneLoanStatus, lender.getLoanApprovalStatus(testOneLoanAmount));

        // Termination (no action)

        // test 2 (rejected by margin)
        final double testTwoLoanAmount = 250000;
        final Lender.LoanStatus testTwoLoanStatus = Lender.LoanStatus.DENIED;

        // Execution (no action -- available funds constant from test one)

        // Assertion
        assertEquals(testTwoLoanStatus, lender.getLoanApprovalStatus(testTwoLoanAmount));

        // Termination (no action)

        // test 3 (approved with no margin)
        final double testThreeLoanAmount = 250000;
        final double testThreeAvailableFunds = 250000;
        final Lender.LoanStatus testThreeLoanStatus = Lender.LoanStatus.APPROVED;

        // Execution
        // need 250k, already have 200k | 200k + (250k - 200k) == 250k
        lender.depositFunds(testThreeAvailableFunds - lender.getAvailableFunds());

        // Assertion
        assertEquals(testThreeLoanStatus, lender.getLoanApprovalStatus(testThreeLoanAmount));

        // Teardown (no action)

    }

}
