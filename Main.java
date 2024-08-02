import java.util.ArrayList;

public class Main extends GlobalFunctions{
    public static void main(String[] args) {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        readFile(patientList, "Prioritization Exercise_csv.csv");
        outputToFile(patientList,"results.txt");
    }

}