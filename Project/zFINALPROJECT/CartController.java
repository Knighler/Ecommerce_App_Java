import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class CartController extends MainController implements Initializable {
    private static Customer customer;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button checkOutButton;

    @FXML
    private TableView<Pair<Product, Integer>> cartTable;

    @FXML
    private TableColumn<Pair<Product, Integer>, String> nameCol;

    @FXML
    private TableColumn<Pair<Product, Integer>, Integer> quantityCol;

    @FXML
    private TableColumn<Pair<Product, Integer>, String> priceCol;

    @FXML
    private TableColumn<Pair<Product, Integer>, Void> removeCol;

    @FXML
    private TableColumn<Pair<Product, Integer>, Void> removeOneCol;

    @FXML
    private TextField totalPrice;

    private ObservableList<Pair<Product, Integer>> cartItems;


    public void switchToHome(ActionEvent event) throws IOException {
        CustomerHomePageSceneController.setCustomer(customer);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerHomePageScene.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProducts(ActionEvent event) throws IOException {
        CustomerProductsSceneController.setProducts(Database.getAllProducts());
        CustomerProductsSceneController.setCustomer(customer);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerProductsScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProfile(MouseEvent event) throws IOException {
        AccountPageController.setCustomer(customer);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ProfileScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

        removeCol.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Remove");

            {
                btn.setOnAction(event -> {
                    try {
                        Pair<Product, Integer> product = getTableView().getItems().get(getIndex());
                        getTableView().getItems().remove(product);
                        customer.getCart().removeProduct(product.getKey().getName());
                        customer.getCart().calculate_price();
                        totalPrice.setText(String.format("$%.2f",customer.getCart().getTotalprice()));
                    } catch (ItemNotFoundException e) {
                        System.out.println("In table Remove column Buttons: " +e.getMessage());
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        removeOneCol.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Remove one");

            {
                btn.setOnAction(event -> {
                    try {
                        Pair<Product, Integer> product = getTableView().getItems().get(getIndex());
                        if (product.getValue() > 1) {
                            customer.getCart().decreaseProductAmountByOne(product.getKey().getName());
                            getTableView().getItems().set(getIndex(),customer.getCart().returnProductsPair(product.getKey()));
                            customer.getCart().calculate_price();
                            totalPrice.setText(String.format("$%.2f", customer.getCart().getTotalprice()));
                        }
                        else{
                            getTableView().getItems().remove(product);
                            customer.getCart().decreaseProductAmountByOne(product.getKey().getName());
                            customer.getCart().calculate_price();
                            totalPrice.setText(String.format("$%.2f", customer.getCart().getTotalprice()));
                        }
                    } catch (ItemNotFoundException e) {
                        System.out.println("In table Remove column Buttons: " +e.getMessage());
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        // Initialize the TableView with the cart's data
        cartItems = FXCollections.observableArrayList(customer.getCart().getProducts());
        cartTable.setItems(cartItems);

    }

    public void checkOutButton(ActionEvent e) throws IOException {
        if (customer.getCart().isEmpty()){
            System.out.println("Cart is Empty");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert!");
            alert1.setContentText("Cart is Empty");
            alert1.show();
        }
        else {
            OrderController.setCustomer(customer);
            root = FXMLLoader.load(getClass().getResource("OrderPage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }



    public static void setCustomer(Customer customer) {
        CartController.customer = customer;
    }
}
