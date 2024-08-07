import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

//Programmed by Zachary Treichler

public class GlobalFunctions{
    /*
     * This method is used to read the contents of the ".csv" file and input them into the patientList ArrayList.
     * The reason why 2 arrays are used is because there are null instances within the excel spreadsheet, which need to replaced with empty strings
     * Time complexity: O(N^2)
     *
     * @ param read - Scanner used to read the lines from the file
     * @ param line - String used to store each line from the file
     * @ param tokens - Array used to store the split Strings once they are properly formatted to not contain any null values
     * @ param splitTokens - used to store the result of the split, regardless of null values
     */
    public static void readFile(ArrayList<Patient> list, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            read.nextLine();
            while(read.hasNextLine()){
                String line = read.nextLine();
                //Set to length 16 to ensure no out of bounds error
                String[] tokens = new String[16];
                String[] splitTokens = line.split(",");
                for (int i = 0; i < splitTokens.length; i++) {
                    tokens[i] = splitTokens[i];
                }
                for (int i = splitTokens.length; i < 16; i++) {
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
     * This method is used to convert a date stored as a string into a long.
     * The method splits up the string based on every instance of a '/' character, and performs a multiplication and addition operation to give each date a numerical
     * value that can be compared with other dates from the data.
     * Time complexity: O(3) -> O(1). This is constant because we always know the tokens array will have a length of 3
     *
     * @ param tokens - used to store the split String elements
     * @ param dateAsLong - used to store the numerical value of the date, in YYYYMMDD format
     */
    public static long convertDateToLong(String dateAString){
        String[] tokens = dateAString.split("/");

        long dateAsLong = (Long.parseLong(tokens[2])*10000) + (Long.parseLong(tokens[0])*100) + (Long.parseLong(tokens[1]));

        return dateAsLong;
    }
    /*
     * This method is used to format the array based on the clients specifications, and output the contents of the patient array into a text file that can be read
     * Time complexity: O(N)
     */
    public static void outputToFile(ArrayList<Patient> list, String filename){
        generateScoresAndSortArray(list);
        generateOutput(list, filename);
    }
    /*
     * This method determines the score for each patient by adding the Patient class's calculateScore method and the score determined by findZipScores. Then, the score for that patient is set to the sum.
     * For consistency, the score is converted to a value out of 100, determined by its percentage of the data's maximum value.
     * Finally, the default comparator is used to sort the list by score, and then ComparatorByMarket is used to sort the list alphabetically based on market name.
     * Time Complexity: O(N), although this is on the higher end because the array is incremented through 3 times
     * @ param temp - used to store the sum of the getScore and findZipScore operations
     */
    public static void generateScoresAndSortArray(ArrayList<Patient> list){
        for(Patient p : list){
            p.calculateScore();
            double temp = p.getScore() + (0.1 * findZipScore(list, p));
            p.setScore(temp);
        }
        double highScore = findHighestScore(list);
        for(Patient p : list){
            p.setScore(calculateScoreOutOf100(p.getScore(), highScore));
        }
        list.sort(null);
        list.sort(new ComparatorByMarket());
    }
    /*
     * This method calculates the zip score for a patient, by incrementing through the list to see how many matching zip codes there are.
     * The return value is changed to a double because that is the data type for score.
     * Time complexity: O(N)
     *
     * @ param numberOfMatchingZips - Used to store the number of zips that match
     */
    public static double findZipScore(ArrayList<Patient> list, Patient p){
        int numberOfMatchingZips = -1; //Set to -1 because the original patient p will always be counted as a match
        for(Patient pat : list){
            if(p.getZIP() == pat.getZIP()){
                numberOfMatchingZips++;
            }
        }
        return (double)numberOfMatchingZips;
    }
    /*
     * This method is used to find the highest score value in the patient list.
     * @ param maxScore - Used to store the maximum score value. Set to -99999.99 to ensure method functions properly
     * Time complexity: O(N)
     */
    public static double findHighestScore(ArrayList<Patient> list){
        double maxScore = -99999.99;
        for(Patient p : list){
            if(p.getScore() > maxScore){
                maxScore = p.getScore();
            }
        }
        return maxScore;
    }
    /*
     * This method calculates a patients score, relative to the maximum score in the dataset. This is done for streamlining purposes.
     * Time complexity: O(1)
     */
    public static double calculateScoreOutOf100(double currentScore, double maxScore){
        return (currentScore/maxScore) * 100;
    }
    /*
     * This method first creates an arraylist, finalList, which is used to store the top 25% patients from each market
     * generateFinalList is called for each market, adding to finalList
     * Then, the data is written into the file, and the file is closed to ensure proper handling of data.
     * Time Complexity: O(N), similar to the method generateScoreAndSortArray, this is on the upper end of O(N) due to the number of times the list is incremented through
     *
     * @ param finalList - used to store the top 25% patients extracted from the overall database
     * @ param fWrite - FileWriter used to write into the file, "results.txt"
     */
    public static void generateOutput(ArrayList<Patient> list, String filename){
        ArrayList<Patient> finalList = new ArrayList<Patient>();
        generateFinalList(list, finalList, "Albany");
        generateFinalList(list, finalList, "Buffalo");
        generateFinalList(list, finalList, "Central NY - Low Density");
        generateFinalList(list, finalList, "NYC");
        generateFinalList(list, finalList, "Rochester");
        generateFinalList(list, finalList, "Syracuse");
        try{
            FileWriter fWrite = new FileWriter(filename);
            fWrite.write("Here are the contents of the excel spreadsheet, organized by a score value which is out of 100.\n\n");
            for(Patient p : finalList){
                fWrite.write(p.partialToString());
            }
            fWrite.close();
        }
        catch(IOException e){
            System.out.println("Error writing to the file, " + filename);
        }
    }
    /*
     * This method is used to generate the final list, which is created by taking the top 25% from each market and stored into list2.
     * Since the data is already organized, we know that by multiplying by 0.25 we are getting the top 25%s for each market using this method.
     * Time Complexity: O(N)
     *
     * @ param count - This is used to count the number of patients in a particular market. The value of count is used to determine how many patients should be output from each market
     * @ param marketIndex - This is used to find the index of the first instance of the String market, as a patient's market attribute
     */
    public static void generateFinalList(ArrayList<Patient> list1, ArrayList<Patient> list2, String market){
        int count = 0;
        for(Patient p : list1){
            if(p.getMarket().equals(market)){
                count++;
            }
        }
        count = (int)(count * 0.25);
        if(count < 1){
            count = 1;
        }
        if(count > 50){
            count = 50;
        }
        int marketIndex = 0;
        while((list1.get(marketIndex).getMarket().equals(market)) == false){
            marketIndex++;
        }
        for(int i = 0; i < count; i ++){
            list2.add(list1.get(marketIndex));
            marketIndex++;
        }
    }
}
