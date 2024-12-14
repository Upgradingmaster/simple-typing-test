package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import model.State;

import model.Services;

// The Main UI Application Frame
public class MainFrame extends JFrame {
    private static final String TITLE = "Simple Typing Test";
    private ViewHandler viewHandler;
    private Services services;


    // EFFECTS: Sets the top-level parameters
    //          Sets the initiale position and size
    //          Registers children Panels
    MainFrame(State state, Services services) {
        this.viewHandler = new ViewHandler(this);
        this.services = services;
        state.addPropertyChangeListener(viewHandler);
        initMain();
        logBeforeExit();
        initLayout();
        initViews();

    }


    // EFFECTS: Sets the top-level parameters
    private void initMain() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // For manual handling of close (Logging)
    }

    private void logBeforeExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                services.getLogger().printToTerminal();
                dispose();
                System.exit(0);
            }
        });
    }

    // EFFECTS: Sets the position and size of the frame
    private void initLayout() {
        setSize(1080, 720);
        setLocationRelativeTo(null);
    }

    // EFFECTS: Registers the child panels to the frame
    private void initViews() {
        viewHandler.initViews(services);
    }
}
