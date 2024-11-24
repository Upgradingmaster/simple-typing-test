package ui;


import model.Logger;

public class StatisticButtonController {

    private Logger loggerService;
    private int id;

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

