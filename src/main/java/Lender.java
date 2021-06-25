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
}
