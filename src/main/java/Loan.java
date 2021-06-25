import java.util.UUID;

public class Loan {

    // defines internal enum for Loan Approval for qualified applicants
    enum LoanStatus {
        APPROVED, DENIED, PENDING, ACCEPTED, REJECTED
    }

    // enumerate member fields
    private final double _loanAmount;

    private LoanStatus _loanStatus;

    private final UUID _identifier;

    // defines constructor
    public Loan(double loanAmount, LoanStatus loanStatus){
        this._loanAmount = loanAmount;
        this._loanStatus = loanStatus;
        this._identifier = UUID.randomUUID();
    }


    // enumerate instance methods

    public double getLoanAmount(){
        return this._loanAmount;
    }

    public LoanStatus getLoanStatus(){
        return this._loanStatus;
    }

    public UUID getIdentifier(){
        return this._identifier;
    }

    public void setLoanStatus(final LoanStatus loanStatus){
        this._loanStatus = loanStatus;
    }

}
