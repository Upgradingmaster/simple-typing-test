package model;

import java.util.ArrayList;

// A collection of Statistic objects
public class Statistics {

    private ArrayList<Statistic> stats = new ArrayList<>();

    /*
     * REQUIRES: an array of Statistic instances
     * MODIFIES: this
     * EFFECTS: Instantiates this object with an ArrayList of statistics
     * */
    public Statistics(ArrayList<Statistic> stats) {
        //stub 
    }


    /*
     * REQUIRES: this
     * EFFECTS: Builds a progress graph based of the WPM values of the current collection of statistics 
     *          The Y-axis represents the WPM of a Statistic
     *          The X-axis essentially represents the order it was added to the list 
     * */
    public String generateGraph() {
        // stub
        return "";
    }



    // Gettters and Setters

    public ArrayList<Statistic> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Statistic> stats) {
        this.stats = stats;
    }


}
