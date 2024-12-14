package ui;


import model.LoggerService;
import model.Services;

public class StatisticButtonController {

    private LoggerService loggerService;
    private int id;

    // EFFECTS: Initializes a StatisticButtonController with the 
    //          needed services and the id of the attached button
    StatisticButtonController(Services services, int id) {
        this.loggerService = services.getLogger();
        this.id = id;
    }

    public void hideDesc() {
        loggerService.log(">> Hiding Description for button number: " + id);
    }

    public void showDesc() {
        loggerService.log(">> Showing Description for button number: " + id);
    }
}

