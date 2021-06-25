import java.util.ArrayList;

public class Lender {


    // defines internal enum for Loan Approval for qualified applicants
    enum LoanStatus {
        APPROVED, DENIED
    }

    // enumerates member fields

    private double _availableFunds = 0;


    // enumerates instance methods

    public double getAvailableFunds() {
        return this._availableFunds;
    }

    public void depositFunds(final double depositAmount) {
         _availableFunds += depositAmount;
    }

    public LoanStatus getLoanApprovalStatus(final double requestedLoanAmount){
        return this._availableFunds >= requestedLoanAmount ?
                LoanStatus.APPROVED :
                LoanStatus.DENIED;
    }

    // Rule: Qualifying candidates must have debt-to-income (DTI)
    //less than 36%, credit score above 620 and savings worth 25%
    //of requested loan amount.

    public boolean isApplicantQualified(
            double loanAmount,
            double savingsAmount,
            int dti,
            int creditScore) {

        return dti < 36 && creditScore > 620 && savingsAmount / loanAmount >= 0.25;
    }

}
