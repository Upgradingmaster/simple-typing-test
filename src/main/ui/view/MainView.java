package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

// Represents the main view which will either be the main menu or the graph
public class MainView extends JPanel {
    private HomeView homeView;
    private GraphView graphView;
    private CardLayout cardLayout;

    MainView() {
        initViews();
        initCardLayout();
    }

    //EFFECTS: Defines the two child views, Home and Graph
    private void initViews() {
        homeView = new HomeView();
        graphView = new GraphView();
    }

    // EFFECTS: Registers the two views into the card layout
    //          showing Home as default
    private void initCardLayout() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        this.add(homeView, "Home");
        this.add(graphView, "Graph");
    }

    // EFFECTS: Sets the view to home 
    public void showHomeView() {
        cardLayout.show(this, "Graph");
    }

    // EFFECTS: Sets the view to graph
    public void showGraphView() {
        cardLayout.show(this, "Graph");
    }

    // EFFECTS: Toggles the view from home to graph and vice versa
    public void toggleView() {
        cardLayout.next(this);
    }
}
