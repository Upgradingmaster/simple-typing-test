package ui;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.Statistic;
import model.Statistics;

// Top level Handler for managing state and user interaction with the various view
class ViewHandler implements PropertyChangeListener {
    private boolean graphState = false;
    private JFrame frame;

    private MainView mainView;
    private StatisticsView statisticView; 
    private JSplitPane splitPane; 

    ViewHandler(JFrame frame) {
        this.frame = frame;
    }

    // EFFECTS: Initializes the primary child views
    public void initViews() {
        
    }

    // EFFECTS: Reloads all the views with latest ui changes
    public void reloadViews(){
       
    }


    // EFFECTS: Swaps the Main Panel with the GraphView or the MainMenuView
    public void toggleGraph() {
        
    }


    // REQUIRES: graphState == GraphState.OFF
    // EFFECTS: Sets the main panel to display the main menu
    private void renderMainMenu() {

    }


    // REQUIRES: graphState == GraphState.ON
    // EFFECTS: Sets the main panel to display the graph
    private void renderGraph() {

    }


    // EFFECTS: Handles ui updates for child views whenever state changes 
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        
    }

    // EFFECTS: Adds the Statistic Button at the end of the view
    private void updateFrameAddStat(Statistic statistic) {
    }

    // EFFECTS: Removes the Statistic Button at the index
    private void updateFrameRemoveStat(int index) {
    }

    // EFFECTS: Sets the Buttons to match new Statistics object
    private void updateFrameSetStats(Statistics statistics) {
    }
}
