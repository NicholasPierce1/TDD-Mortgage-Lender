public class Loan {

    // defines internal enum for Loan Approval for qualified applicants
    enum LoanStatus {
        APPROVED, DENIED, PENDING
    }

    // enumerate member fields
    private final double loanAmount;

    private LoanStatus loanStatus;

    // defines constructor
    public Loan(double loanAmount, LoanStatus loanStatus){
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
    }


    // enumerate instance methods

    public double getLoanAmount(){
        return this.loanAmount;
    }

    public LoanStatus getLoanStatus(){
        return this.loanStatus;
    }


}
