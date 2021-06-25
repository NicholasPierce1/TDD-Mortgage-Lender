import java.util.ArrayList;
import java.util.Optional;

public class Lender {

    // enumerates member fields

    private double _availableFunds = 0;

    private double _pendingFunds = 0;


    // enumerates instance methods

    public double getAvailableFunds() {
        return this._availableFunds;
    }

    public double getPendingFunds(){
        return this._pendingFunds;
    }

    public void depositFunds(final double depositAmount) {
         _availableFunds += depositAmount;
    }

    public Loan.LoanStatus getLoanApprovalStatus(final double requestedLoanAmount){
        return this._availableFunds >= requestedLoanAmount ?
                Loan.LoanStatus.APPROVED :
                Loan.LoanStatus.DENIED;
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

    public Loan getPendingLoanForApplicant(final double loanAmount){

        this.transferAvailableFunds(loanAmount);

        return new Loan(loanAmount, Loan.LoanStatus.PENDING);
    }

    private void transferAvailableFunds(final double amountToTransfer){
       this._availableFunds -= amountToTransfer;
       this._pendingFunds += amountToTransfer;
    }

}
