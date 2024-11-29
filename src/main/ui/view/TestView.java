package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

// Represents the view where a test will occur
class TestView extends JPanel {
    private TestController controller;
    private JLabel systemField;
    private JTextField userField;


    private static final Font TEST_FONT = new Font("Serif", Font.BOLD, 26);

    // EFFECTS: Initializes the layout to BoxLayout
    //          Adds the test components
    TestView(TestController controller) {
        this.controller = controller;
        initLayout();
        initComponents();
    }

    // EFFECTS: Sets the layout of the view
    private void initLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // EFFECTS: Adds the primary components' skeleton
    private void initComponents() {
        initUserField();
        initSystemField();


        add(Box.createVerticalGlue());
        add(Box.createHorizontalGlue());

        add(systemField);
        add(Box.createVerticalStrut(10));
        add(userField);

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
    }


    // EFFECTS: Defines the systemField skeleton
    private void initSystemField() {
        systemField = new JLabel(); 
        systemField.setAlignmentX(Component.CENTER_ALIGNMENT);

        systemField.setFont(TEST_FONT);
    }

    // EFFECTS: Defines the userField skeleton
    private void initUserField() {
        userField = new JTextField(75);
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);

        userField.setFont(TEST_FONT);

        userField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enter pressed
                end(userField.getText());
            }
        });
    }


    // EFFECTS: Starts a new test
    //          calls the appropriate methods in the controller
    //          starts the countdown
    public void startNewTestSequence(int wordLimit, int timeLimit) {
        systemField.setText("Starting in, " + Constants.countdownTime);
        clearUserField();
        resize();
        reload();

        controller.newTestInstance(wordLimit, timeLimit, this);
        startingCountdown(Constants.countdownTime, () -> beginTest());
    }


    // REQUIRES: To be called after the countdown
    // EFFECTS:  Calls the appropriate controller methods 
    //           changes the view to show the expected sentence and the textfield
    private void beginTest() {
        controller.beginTest(); // Starts the timers
        systemField.setText(controller.getExpectedSentence());
        resize();
        userField.setEditable(true);
    }

    // REQUIRES: Call in service 
    // EFFECTS: Called by the service to force stop
    public void onForceEnd() {
        String userString = userField.getText();
        end(userString);
    }


    // EFFECTS: End the test with whatever the user typed
    private void end(String userSentence) {
        controller.testEnded(userSentence);
    }



    // EFFECTS: Sets the textField to the empty string
    private void clearUserField() {
        userField.setText("");
        userField.setEditable(false);
    }

    // EFFECTS: Pack both fields to the dimensions of system field's natural size
    private void resize() {
        Dimension newWidth = systemField.getPreferredSize();
        userField.setMaximumSize(newWidth);
        systemField.setMaximumSize(newWidth);
    }

    // EFFECTS: Updates the view to the latest state
    private void reload() {
        revalidate();
        repaint();
    }


    // EFFECTS: Displays the countdown and runs callback at the end
    private void startingCountdown(int seconds, Runnable callback) {
        Timer timer = new Timer(1000, new ActionListener() {
            private int time = seconds;
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                systemField.setText("Starting in, " + time);
                if (time == 0) {
                    ((Timer)e.getSource()).stop();
                    callback.run();
                }
            }
        });
        timer.start();
    }

    
}
