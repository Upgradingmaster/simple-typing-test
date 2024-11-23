package ui;

import org.jfree.chart.ChartPanel;  

import model.Statistics;  

public class GraphController {
    private Services services;
    private MainView parentView;

    GraphController(MainView parentView, Services services) {
        this.services = services;
        this.parentView = parentView;
    }


    public ChartPanel getChartPanel(Statistics newState) {
        return new ChartPanel(services.getGraphService().getChart(newState));
    }

    public void backButtonPressed() {
        parentView.showHomeView();
    }
}
