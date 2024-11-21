package ui;

import persistence.StateWriter;
import persistence.StateReader;

public class HomeController {

    private Services services;
    private MainView parentView;

    HomeController(MainView parentView, Services services){
        this.services = services;
        this.parentView = parentView;
    }

    public void startButtonPressed() {
         
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
