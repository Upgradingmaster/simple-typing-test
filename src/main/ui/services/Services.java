package ui;

import model.Logger;

import model.State;

// An abstraction for holding services for DI
class Services {
    private PersistenceService persistenceService;
    private TestService testService;
    private GraphService graphService;
    private Logger logger;


    public Logger getLogger() {
        return logger;
    }

    public GraphService getGraphService() {
        return graphService;
    }

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public TestService getTestService() {
        return testService;
    }

    Services(State state) {
        persistenceService = new PersistenceService(state);
        testService = new TestService(state);
        graphService = new GraphService(state);
        logger = new Logger();
    }
}

