import java.util.HashMap;

public class Patient implements Comparable<Patient>{
    //Variables included in the Excel file
    String name;
    double risk;
    String market;
    long LOD;
    String status;
    int zip;
    String level;
    String provider1;
    String provider2;
    String provider3;
    String provider4;
    String provider5;
    String provider6;
    String provider7;
    String provider8;
    String provider9;
    Double score;
    /*
     * Default constructor for the patient class
     * Time complexity: O(1);
     */
    public Patient(){
        this.name = "";
        this.risk = 0.0;
        this.market = "";
        this.LOD = 0;
        this.status = "";
        this.zip = 0;
        this.level = "";
        this.provider1 = "";
        this.provider2 = "";
        this.provider3 = "";
        this.provider4 = "";
        this.provider5 = "";
        this.provider6 = "";
        this.provider7 = "";
        this.provider8 = "";
        this.provider9 = "";
        this.score = 0.0;
    }
    /*
     * Patient constructor containing all arguments (Due to the nature of the file, only a full constructor is needed)
     * Score is calculated separately, which is why it still isn't passed as a parameter and set to 0.0
     * Time complexity: O(1);
     */
    public Patient(String name, double risk, String market, long LOD, String status, int zip, String level, String provider1, String provider2, String provider3, String provider4, String provider5, String provider6, String provider7, String provider8, String provider9){
        this.name = name;
        this.risk = risk;
        this.market = market;
        this.LOD = LOD;
        this.status = status;
        this.zip = zip;
        this.level = level;
        this.provider1 = provider1;
        this.provider2 = provider2;
        this.provider3 = provider3;
        this.provider4 = provider4;
        this.provider5 = provider5;
        this.provider6 = provider6;
        this.provider7 = provider7;
        this.provider8 = provider8;
        this.provider9 = provider9;
        this.score = 0.0;
    }
    /*
     * Get methods for the patient's attributes which are used in the class.
     * Time complexity: O(1);
     */
    public double getRisk() { return risk; }
    public String getMarket() { return market; }
    public long getLOD() { return LOD; }
    public String getStatus(){ return status;}
    public int getZIP(){ return zip; }
    public String getLevel() { return level; }
    public String getProvider1() { return provider1; }
    public String getProvider2() { return provider2; }
    public String getProvider3() { return provider3; }
    public String getProvider4() { return provider4; }
    public String getProvider5() { return provider5; }
    public String getProvider6() { return provider6; }
    public String getProvider7() { return provider7; }
    public String getProvider8() { return provider8; }
    public String getProvider9() { return provider9; }
    public Double getScore() { return score; }
    /*
     * Set method for the patient's attributes which are used in the class
     * Time complexity: O(1)
     */
    public void setScore(Double d){ score = d; }

