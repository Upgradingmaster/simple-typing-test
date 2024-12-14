package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import model.Statistics;
import model.Statistic;

import model.State;
import model.Services;

// An application to test typing with random sentences and review statistics of tests
public class SimpleTypingTestGui {
    private State state;

    /*
     * MODIFIES: this
     * EFFECTS: starts the main appplication frame
     */
    public SimpleTypingTestGui() {
        state = new State();
        Services services = new Services(state);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(state, services);
            frame.setVisible(true);
        });
    }
}
