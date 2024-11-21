package ui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class HomeView extends JPanel {
    
    private final Font TITLE_FONT = new Font("Serif", Font.BOLD, 36);
    private final Font BUTTON_FONT = new Font("Serif", Font.BOLD, 20);

    HomeView() {
        initLayout();
        addHomeComponents();
    }
    


    // EFFECTS: Sets the layout for the positioning of child components 
    private void initLayout() {
    }

    // EFFECTS: Adds the primary home option buttons in the menu
    private void addHomeComponents() {

    }

    // EFFECTS: Returns the title label
    private JLabel makeTitle() {
        
    }

    // EFFECTS: Returns a start button which starts a test
    private JButton makeStartButton() {
        
    }

    // EFFECTS: Returns a save button which saves statistics
    private JButton makeSaveButton() {
        
    }

    // EFFECTS: Returns a load button which writes statistics
    private JButton makeLoadButton() {
        
    }

    // EFFECTS: Returns a quit button which quits the application
    private JButton makeQuitButton() {
        
    }
}
