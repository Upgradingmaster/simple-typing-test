package ui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogBoxView extends JDialog {
    
    private int word;
    private int time;
    private boolean valid;

    DialogBoxView(JFrame parent) {
        super(parent, "Select", true);
        initLayout();
        addComponents();
        position(parent);
    }
    

    // EFFECTS: Sets the layout for the positioning of child components 
    private void initLayout() {
        setLayout(new GridLayout(3,2));
    }


    private void addComponents() {
        JTextField wordTF = new JTextField(10);
        JTextField timeTF = new JTextField(10);

        add(new JLabel(String.format("Test Word Count(max of %d) : ", Constants.wordsMax)));
        add(wordTF);
        add(new JLabel(String.format("Test Length(s)(max of %d) : ", Constants.timeMax)));
        add(timeTF);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            try {
                word = Integer.parseInt(wordTF.getText());
                time = Integer.parseInt(timeTF.getText());
                valid = validateInputs(word, time);
                if (!valid) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers");
                    return;
                }
                
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers");
            }
        });

        add(ok);
    }


    private boolean validateInputs(int word, int time) {
       if (Constants.wordsMax < word || word <= 0 
               || Constants.timeMax < time || time <= 0){
           return false;
        } 
       return true;
    }

    private void position(JFrame parent) {
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public int getWord() {
        return word;
    }


    public int getTime() {
        return time;
    }

    public boolean getValid() {
        return valid;
    }

    public boolean isValid() {
        return valid;
    }
}
