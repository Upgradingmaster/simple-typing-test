package ui;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Statistics;  

public class GraphView extends JPanel {
    private GraphController controller;

    GraphView(GraphController controller) {
        this.controller = controller;
        initLayout();
        addButton();
        showGraph();
    }





    private void addButton() {
        JButton back = new JButton("Go Back");
        back.setAlignmentX(Component.LEFT_ALIGNMENT);
        back.addActionListener(e -> {
            controller.backButtonPressed();
        });
        add(back);
    }


    private void initLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


    public void showGraph() {
        add(controller.getChartPanel(null));
    }


    public void redrawGraph(Statistics newState) {
        remove(1);
        add(controller.getChartPanel(newState));
        revalidate();
        repaint();
    }

}
