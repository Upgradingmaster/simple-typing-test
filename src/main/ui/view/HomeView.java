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

    private HomeController controller;

    HomeView(HomeController controller) {
        this.controller = controller;
        initLayout();
        addHomeComponents();
    }
    
    // EFFECTS: Sets the layout for the positioning of child components 
    private void initLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // EFFECTS: Adds the primary home option buttons in the menu
    private void addHomeComponents() {

        add(Box.createVerticalGlue());

        addTitle(0, 25);
        addStartButton(0, 10);
        addGraphButton(0, 10);
        addSaveButton(0, 10);
        addLoadButton(0, 10);
        addQuitButton(0, 10);

        //Push up
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
    }

    // EFFECTS: Returns the title label
    private void addTitle(int spaceBefore, int spaceAfter) {
        add(Box.createVerticalStrut(spaceBefore));

        JLabel titleLabel = new JLabel("Simple Typing Test");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(spaceAfter));
    }

    // EFFECTS: Returns a start button which starts a test
    private void addStartButton(int spaceBefore, int spaceAfter) {
        add(Box.createVerticalStrut(spaceBefore));

        JButton startButton = new JButton("Start");
        startButton.setFont(BUTTON_FONT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            controller.startButtonPressed();
        });
        add(startButton);

        add(Box.createVerticalStrut(spaceAfter));
    }


    // EFFECTS: Returns a graph button which shows the graph
    private void addGraphButton(int spaceBefore, int spaceAfter) {
        add(Box.createVerticalStrut(spaceBefore));

        JButton graphButton = new JButton("Show Graph");
        graphButton.setFont(BUTTON_FONT);
        graphButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        graphButton.addActionListener(e -> {
            controller.graphButtonPressed();
        });
        add(graphButton);

        add(Box.createVerticalStrut(spaceAfter));
    }

    // EFFECTS: Returns a save button which saves statistics
    private void addSaveButton(int spaceBefore, int spaceAfter) {
        add(Box.createVerticalStrut(spaceBefore));

        JButton saveButton = new JButton("Save");
        saveButton.setFont(BUTTON_FONT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> {
            controller.saveButtonPressed();
        });
        add(saveButton);

        add(Box.createVerticalStrut(spaceAfter));
    }

    // EFFECTS: Returns a load button which writes statistics
    private void addLoadButton(int spaceBefore, int spaceAfter) {
        add(Box.createVerticalStrut(spaceBefore));

        JButton loadButton = new JButton("Load");
        loadButton.setFont(BUTTON_FONT);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.addActionListener(e -> {
            controller.loadButtonPressed();
        });
        add(loadButton);

        add(Box.createVerticalStrut(spaceAfter));
    }

    // EFFECTS: Returns a quit button which quits the application
    private void addQuitButton(int spaceBefore, int spaceAfter) {
        add(Box.createVerticalStrut(spaceBefore));

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(BUTTON_FONT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(e -> {
            controller.quitButtonPressed();
        });
        add(quitButton);

        add(Box.createVerticalStrut(spaceAfter));
    }
}
