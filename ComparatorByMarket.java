import java.util.Comparator;

public class ComparatorByMarket implements Comparator<Patient>{
    public int compare(Patient p1, Patient p2){
        String market1 = p1.getMarket();
        String market2 = p2.getMarket();

        return market1.compareTo(market2);
    }
}