package ui;

public class GraphController {
    private Services state;
    private MainView parentView;

    GraphController(MainView parentView, Services state){
        this.state = state;
        this.parentView = parentView;
    }

}
