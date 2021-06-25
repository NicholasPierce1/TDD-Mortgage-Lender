import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        final Loan.LoanStatus testOneLoanStatus = Loan.LoanStatus.APPROVED;

        // Execution
        lender.depositFunds(testOneAvailableFunds);

        // Assertion
        assertEquals(testOneLoanStatus, lender.getLoanApprovalStatus(testOneLoanAmount));

        // Termination (no action)

        // test 2 (rejected by margin)
        final double testTwoLoanAmount = 250000;
        final Loan.LoanStatus testTwoLoanStatus = Loan.LoanStatus.DENIED;

        // Execution (no action -- available funds constant from test one)

        // Assertion
        assertEquals(testTwoLoanStatus, lender.getLoanApprovalStatus(testTwoLoanAmount));

        // Termination (no action)

        // test 3 (approved with no margin)
        final double testThreeLoanAmount = 250000;
        final double testThreeAvailableFunds = 250000;
        final Loan.LoanStatus testThreeLoanStatus = Loan.LoanStatus.APPROVED;

        // Execution
        // need 250k, already have 200k | 200k + (250k - 200k) == 250k
        lender.depositFunds(testThreeAvailableFunds - lender.getAvailableFunds());

        // Assertion
        assertEquals(testThreeLoanStatus, lender.getLoanApprovalStatus(testThreeLoanAmount));

        // Teardown (no action)

    }

    @Test
    public void testIsApplicantQualified() {

        // SEAT

        // Test 1 (Applicant denied via dti)
        // Setup
        final double testOneLoanAmount  = 100000;
        final double testOneSavingsAmount  = 25000;
        final int testOneDti  = 36;
        final int testOneCreditScore = 700;
        final boolean testOneExpected = false;

        // Execution (no action)

        // Assertion
        assertEquals(testOneExpected, lender.isApplicantQualified(
                testOneLoanAmount,
                testOneSavingsAmount,
                testOneDti,
                testOneCreditScore)
        );
        // Teardown (no action)

        // Test 2 (Applicant denied via Credit Score)

        // Setup
        final double testTwoLoanAmount  = 100000;
        final double testTwoSavingsAmount  = 25000;
        final int testTwoDti  = 35;
        final int testTwoCreditScore = 620;
        final boolean testTwoExpected = false;

        // Execution (no action)

        // Assertion
        assertEquals(testTwoExpected, lender.isApplicantQualified(
                testTwoLoanAmount,
                testTwoSavingsAmount,
                testTwoDti,
                testTwoCreditScore)
        );
        // Teardown (no action)

        // Test 3 (Applicant denied via their savings to loan percentage)

        // Setup
        final double testThreeLoanAmount  = 100000;
        final double testThreeSavingsAmount  = 24000;
        final int testThreeDti  = 35;
        final int testThreeCreditScore = 700;
        final boolean testThreeExpected = false;

        // Execution (no action)

        // Assertion
        assertEquals(testThreeExpected, lender.isApplicantQualified(
                testThreeLoanAmount,
                testThreeSavingsAmount,
                testThreeDti,
                testThreeCreditScore)
        );
        // Teardown (no action)

        // Test 4 (Applicant approved)

        // Setup
        final double testFourLoanAmount  = 100000;
        final double testFourSavingsAmount  = 25000;
        final int testFourDti  = 35;
        final int testFourCreditScore = 621;
        final boolean testFourExpected = true;

        // Execution (no action)

        // Assertion
        assertEquals(testFourExpected, lender.isApplicantQualified(
                testFourLoanAmount,
                testFourSavingsAmount,
                testFourDti,
                testFourCreditScore)
        );

        // Teardown (no action)

    }


    @Test
    public void testGetApprovedLoan(){

        // SEAT

        // Test 1
        final double inputLoanAmount  = 100000;

        // Setup
        // applicant's loan approval request (valid && lender has funds)

        final Loan partialExpectedLoan = new Loan(inputLoanAmount, Loan.LoanStatus.PENDING);

        final double expectedAvailableFunds = 0;

        // lenders initial funds availability
        final double lenderAvailability = 100000;

        // Execution
        lender.depositFunds(lenderAvailability);

        final Loan actualLoan = lender.getPendingLoanForApplicant(inputLoanAmount);

        // Assertion

        // extract Loan
        // Loan: loan amount
        assertEquals(
                partialExpectedLoan.getLoanAmount(),
                actualLoan.getLoanAmount(),
                "Loan amounts are not equal. Please revise."
        );

        // Loan: loan status
        assertEquals(
                partialExpectedLoan.getLoanStatus(),
                actualLoan.getLoanStatus(),
                "Loan statuses are not equal. Please revise."
        );

        // Lender: available funds
        assertEquals(
                expectedAvailableFunds,
                lender.getAvailableFunds(),
                "Lender's available funds are not equal. Please revise."
        );

        // Lender: pending funds
        assertEquals(
                inputLoanAmount,
                lender.getPendingFunds(),
                "Lender's pending funds are not equal. Please revise."
        );


    }

    @Test
    public void testHandleLoanResponse(){

        // SEAT

        // Setup

        final double lenderAvailability = 500000;

        double currentAvailability;
        double currentPendingFunds;

        final Loan loanToAccept;
        final double loanToAcceptAmount = 100000;
        final Loan.LoanStatus expectedAcceptedLoanStatus = Loan.LoanStatus.ACCEPTED;

        final Loan loanToReject;
        final double loanToRejectAmount = 200000;
        final Loan.LoanStatus expectedRejectedLoanStatus = Loan.LoanStatus.REJECTED;


        // Execution

        // initializing (making the loans live)
        loanToAccept = lender.getPendingLoanForApplicant(loanToAcceptAmount);
        loanToReject = lender.getPendingLoanForApplicant(loanToRejectAmount);

        // updates placeholder state for current lender
        currentAvailability = lender.getAvailableFunds();
        currentPendingFunds = lender.getPendingFunds();


        // submit responses to loans (loan to accept first)
        lender.handleLoanResponse(loanToAccept, true);

        // Assertion

        // loan acceptance: available funds
        assertEquals(
                currentAvailability,
                lender.getAvailableFunds(),
                "testHandleLoanResponse - Loan Acceptance: available funds not equal, please revise."
        );

        // loan acceptance: pending funds
        assertEquals(
                currentPendingFunds - loanToAcceptAmount,
                lender.getPendingFunds(),
                "testHandleLoanResponse - Loan Acceptance: pending funds not equal, please revise."
        );

        // loan acceptance: Loan Status
        assertEquals(
                expectedAcceptedLoanStatus,
                loanToAccept.getLoanStatus(),
                "testHandleLoanResponse - Loan Acceptance: loan statuses are not equal, please revise."
        );

        // updates placeholder state for current lender
        currentAvailability = lender.getAvailableFunds();
        currentPendingFunds = lender.getPendingFunds();

        lender.handleLoanResponse(loanToReject, false);

        // loan rejection: available funds
        assertEquals(
                currentAvailability + loanToRejectAmount,
                lender.getAvailableFunds(),
                "testHandleLoanResponse - Loan Rejection: available funds not equal, please revise."
        );

        // loan rejection: pending funds
        assertEquals(
                currentPendingFunds - loanToRejectAmount,
                lender.getPendingFunds(),
                "testHandleLoanResponse - Loan Rejection: pending funds not equal, please revise."
        );

        // loan rejection: Loan Status
        assertEquals(
                expectedRejectedLoanStatus,
                loanToReject.getLoanStatus(),
                "testHandleLoanResponse - Loan Rejection: loan statuses are not equal, please revise."
        );

        // pending funds shall be balanced out again
        assertEquals(
                lender.getPendingFunds(),
                0,
                "testHandleLoanResponse - Loan Rejection: pending funds should be clears as all loan decisions" +
                        " have been made. Please revise."
                );


    }


}
