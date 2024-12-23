package ui;


import model.TestService;
import model.Test;
import model.Services;

// Routes user interactions during the test to the service
class TestController {
    private TestService service;
    private MainView parentView;

    private Test testInstance;


    // EFFECTS: Initializes a TestController with the MainView and the needed services
    TestController(MainView parentView, Services services) {
        this.service = services.getTestService();
        this.parentView = parentView;
    }

    // EFFECTS: Creates a new test instance with the given parameters
    //          Passes testView to the service for the service to send the termination signal to the view
    public void newTestInstance(int wordLimit, int timeLimit, TestView testView) {
        this.testInstance = service.newTestInstance(wordLimit, timeLimit, testView);
    }

    // EFFECTS: Start the test
    public void beginTest() {
        service.beginTest();
    }

    // EFFECTS: Ends the test 
    //          Switches to the HomeView 
    public void testEnded(String userSentence) {
        service.onEnd(userSentence); 
        parentView.showHomeView();

    } 

    public String getExpectedSentence() {
        return testInstance.getRandomSentence();
    }

    public int getTotalDuration() {
        return testInstance.getTimeLimit();
    }


}
