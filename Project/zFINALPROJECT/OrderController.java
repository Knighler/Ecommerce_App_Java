import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class OrderController extends MainController implements EventHandler<ActionEvent>, Initializable {
    private static Customer customer;
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private TableView<Pair<Product, Integer>> cartTable;

    @FXML
    private TableColumn<Pair<Product, Integer>, String> nameCol;

    @FXML
    private TableColumn<Pair<Product, Integer>, Integer> quantityCol;

    @FXML
    private TableColumn<Pair<Product, Integer>, String> priceCol;

    @FXML
    private TextField totalPrice;

    @FXML
    private RadioButton pRadioButton;

    @FXML
    private RadioButton vRadioButton;

    private ObservableList<Pair<Product, Integer>> cartItems;

    @Override
    public void handle(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalPrice.setEditable(false);
        customer.getCart().calculate_price();
        totalPrice.setText(String.format("$%.2f",customer.getCart().getTotalprice()));

        // Initialize the table columns
        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getKey().getName()));

        quantityCol.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getValue()));

        priceCol.setCellValueFactory(data ->
                new SimpleStringProperty(String.format("$%.2f", data.getValue().getKey().getPrice())));

        // Initialize the TableView with the cart's data
        cartItems = FXCollections.observableArrayList(customer.getCart().getProducts());
        cartTable.setItems(cartItems);

    }

    public void cancelOrderButton(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void placeOrderButton(ActionEvent e) throws IOException {
        if (pRadioButton.isSelected()) {
            Order order = new Order(customer.getUsername(), "PayPal", customer.getCart().getTotalprice(), customer.getCart().getProducts());
            Database.addOrder(order);
            customer.getCart().clearCart();
            CustomerHomePageSceneController.setCustomer(customer);
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerHomePageScene.fxml")));
            stage= (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (vRadioButton.isSelected()) {
            Order order = new Order(customer.getUsername(), "Visa", customer.getCart().getTotalprice(), customer.getCart().getProducts());
            Database.addOrder(order);
            customer.getCart().clearCart();
            CustomerHomePageSceneController.setCustomer(customer);
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerHomePageScene.fxml")));
            stage= (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("No payment Method Selected");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert!");
            alert1.setContentText("No Payment Method Selected");
            alert1.show();
        }
    }

    public static void setCustomer(Customer customer) {
        OrderController.customer = customer;
    }
}
