package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import model.Statistic;

// A UI element representing a single statistic with a drop down for more details
public class StatisticButton extends JPanel {
    private StatisticButtonController controller;
    private boolean showingDesc;
    private static final int BUTTON_MIN = 50;
    private static final int DESC_MIN = 300;

    private Statistic statistic;

    // EFFECTS: Defines the statistic button with a dropdown
    public StatisticButton(Statistic statistic, StatisticButtonController controller) {
        this.controller = controller;
        showingDesc = false;
        this.statistic = statistic;
        initLayout();
        addCustomButton();
    }


    // EFFECTS: Sets the layout of the button
    private void initLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


    // EFFECTS: Adds the custom button with the dropdown description
    //          Creates the description when called
    private void addCustomButton() {
        JButton button = new JButton(getHeaderString());
        button.setAlignmentX(CENTER_ALIGNMENT);

        JScrollPane desc = createDesc();

        setMinimumSize(new Dimension(Integer.MAX_VALUE, BUTTON_MIN)); 
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, BUTTON_MIN)); 
        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, desc.getPreferredSize().height));

        button.addActionListener((a) -> {
            if (showingDesc) {
                remove(1);
                controller.hideDesc();
            } else {
                add(desc);
                controller.showDesc();
            }
            revalidate();
            repaint();
            showingDesc = !showingDesc;
        });
        add(button);
    }


    // EFFECTS: Builds the description Pane
    private JScrollPane createDesc() {
        JTextArea desc = new JTextArea(getDescriptionString());
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setAlignmentX(CENTER_ALIGNMENT);
        JScrollPane scrollableDesc = new JScrollPane(desc);
        scrollableDesc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollableDesc;
    }

    // EFFECTS: Builds and returns the Statistic information for the GUI
    private String getDescriptionString() {
        String w = (statistic.getWorstLetter() == 0) ? "No mistakes" :  statistic.getWorstLetter() + "";
        return String.format(
                "Time Taken: %d\n"
                + "WPM: %d\n"
                + "Accuracy: %d%%\n"
                + "Words Typed: %d\n"
                + "Worst Letter: %s\n"
                + "Expected :%s\n"
                + "Your Sentence: %s",
                statistic.getUserDuration(),
                statistic.getWpm(),
                statistic.getAccuracy(), 
                statistic.getWordsTyped(),
                w, 
                statistic.getExpectedSentence(),
                statistic.getUserSentence());
    }

    // EFFECTS: Returns the expected sentence
    private String getHeaderString() {
        return statistic.getExpectedSentence();
    }
}
