package model;

import java.util.stream.Collectors;

import org.json.JSONArray;

import persistence.IJsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// A collection of Statistic objects
public class Statistics implements IJsonArray {

    private ArrayList<Statistic> stats = new ArrayList<>();

    /*
     * REQUIRES: an variable amount of Statistic instances
     * MODIFIES: this
     * EFFECTS: Instantiates this object with an ArrayList of statistics
     * */
    public Statistics(Statistic... stats) {
        this.stats = new ArrayList<Statistic>(Arrays.asList(stats));
    }


    /*
     * REQUIRES: this
     * EFFECTS: Builds and returns a progress graph based of the WPM values of the current collection of statistics 
     *          The Y-axis represents the WPM of a Statistic
     *          The X-axis essentially represents the order it was added to the list 
     * */
    public String generateGraph() {
        final int margin = 5;
        final int spacing = 10;

        List<Integer> wpmY = stats.stream().map(Statistic::getWpm).collect(Collectors.toList());
        int maxY = Collections.max(wpmY);
        int minY = Collections.min(wpmY) - 1;
        int n = wpmY.size();

        int rows = (maxY - minY + 1);
        int columns = (margin + 1 + (n + (n * spacing)) + 1);
        StringBuilder graph = new StringBuilder(rows * columns);

        for (int y = maxY; y >= minY; y--) {

            if (y == maxY || y == minY) {
                graph.append(" ".repeat((margin - (String.valueOf(y)).length())) + y);
            } else {
                graph.append(" ".repeat(margin));
            }
            graph.append("|");
            if (y == minY) {
                graph.append(("-".repeat(spacing) + "|").repeat(n));
                break;
            }

            for (int x = 0; x < n; x++) {
                graph.append(" ".repeat(spacing));
                char point = (wpmY.get(x) == y) ? 'x' : ' ';
                graph.append(point);
            }
            graph.append("\n");
        }
        return graph.toString();
    }

    public void addStat(Statistic stat) {
        this.stats.add(stat);
    }

    @Override
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Statistic s : this.stats) {
            jsonArray.put(s.toJsonObject());
        }
        return jsonArray;
    }

    // Gettters and Setters
    public ArrayList<Statistic> getStatsArrayList() {
        return stats;
    }

    public void setStats(ArrayList<Statistic> stats) {
        this.stats = stats;
    }




}
