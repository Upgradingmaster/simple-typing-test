package ui;

public class TestController {
    private TestService service;
    private MainView parentView;

    private Test testInstance;

    TestController(MainView parentView, Services services) {
        this.service = services.getTestService();
        this.parentView = parentView;
    }

    public void newTestInstance(int wordLimit, int timeLimit, TestView testView) {
        this.testInstance = service.newTestInstance(wordLimit, timeLimit, testView);
    }

    public void beginTest() {
        service.beginTest();
    }

    public void testEnded(String userSentence) {
        service.onEnd(userSentence); //Model logic
        parentView.showHomeView();

    } 

    public String getExpectedSentence() {
        return testInstance.getRandomSentence();
    }



    public int getTotalDuration() {
        return testInstance.getTimeLimit();
    }


}
