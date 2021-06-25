public class Lender {

    // enumerates member fields

    private double _availableFunds = 0;


    // enumerates instance methods


    public double getAvailableFunds() {
        return this._availableFunds;
    }

    public void depositFunds(double depositAmount) {
         _availableFunds += depositAmount;
    }
}
