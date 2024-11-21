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
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        mainView = new MainView();
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


    // EFFECTS: Swaps the Main Panel with the GraphView or the MainMenuView
    public void toggleGraph() {
        if (graphState) {
            renderMainMenu();
        } else {
            renderGraph();
        }
        graphState = !graphState;
    }


    // REQUIRES: !graphState
    // EFFECTS: Sets the main panel to display the main menu
    private void renderMainMenu() {

    }


    // REQUIRES: graphState
    // EFFECTS: Sets the main panel to display the graph
    private void renderGraph() {

    }


    // EFFECTS: Handles ui updates for child views whenever state changes 
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        switch (pce.getPropertyName()){
            case "statsIncremental+":
                updateFrameAddStat((Statistic) pce.getNewValue());
                break;
            case "statsIncremental-":
                updateFrameRemoveStat((Integer) pce.getNewValue());
                break;
            case "statsDefinite":
                updateFrameSetStats((Statistics) pce.getNewValue());
                break;
        }
        reloadViews();
    }

    // EFFECTS: Adds the Statistic Button at the end of the view
    private void updateFrameAddStat(Statistic statistic) {
        this.statisticView.addStatisticButton(statistic);
    }

    // EFFECTS: Removes the Statistic Button at the index
    private void updateFrameRemoveStat(int index) {
        this.statisticView.removeStatisticButton(index);
    }

    // EFFECTS: Sets the Buttons to match new Statistics object
    private void updateFrameSetStats(Statistics statistics) {
        this.statisticView.setStatisticsButton(statistics);
    }
}
