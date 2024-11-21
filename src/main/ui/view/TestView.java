package ui;

import javax.swing.JPanel;

class TestView extends JPanel {
    private TestController controller;

    TestView(TestController controller) {
        this.controller = controller;
    }
}
