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
       //initComponents();
    }

    //private void initComponents() {
    //    add(new StatisticButton(new Statistic("abc", "abc", 2, 2)));
    //    add(new StatisticButton(new Statistic("abc", "abc", 2, 2)));
    //    add(new StatisticButton(new Statistic("abc", "abc", 2, 2)));
    //}

    // EFFECTS: Display no statistics loaded when state is empty
    private void noStatistics() {
        add(new JLabel("No statistics loaded"));
    }

    // EFFECTS: Adds a statistic and updates the ui
    //          Deletes the No statisics message if it exists
    public void addStatistic(Statistic statistic){
    }

}
