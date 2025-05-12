package UI.GestionValores;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class GraficoValoresPanel extends JPanel {

    public GraficoValoresPanel(List<ValorSensor> valores, String nombreSensor) {
        setLayout(new BorderLayout());

        TimeSeries series = new TimeSeries("Valores");

        for (ValorSensor valor : valores) {
            // Suponiendo que valor.getFecha() es un java.util.Date
            series.add(new Day(valor.getFecha()), valor.getValor());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Histórico de valores - " + nombreSensor,
                "Fecha",
                "Valor",
                dataset,
                false,
                true,
                false
        );

        // Estilo del eje de fechas
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis ejeFecha = (DateAxis) plot.getDomainAxis();
        ejeFecha.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy"));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);

        add(chartPanel, BorderLayout.CENTER);
    }
}
