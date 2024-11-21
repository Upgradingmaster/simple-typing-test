package ui;


public class TestController {
    private Services state;
    private MainView parentView;

    TestController(MainView parentView, Services state){
        this.state = state;
        this.parentView = parentView;
    }
}
