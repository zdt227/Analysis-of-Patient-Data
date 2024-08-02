import java.util.HashMap;

public class StatusRankings {
    public HashMap<String, Integer> Statuses = new HashMap<String, Integer>();

    public StatusRankings() {
        Statuses = new HashMap<>();
        Statuses.put("Scheduled", 10);
        Statuses.put("Talked with provider", 9);
        Statuses.put("Left message", 8);
        
    }

    // Public getter method to provide access to the statuses map
    public HashMap<String, Integer> getStatuses() {
        return Statuses;
    }
}
