import java.util.Comparator;

public class ComparatorByMarket implements Comparator<Patient>{
    /*
    * This is a comparator used by the patient class to sort patients by their markets.
    * @ param market1 - stores the market for the first patient
    * @ param market2 - stores the market for the second patient
    */
    public int compare(Patient p1, Patient p2){
        String market1 = p1.getMarket();
        String market2 = p2.getMarket();

        return market1.compareTo(market2);
    }
}