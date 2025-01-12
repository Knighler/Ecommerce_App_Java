import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountPageController extends MainController implements EventHandler<ActionEvent>, Initializable {
    @Override
    public void handle(ActionEvent actionEvent) {

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static Customer customer;

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        AccountPageController.customer = customer;
    }

    @FXML
    private Button ProductsButton;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField balanceTextField;

    @FXML
    private TextField dateOfBirthTextField;

    @FXML
    private Button homeButton;

    @FXML
    private TextArea interestsTextArea;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ImageView profilePic;

    @FXML
    private TextField usernameTextField;

    public void usernameButton(){
        String username = usernameTextField.getText();
        Database.updateUsername(customer,username);
        System.out.println("New username: " + customer.getUsername());
    }

    public void passwordButton() {
        String password = passwordTextField.getText();
        // \\d means contains a Number and \\D means doesn't contain a Number
        if (!password.matches(".*\\d.*") || !password.matches(".*\\D.*") || password.length() < 8) {
            System.out.println("Password must Be longer than 8 Characters and have Letters and Numbers");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert!");
            alert1.setContentText("Password must Be longer than 8 Characters and have Letters and Numbers");
            alert1.show();
        }
        else{
            Database.updatePassword(customer, password);
            System.out.println("New password: " + customer.getPassword());
        }
    }

    public void dateOfBirthButton(){
        String dateOfBirth = dateOfBirthTextField.getText();
        if (!dateOfBirth.matches("\\d{4}[-\\s]\\d{1,2}[-\\s]\\d{1,2}")){
            System.out.println("Enter 3 Values");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert!");
            alert1.setContentText("Enter 3 Integers Without a Space at the Start in the Format YYYY MM DD");
            alert1.show();
        }
        else {
            String[] dates = dateOfBirth.split("[-\\s]");
            int year = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[2]);
            if (year < 1980 || year > 2024 || month < 1 || month > 12 || day < 1 || day > 31) {
                System.out.println("Enter a Correct date separated by single spaces in the Format YYYY MM DD");
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Alert!");
                alert1.setContentText("Enter a Correct date separated by single spaces in the Format YYYY MM DD");
                alert1.show();
            } else {
                Database.updateDateOfBirth(customer, year + "-" + month + "-" + day);
                dateOfBirthTextField.setText(customer.getDateOfBirth());
                System.out.println("New Birth Date: " + customer.getDateOfBirth());
            }
        }
    }

    public void addressButton(){
        String address = addressTextField.getText();
        //.*[a-zA-Z]+.* checks if there are any Letters in the Address
        if (address.isEmpty() || !address.matches(".*[a-zA-Z]+.*")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert!");
            alert1.setContentText("Enter a Correct Address");
            alert1.show();
        }
        else {
            Database.updateCustomerAddress(customer, address);
            System.out.println("New Address: " + customer.getAddress());
        }
    }

    public void balanceButton(){
        double balance = Double.valueOf(balanceTextField.getText());
        if (balance < 0){
            System.out.println("Please Enter a Valid Balance");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert!");
            alert1.setContentText("Please Enter a Valid Balance");
            alert1.show();
        }
        else {
            Database.updateCustomerBalance(customer,balance);
            System.out.println("New Balance: " + customer.getBalance());
            balanceTextField.setText(String.valueOf(customer.getBalance()));
        }
    }

    public void interestsButton(){
        // $ means end of line and \\s+ means any spaces
        String[] interests = interestsTextArea.getText().split("[$+\\s+]");
        Database.updateCustomerInterests(customer,interests);
        String s1 = "";
        for (int i = 0 ; i < customer.getInterests().length; i++){
            s1 = s1.concat(customer.getInterests()[i] + "\n");
        }
        interestsTextArea.setText(s1);
    }

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

    public void switchToCart(ActionEvent event) throws IOException{
        CartController.setCustomer(customer);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CartPage.fxml")));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameTextField.setText(customer.getUsername());
        passwordTextField.setText(customer.getPassword());
        dateOfBirthTextField.setText(customer.getDateOfBirth());
        addressTextField.setText(customer.getAddress());
        balanceTextField.setText(String.valueOf(customer.getBalance()));
        String s1 = "";
        for (int i = 0 ; i < customer.getInterests().length; i++){
            s1 = s1.concat(customer.getInterests()[i] + "\n");
        }
        interestsTextArea.setText(s1);
    }
}
