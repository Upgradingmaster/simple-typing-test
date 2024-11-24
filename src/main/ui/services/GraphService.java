package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.plot.CategoryPlot;

import model.Statistic;  
import model.Statistics;  

import model.State;

// Handles Graph logic and generation
class GraphService {
    private State state;

    GraphService(State state) {
        this.state = state;
    }

    // EFFECTS: Creates the JFreeChart with the given statistics
    public JFreeChart getChart(Statistics newState) {  
        Statistics statistics = state.getStats();
        if (newState != null) {
            statistics = newState;
        }

        DefaultCategoryDataset dataset = createDataset(statistics);  
        JFreeChart chart = ChartFactory.createLineChart(
                "Progress", // Chart title  
                "Past Attempts", // X-Axis Label  
                "WPM", // Y-Axis Label  
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);  

        customizeChart(chart);
        return chart;
    }  


    // EFFECTS: Applies customization to the chart
    private void customizeChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(5.0f));
        plot.setRenderer(renderer);

        plot.setOutlinePaint(Color.GRAY);
        plot.setOutlineStroke(new BasicStroke(1.0f));

        plot.setBackgroundPaint(Color.WHITE);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
    }


    // EFFECTS: Builds the "DefaultCategoryDataset" from the statistics 
    //          to pass to the chartfactory
    public DefaultCategoryDataset createDataset(Statistics s) {
        ArrayList<Statistic> stats = s.getStatsArrayList();
        List<Integer> wpmY = stats.stream().map(Statistic::getWpm).collect(Collectors.toList());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  

        for (int i = 0; i < wpmY.size(); i++) { 
            dataset.addValue(wpmY.get(i), "", "" + (i + 1));
        }

        return dataset;

    }

}

