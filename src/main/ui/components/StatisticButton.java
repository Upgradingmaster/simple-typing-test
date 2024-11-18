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
    private boolean showingDesc;
    private final int BUTTON_MIN = 50;
    private final int DESC_MIN = 300;

    private Statistic s;

    // EFFECTS: Defines the  statistic button with a dropdown
    public StatisticButton(Statistic s) {
        showingDesc = false;
        this.s = s;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //setBorder(new LineBorder(Color.GREEN, 2));
        JButton button = new JButton(getHeaderString());
        button.setAlignmentX(CENTER_ALIGNMENT);


        JTextArea desc = new JTextArea(getDescriptionString());
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setAlignmentX(CENTER_ALIGNMENT);
        JScrollPane scrollableDesc = new JScrollPane(desc);
        scrollableDesc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setMinimumSize(new Dimension(Integer.MAX_VALUE, BUTTON_MIN)); 
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, BUTTON_MIN)); 
        scrollableDesc.setMaximumSize(new Dimension(Integer.MAX_VALUE, desc.getPreferredSize().height));


        button.addActionListener((a) -> {
             if (showingDesc) {
                 remove(1);
             } else {
                 add(scrollableDesc);
             }
             revalidate();
             repaint();
             showingDesc = !showingDesc;
        });
        add(button);
    }
    private String getDescriptionString() {
        String w = (s.getWorstLetter() == 0) ? "No mistakes" :  s.getWorstLetter() + "";
        return String.format(
                "Time Taken: %d\n"
                + "WPM: %d\n"
                + "Accuracy: %d%%\n"
                + "Words Typed: %d\n"
                + "Worst Letter: %s\n"
                + "Expected :%s\n"
                + "Your Sentence: %s",
                s.getUserDuration(),
                s.getWpm(),
                s.getAccuracy(), 
                s.getWordsTyped(),
                w, 
                s.getExpectedSentence(),
                s.getUserSentence());
    }
    private String getHeaderString() {
        return s.getExpectedSentence();
    }
}
