package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Statistic;
import model.Statistics;

// The pane displaying all current statistics i.e. the state
public class StatisticsView extends JPanel {
    private StatisticsController controller;

    // EFFECTS: Registers the controller 
    //          Sets the layout to BoxLayout
    //          Displays no statistics message
    StatisticsView(StatisticsController controller) {
       this.controller = controller;
       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       noStatistics();
    }

    //private void initComponents() {
    //    add(new StatisticButton(new Statistic("abc", "abc", 2, 2)));
    //    add(new StatisticButton(new Statistic("abc", "abc", 2, 2)));
    //    add(new StatisticButton(new Statistic("abc", "abc", 2, 2)));
    //}
    
    // EFFECTS: Adds a statistic and updates the ui
    //          Deletes the No statisics message if it exists
    public void addStatisticButton(Statistic statistic) {
        if (getComponentCount() == 1 && getComponent(0) instanceof JLabel) {
            remove(0);
        }
        add(new StatisticButton(statistic));
    }

    public void removeStatisticButton(int index) {
        remove(index);
        if (getComponentCount() == 0) {
            noStatistics();
        }
    }

    public void clearStatisticButtons() {
        removeAll();
        noStatistics();
    }


    public void setStatisticsButton(Statistics statistics) {
        clearStatisticButtons();
        for (Statistic s : statistics.getStatsArrayList()){
            addStatisticButton(s);
        }
    }

    // EFFECTS: Display no statistics loaded when state is empty
    private void noStatistics() {
        add(new JLabel("No statistics loaded"));
    }
}
