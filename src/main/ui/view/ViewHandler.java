package ui;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.Statistic;
import model.Statistics;

// Top level Handler for managing state and user interaction with the various views
class ViewHandler implements PropertyChangeListener {

    private enum MainViewState {
        HOME, GRAPH, TEST
    }

    private MainViewState mainViewState;

    //private boolean graphState = false;
    private JFrame frame;

    private MainView mainView;
    private StatisticsView statisticView; 
    private JSplitPane splitPane; 

    ViewHandler(JFrame frame) {
        mainViewState = MainViewState.HOME;
        this.frame = frame;
    }

    // EFFECTS: Initializes the primary child views
    public void initViews(Services services) {
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        mainView = new MainView(services);
        statisticView = new StatisticsView(new StatisticsController());

        mainView.setMinimumSize(new Dimension((int)(frame.getWidth() * 0.5), frame.getHeight()));
        statisticView.setMinimumSize(new Dimension((int)(frame.getWidth() * 0.2), frame.getHeight()));

        //main.setBorder(new LineBorder(Color.RED, 2));
        //statistic.setBorder(new LineBorder(Color.RED, 2));
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainView, statisticView);
        splitPane.setResizeWeight(0.8);
        splitPane.setOneTouchExpandable(true);

        frame.add(splitPane);
    }

    // EFFECTS: Reloads all the views with latest ui changes
    public void reloadViews(){
        frame.revalidate();
        frame.repaint();
    }


    //// EFFECTS: Swaps the Main Panel with the GraphView or the MainMenuView
    //public void toggleGraph() {
    //    switch (mainViewState) {
    //        case HOME: 
    //            mainView.showHomeView(); 
    //            break;
    //        case GRAPH: 
    //            mainView.showHomeView(); 
    //            break;
    //        case  
    //            mainView.showHomeView(); 
    //            break;
    //
    //    }
    //    if (graphState) {
    //        mainView.showHomeView();
    //    } else {
    //        mainView.showGraphView();
    //    }
    //    graphState = !graphState;
    //}
    //
    //
    //// REQUIRES: !graphState
    //// EFFECTS: Sets the main panel to display the main menu
    //private void renderMainMenu() {
    //
    //}
    //
    //
    //// REQUIRES: graphState
    //// EFFECTS: Sets the main panel to display the graph
    //private void renderGraph() {
    //
    //}


    // EFFECTS: Handles ui updates for child views whenever state changes 
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        Object newVal =  pce.getNewValue();
        Object oldVal =  pce.getOldValue();

        switch (pce.getPropertyName()){
            case "statsIncremental+":
                updateFrameAddStat((Statistics) oldVal, (Statistic) newVal);
                break;
            case "statsDefinite":
                updateFrameSetStats((Statistics) newVal);
                break;
        }
        reloadViews();
    }
    
    // EFFECTS: Adds the Statistic Button at the end of the view
    private void updateFrameAddStat(Statistics prevStats, Statistic newStat) {
        this.statisticView.addStatisticButton(newStat);
        Statistics prevCopy = new Statistics();
        // TODO: Add iterator for statistics
        for(Statistic s : prevStats.getStatsArrayList()) {
           prevCopy.addStat(s);
        }
        prevCopy.addStat(newStat);
        this.mainView.getGraphView().redrawGraph(prevCopy);
    }

    // EFFECTS: Sets the Buttons to match new Statistics object
    private void updateFrameSetStats(Statistics statistics) {
        this.statisticView.setStatisticsButton(statistics);
        this.mainView.getGraphView().redrawGraph(statistics);
    }
}
