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

import model.Services;

// The pane displaying all current statistics i.e. the state
public class StatisticsView extends JPanel {
    private Services services;

    // EFFECTS: Registers the controller 
    //          Sets the layout to BoxLayout
    //          Displays no statistics message
    StatisticsView(Services services) {
        this.services = services;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        noStatistics();
    }

    // EFFECTS: Adds a statistic 
    //          Deletes the No statisics message if it exists
    public void addStatisticButton(Statistic statistic) {
        if (getComponentCount() == 1 && getComponent(0) instanceof JLabel) {
            remove(0);
        }
        add(new StatisticButton(statistic, new StatisticButtonController(services, getComponentCount() + 1)));
    }


    // EFFECTS: Removes all statisticButtons from the view
    public void clearStatisticButtons() {
        removeAll();
        noStatistics();
    }

    // EFFECTS: Adds all statistics from the argument to the view 
    public void setStatisticsButtons(Statistics statistics) {
        clearStatisticButtons();
        for (Statistic s : statistics.getStatsArrayList()) {
            addStatisticButton(s);
        }
    }

    // EFFECTS: Display no statistics loaded when state is empty
    private void noStatistics() {
        add(new JLabel("No statistics loaded"));
    }
}
