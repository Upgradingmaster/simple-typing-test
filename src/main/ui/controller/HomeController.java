package ui;

import persistence.StateWriter;
import ui.DialogBoxView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import persistence.StateReader;

public class HomeController {

    private Services services;
    private MainView parentView;

    HomeController(MainView parentView, Services services) {
        this.services = services;
        this.parentView = parentView;
    }

    public void startButtonPressed() {
        DialogBoxView dialogBoxView = new DialogBoxView((JFrame) SwingUtilities.getWindowAncestor(parentView));
        if (dialogBoxView.getValid()) {
            parentView.showTestView();
            parentView.getTestView().startNewTestSequence(dialogBoxView.getWord(), dialogBoxView.getTime());
        }

    }

    public void graphButtonPressed() {
        parentView.showGraphView();
    }


    public void saveButtonPressed() {
        services.getPersistenceService().save();
    }

    public void loadButtonPressed() {
        services.getPersistenceService().load();
    }

    public void quitButtonPressed() {
        System.exit(0);
    }
}
