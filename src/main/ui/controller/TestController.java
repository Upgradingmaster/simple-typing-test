package ui;


public class TestController {
    private Services services;
    private MainView parentView;

    TestController(MainView parentView, Services services){
        this.services = services;
        this.parentView = parentView;
    }
}
