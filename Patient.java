public class Patient extends StatusRankings{
    //Variables included in the excel file
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
     * No argument constructor for the patient class
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
     * Score is calculated separately, which is why it still isn't passed as a parameter
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
     * To String method which outputs all parameters of the patient class
     */
    public String fullToString(){
        return "Patient name: "+ this.name + " | " + "Patient Risk: "+ this.risk + " | " + "Market: " + this.market + " | " +
                "Patient LOD: " + this.LOD + " | " + "Patient Status: " + this.status + " | " + "Patient Level: " + this.level + " | " + "Patient Score: " + this.score + "\n";
    }
    /*
     * To String method used to output the patient's name, and a score value which will be calculated at a later point
     */
    public String partialToString(){
        return "Patient name: "+ this.name + " | " + "Patient Score: " + this.score;
    }


}
