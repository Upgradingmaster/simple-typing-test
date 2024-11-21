package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;


// The Main UI Application Frame
public class MainFrame extends JFrame {
    private final String TITLE;
    private ViewHandler viewHandler;


    // EFFECTS: Sets the top-level parameters
    //          Sets the initiale position and size
    //          Registers children Panels
    MainFrame() {
        TITLE = "Simple Typing Test";
        this.viewHandler = new ViewHandler(this);
        initMain();
        initLayout();
        initViews();
    }

    // EFFECTS: Sets the top-level parameters
    private void initMain() {
        setTitle(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // EFFECTS: Sets the position and size of the frame
    private void initLayout() {
        setSize(1080, 720);
        setLocationRelativeTo(null);
    }

    // EFFECTS: Registers the child panels to the frame
    private void initViews() {
        viewHandler.initViews();
    }
}
