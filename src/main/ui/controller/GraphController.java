package ui;

import org.jfree.chart.ChartPanel;  

import model.Statistics;  
import model.Services;  

// Handles user interaction with the GraphView
public class GraphController {
    private Services services;
    private MainView parentView;

    // EFFECTS: Initializes a GraphController with the MainView and the needed services
    GraphController(MainView parentView, Services services) {
        this.services = services;
        this.parentView = parentView;
    }

    // EFFECTS: Gets the Chart from the service
    //          wraps the chart in a ChartPanel and returns it
    public ChartPanel getChartPanel(Statistics newState) {
        return new ChartPanel(services.getGraphService().getChart(newState));
    }

    // EFFECTS: Switches to the HomeView
    public void backButtonPressed() {
        parentView.showHomeView();
    }
}
