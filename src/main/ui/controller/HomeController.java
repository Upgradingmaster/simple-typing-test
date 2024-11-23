package ui;

import persistence.StateWriter;
import ui.DialogBoxView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import persistence.StateReader;

// Handles user interaction with the HomeView
public class HomeController {

    private Services services;
    private MainView parentView;

    HomeController(MainView parentView, Services services) {
        this.services = services;
        this.parentView = parentView;
    }

    // EFFECTS: Spawns a popup requesting wordCount and timeLimit for the test
    //          Then switching to the testView and starting the test sequence
    //
    //          This blocks the main frame until valid values are received
    public void startButtonPressed() {
        DialogBoxView dialogBoxView = new DialogBoxView((JFrame) SwingUtilities.getWindowAncestor(parentView));
        if (dialogBoxView.getValid()) {
            parentView.showTestView();
            parentView.getTestView().startNewTestSequence(dialogBoxView.getWord(), dialogBoxView.getTime());
        }

    }

    // EFFECTS: Switches to the GraphView
    public void graphButtonPressed() {
        parentView.showGraphView();
    }

    // EFFECTS: Saves the application via the service method
    public void saveButtonPressed() {
        services.getPersistenceService().save();
    }

    // EFFECTS: Loads the application via the service method
    public void loadButtonPressed() {
        services.getPersistenceService().load();
    }

    // EFFECTS: Ends the application
    public void quitButtonPressed() {
        System.exit(0);
    }
}
