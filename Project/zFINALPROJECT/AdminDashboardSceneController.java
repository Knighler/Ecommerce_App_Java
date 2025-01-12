import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminDashboardSceneController extends MainController {

    @FXML
    private TextField searchField;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxisLineChart;
    @FXML
    private NumberAxis yAxisLineChart;
    @FXML
    private PieChart genderPieChart;

    public void handleTextFieldActionAux() {
        System.out.println(searchField);
        setSearchField(searchField);
        handleTextFieldAction();
    }

    public void initialize() {
        System.out.println("Initializing Bar Chart...");

        // Step 1: Group customers by 5-year intervals
        Map<String, Integer> yearGroups = new HashMap<>();
        for (Users user : Database.getAllUsers()) {
            if (user instanceof Customer) {
                String dob = ((Customer) user).getDateOfBirth();
                String yearString = dob.substring(0, 4); // Extract year as a string
                int year = Integer.parseInt(yearString); // Convert to integer

                // Calculate the 5-year range
                int startYear = (year / 5) * 5; // Find start of the 5-year range
                String range = startYear + "-" + (startYear + 4); // Create the range string
                yearGroups.put(range, yearGroups.getOrDefault(range, 0) + 1);
            }
        }

        // Step 2: Create Chart Data Series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Customers by Year Range");
        for (Map.Entry<String, Integer> entry : yearGroups.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Step 3: Configure Bar Chart
        barChart.getData().clear();
        barChart.getData().add(series);

        // Step 4: Configure Y-Axis Scaling
        yAxis.setAutoRanging(false); // Disable auto-scaling
        yAxis.setLowerBound(0); // Set lower bound
        yAxis.setUpperBound(getMaxValue(yearGroups) + 5); // Set upper bound (adding some padding)
        yAxis.setTickUnit(5); // Set interval between ticks
    
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Products per Category");

        for (Category category : Database.getAllCategories()) {
            String categoryName = category.getCatName();
            int productCount = category.getListOfProducts().size();
            series2.getData().add(new XYChart.Data<>(categoryName, productCount));
        }

        lineChart.getData().clear();
        lineChart.getData().add(series2);

        yAxisLineChart.setAutoRanging(false);
        yAxisLineChart.setLowerBound(0);
        yAxisLineChart.setUpperBound(getMaxProductCount() + 5);
        yAxisLineChart.setTickUnit(5);
    
       
    }
    private int getMaxProductCount() {
        return Database.getAllCategories().stream()
                .mapToInt(category -> category.getListOfProducts().size())
                .max()
                .orElse(0);
    }


    private int getMaxValue(Map<String, Integer> yearGroups) {
        int max = 0;
        for (int count : yearGroups.values()) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }


    
    
    
    
    }

  