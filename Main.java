import java.util.ArrayList;

//Programmed by Zachary Treichler

public class Main extends GlobalFunctions{
    public static void main(String[] args) {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        readFile(patientList, "Prioritization Exercise, v3.csv");
        outputToFile(patientList,"results.txt");
    }
}