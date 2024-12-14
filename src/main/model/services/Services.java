package model;

// An abstraction for holding services for DI
public class Services {
    private PersistenceService persistenceService;
    private TestService testService;
    private GraphService graphService;
    private LoggerService loggerService;

    public GraphService getGraphService() {
        return graphService;
    }

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public TestService getTestService() {
        return testService;
    }

    public LoggerService getLogger() {
        return loggerService;
    }

    // EFFECTS: Initializes with the services to be 
    //          dependency injected throughout the project
    public Services(State state) {
        persistenceService = new PersistenceService(state);
        testService = new TestService(state);
        graphService = new GraphService(state);
        loggerService = new LoggerService();
    }

}

