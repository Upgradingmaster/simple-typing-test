package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Services;

// Represents the main view which will either be the main menu, the graph, or the test
public class MainView extends JPanel {

    private CardLayout cardLayout;
    private HomeView homeView;
    private GraphView graphView;

    private TestView testView;


    private JButton switchButton;

    // EFFECTS: Adds the two views, initializes a card layout with the main three views
    MainView(Services services) {
        initViews(services);
        setupCardLayout();
    }


    //EFFECTS: Defines the two child views, Home and Graph
    private void initViews(Services services) {
        homeView = new HomeView(new HomeController(this, services));
        graphView = new GraphView(new GraphController(this, services));
        testView = new TestView(new TestController(this, services));
    }

    // EFFECTS: Registers the two views into the card layout
    //          showing Home as default
    private void setupCardLayout() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.add(homeView, "Home");
        this.add(graphView, "Graph");
        this.add(testView, "Test");
    }

    // EFFECTS: Sets the view to home 
    public void showHomeView() {
        cardLayout.show(this, "Home");
    }

    // EFFECTS: Sets the view to graph
    public void showGraphView() {
        cardLayout.show(this, "Graph");
    }

    // EFFECTS: Sets the view to the test
    public void showTestView() {
        cardLayout.show(this, "Test");
    }

    public GraphView getGraphView() {
        return graphView;
    }

    public TestView getTestView() {
        return testView;
    }
}
