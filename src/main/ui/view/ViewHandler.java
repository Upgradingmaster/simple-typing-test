package ui;

public class ViewHandler {
    enum GraphState {
        ON, OFF
    }

    public GraphState graphState;


    // EFFECTS: Swaps the Main Panel with the GraphView or the MainMenuView
    public void toggleGraph() {
        if (graphState == GraphState.ON) {
            renderGraph();
        } else renderMainMenu();
        graphState = !graphState;
    }


    // REQUIRES: graphState == GraphState.OFF
    // EFFECTS: Sets the main panel to display the main menu
    private void renderMainMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderMainMenu'");
    }


    // REQUIRES: graphState == GraphState.ON
    // EFFECTS: Sets the main panel to display the graph
    private void renderGraph() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderGraph'");
    }
}
