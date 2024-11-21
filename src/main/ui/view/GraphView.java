package ui;

import javax.swing.JList;
import javax.swing.JPanel;

public class GraphView extends JPanel {
    private GraphController controller;

    GraphView(GraphController controller) {
        this.controller = controller;
    }
}
