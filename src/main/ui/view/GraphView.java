package ui;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Statistics;  

// Represents the GraphView showing the graph of statistics and a back button to go to HomeView
public class GraphView extends JPanel {
    private GraphController controller;

    GraphView(GraphController controller) {
        this.controller = controller;
        initLayout();
        addButton();
        showGraph();
    }

    // EFFECTS: Defines the layout of this view
    private void initLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // EFFECTS:  Adds the back button to switch to HomeView
    private void addButton() {
        JButton back = new JButton("Go Back");
        back.setAlignmentX(Component.LEFT_ALIGNMENT);
        back.addActionListener(e -> {
            controller.backButtonPressed();
        });
        add(back);
    }

    // EFFECTS: Adds the Graph
    public void showGraph() {
        add(controller.getChartPanel(null));
    }

    // EFFECTS: Redraws the graph with the statistics provided
    public void redrawGraph(Statistics newState) {
        remove(1);
        add(controller.getChartPanel(newState));
        revalidate();
        repaint();
    }

}
