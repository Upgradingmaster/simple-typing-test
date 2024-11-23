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

class TestView extends JPanel {
    private TestController controller;
    private JLabel systemField;
    private JTextField userField;


    private static final Font TEST_FONT = new Font("Serif", Font.BOLD, 26);

    TestView(TestController controller) {
        this.controller = controller;
        initLayout();
        initComponents();
    }

    private void initLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

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


    private void initSystemField() {
        systemField = new JLabel(); 
        systemField.setAlignmentX(Component.CENTER_ALIGNMENT);

        systemField.setFont(TEST_FONT);
    }

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


    public void startNewTestSequence(int wordLimit, int timeLimit) {
        systemField.setText("Starting in, " + Constants.countdownTime);
        clearUserField();
        resize();
        reload();

        controller.newTestInstance(wordLimit, timeLimit, this);
        startingCountdown(Constants.countdownTime, () -> beginTest());
    }


    public void beginTest() {
        controller.beginTest(); // Starts the timers
        systemField.setText(controller.getExpectedSentence());
        resize();
        userField.setEditable(true);
    }

    public void onForceEnd() {
        String userString = userField.getText();
        end(userString);
    }

    private void end(String userSentence) {
        controller.testEnded(userSentence);
    }



    private void clearUserField() {
        userField.setText("");
        userField.setEditable(false);
    }

    // Pack both fields to the dimesnsions of system field's natural size
    private void resize() {
        Dimension newWidth = systemField.getPreferredSize();
        userField.setMaximumSize(newWidth);
        systemField.setMaximumSize(newWidth);
    }

    private void reload() {
        revalidate();
        repaint();
    }


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