    /*
     * To String method which outputs all parameters of the patient class
     * Time complexity: O(1)
     */
    public String fullToString(){
        return "Patient name: "+ this.name + " | " + "Patient Risk: "+ this.risk + " | " + "Market: " + this.market + " | " +
                "Patient LOD: " + this.LOD + " | " + "Patient Status: " + this.status + " | " + "Patient Level: " + this.level + " | " + "Patient Score: " + this.score + "\n";
    }
    /*
     * To String method used to output the patient's name, and a score value which will be calculated at a later point
     * Time complexity: O(1)
     */
    public String partialToString(){
        return String.format("Patient name: %-15s | Market: %-25s | Patient Score: %.3f\n", this.name, this.market, this.score);
    }
    /*
     * This method is used to compare one instance of a patient to another patient. The default comparison method is by a patient's score.
     * Time complexity: O(1)
     */
    public int compareTo(Patient p){
        if(this.getScore() > p.getScore()){
            return -1;
        }
        if(this.getScore() < p.getScore()){
            return 1;
        }
        else{
            return 0;
        }
    }
    /*
     * This method counts the number of providers that the patient has.
     * A higher number of providers correlates to directly to a higher score value being returned.
     * Time Complexity: O(1)
     */
    public double getProviderScore(){
        if(!(this.getProvider9().equals(""))){
            return 9.0;
        }
        else if(!(this.getProvider8().equals(""))){
            return 8.0;
        }
        else if(!(this.getProvider7().equals(""))){
            return 7.0;
        }
        else if(!(this.getProvider6().equals(""))){
            return 6.0;
        }
        else if(!(this.getProvider5().equals(""))){
            return 5.0;
        }
        else if(!(this.getProvider4().equals(""))){
            return 4.0;
        }
        else if(!(this.getProvider3().equals(""))){
            return 3.0;
        }
        else if(!(this.getProvider2().equals(""))){
            return 2.0;
        }
        else if(!(this.getProvider1().equals(""))){
            return 1.0;
        }
        return 0.0;
    }
    /*
     * This method gets the LOD score for this current patient.
     * If the patient has a LOD date that is between 3-6 months ago, the method returns a score of 3.
     * If the patient has a LOD date that is more than 6 months ago, the method returns a score of 2.
     * If the patient has a LOD date that is less than 3 months ago, the method returns a minimum value of 1.
     * Time complexity: O(1);
     */
    public static double getLOD_Score(long l){
        //Dates compared to beginning of project, August 1st, 2024
        final long threeMonthsBefore = 20240501;
        final long sixMonthsBefore = 20240201;

        if((l <= threeMonthsBefore) && (l >= sixMonthsBefore)){
            return 3.0;
        }
        else if(l < 20240201){
            return 2.0;
        }
        else{
            return 1.0;
        }
    }
    /*
     * Returns the score value associated with this current patient's risk
     * Time complexity: O(1)
     */
    public double getRiskScore(){
        return this.getRisk();
    }
    /*
     * This method uses a hash map to get a score for each patients level value. The higher the level, the higher the associated score value.
     * Time Complexity: O(1)
     */
    public double getLevelScore(){
        HashMap<String, Double> levels = new HashMap<String, Double>();
        levels.put("Very High", 6.0);
        levels.put("High", 5.0);
        levels.put("Moderate", 3.0);
        levels.put("Low", 1.0);
        levels.put("#N/A", 0.0);
        levels.put("N/A", 0.0);
        return levels.get(getLevel());
    }
    /*
     * This method, similar to getLevelScore, uses a hash map to store keys in tiers. Depending on the status of the patient, it receives a higher or lower score value.
     * Time Complexity: O(1)
     */
    public double getStatusScore(){
        HashMap<String, Double> statuses = new HashMap<String, Double>();
        //Tier One
        statuses.put("Pending Initial Outreach", 8.0);
        //Tier Two
        statuses.put("Scheduled", 7.0); statuses.put("Partial visit completed", 7.0);
        //Tier Three
        statuses.put("Talked to Caregiver", 6.0); statuses.put("Talked to Member", 6.0); statuses.put("Talked to Member 30 Day Return", 6.0);
        statuses.put("No Availability",6.0); statuses.put("Requested Literature",6.0); statuses.put("Snowbird", 6.0); 
        statuses.put("Sub-Acute", 6.0);
        //Tier Four
        statuses.put("Left Message - Voicemail",5.0); statuses.put("ALF", 5.0); statuses.put("Change in staff availability",5.0);
        statuses.put("Completed",5.0); statuses.put("Hospitalized",5.0); statuses.put("Hospitalized or In Medical Facility", 5.0);
        statuses.put("No Answer",5.0); statuses.put("Patient no longer available for appt time",5.0); statuses.put("Provider sick",5.0);
        //Tier Five
        statuses.put("Max Call Attempt", 4.0); statuses.put("Other Contact", 4.0); statuses.put("Patient not home at time of visit", 4.0);
        statuses.put("Other", 4.0); statuses.put("Patient not available at home visit", 4.0);
        //Tier Six
        statuses.put("Invalid Number", 3.0); statuses.put("Unable to locate/Missed patient", 3.0); statuses.put("Refer to Community Outreach", 3.0);
        statuses.put("Research", 3.0); statuses.put("Wrong Number", 3.0);
        //Tier Seven
        statuses.put("Refused - Its Not Necessary", 2.0); statuses.put("Refused - Do Not Call - Mail Only", 2.0); statuses.put("Custodial", 2.0);
        statuses.put("LTAC", 2.0); statuses.put("Patient refused home visit", 2.0); statuses.put("Patient refuses", 2.0);
        statuses.put("Refused - Patient Has Plan Complaint", 2.0); statuses.put("Refused - Caregiver Refuses", 2.0); statuses.put("Refused - Do Not Call", 2.0);
        statuses.put("Refused - Frequents Doctor", 2.0); statuses.put("Refused - Not Comfortable with Home Visit", 2.0); statuses.put("Refused - Patient Has Landmark Complaint", 2.0);
        statuses.put("Refused - Too Busy for Visit", 2.0);
        //Tier Eight/Misc
        statuses.put("Deceased", 1.0); statuses.put("No Longer Eligible with Plan", 1.0);
        statuses.put("Refused - Patient Not Enrolled in Health Plan", 1.0); statuses.put("Hospice", -100.0);

        return statuses.get(getStatus());
    }
    /*
     * This method calculates the "score" for each patient by getting the score value for each individual attribute.
     * Time complexity: O(1)
     */
    public void calculateScore(){
        score += 0.4 * getRiskScore();              //Risk score
        score += 0.1 * getLOD_Score(getLOD());      //LOD score
        score += 0. * 0.2 * getLevelScore();        //Level score
        score += 0.5 * getProviderScore();          //Provider score
        score += 0.15 * getStatusScore();           //Status score

        //Rounding the score value to 3 decimal places
        score = (double)(Math.round(score*1000));
        score /= 1000;

    }
}
