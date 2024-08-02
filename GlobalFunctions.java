import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class GlobalFunctions {
    /*
     * This method is used to read the contents of the ".csv" file and input them into the patientList ArrayList
     */
    public static void readFile(ArrayList<Patient> list, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String line = read.nextLine();
                //Set to length 16 to ensure no out of bounds error
                String[] tokens = new String[16];
                String[] splitTokens = line.split(",");
                for (int i = 0; i < splitTokens.length; i++) {
                    tokens[i] = splitTokens[i];
                }
                for (int i = splitTokens.length; i < 15; i++) {
                    tokens[i] = "";
                }
                long date = convertDateToLong(tokens[3]);

                Patient temp = new Patient(tokens[0],Double.parseDouble(tokens[1]),tokens[2],date,tokens[4],Integer.parseInt(tokens[5]),tokens[6], tokens[7],tokens[8],tokens[9],tokens[10],tokens[11],tokens[12],tokens[13], tokens[14], tokens[15]);
                list.add(temp);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    /*
     * This method is used to output the contents of the patient array into a text file that can be read.
     */
    public static void outputToFile(ArrayList<Patient> list, String filename){
        try{
            FileWriter fWrite = new FileWriter(filename);
            fWrite.write("Here are the contents of the excel spreadsheet, organized by a score value which is out of 100.\n\n\n");
            for(Patient p: list){
                fWrite.write(p.fullToString());
            }
            fWrite.close();
        }
        catch(IOException e){
            System.out.println("Error writing to the file, " + filename);
        }
    }

    public static long convertDateToLong(String dateAString){
        String[] tokens = dateAString.split("/");

        long dateAsLong = (Long.parseLong(tokens[2])*10000) + (Long.parseLong(tokens[1])*100) + (Long.parseLong(tokens[0]));

        return dateAsLong;
    }
}
