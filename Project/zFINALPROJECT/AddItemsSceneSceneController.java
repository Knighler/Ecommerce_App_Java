import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class AddItemsSceneSceneController extends MainController{

    @FXML private VBox AddItemsSceneVbox;
    @FXML private TextField searchField;

    
    private String choice;
    private ArrayList<TextField> textFields = new ArrayList<>(); // Create an ArrayList to store TextField objects
    

    public void setChoice(String choice){
        this.choice = choice;
        displayContent();
    }

    public void handleTextFieldActionAux(){
    setSearchField(searchField);
    handleTextFieldAction();
}

    


    public void displayContent(){
        // Clear the list at the beginning of the method
        textFields.clear();

        AddItemsSceneVbox.getChildren().clear();

        
        switch(choice){

            case "AddCategoryButton":
                // Create HBox and TextField for Category
                HBox AddCategoryButtonhbox = new HBox(10);
                AddCategoryButtonhbox.setId("catNameHbox");
                AddCategoryButtonhbox.setAlignment(Pos.CENTER_LEFT);
                AddCategoryButtonhbox.setStyle("-fx-padding: 20;");
                
                Label catName = new Label("Name:");
                TextField catNamTextField = new TextField();
                textFields.add(catNamTextField);  // Add TextField to ArrayList
                
                catName.setId("catNameLabel");
                catName.getStyleClass().add("AddItemsSceneLabels");
                catNamTextField.setId("catNamTextField");
                catNamTextField.getStyleClass().add("AddItemsSceneTextFields");
                
                AddCategoryButtonhbox.getChildren().addAll(catName, catNamTextField);
                AddItemsSceneVbox.getChildren().addAll(AddCategoryButtonhbox);
                break;

            case "AddProductButton":
                // Create HBoxes and TextFields for Product
                HBox addProductButtonCategoryNameHbox = new HBox(10);
                addProductButtonCategoryNameHbox.setId("ProductNameHbox");
                addProductButtonCategoryNameHbox.setAlignment(Pos.CENTER_LEFT);
                addProductButtonCategoryNameHbox.setStyle("-fx-padding: 20;");

                HBox addProductButtonNameHbox = new HBox(10);
                addProductButtonNameHbox.setId("ProductNameHbox");
                addProductButtonNameHbox.setAlignment(Pos.CENTER_LEFT);
                addProductButtonNameHbox.setStyle("-fx-padding: 20;");

                HBox addProductButtonPriceHbox = new HBox(10);
                addProductButtonPriceHbox.setId("ProductPriceHbox");
                addProductButtonPriceHbox.setAlignment(Pos.CENTER_LEFT);
                addProductButtonPriceHbox.setStyle("-fx-padding: 20;");

                HBox addProductButtonQuantityHbox = new HBox(10);
                addProductButtonQuantityHbox.setId("ProductPriceHbox");
                addProductButtonQuantityHbox.setAlignment(Pos.CENTER_LEFT);
                addProductButtonQuantityHbox.setStyle("-fx-padding: 20;");

                Label productCategoryName = new Label("Category Name:");
                TextField productCategoryNameTextField = new TextField();
                textFields.add(productCategoryNameTextField);  // Add TextField to ArrayList

                Label productName = new Label("Product Name:");
                TextField productNameTextField = new TextField();
                textFields.add(productNameTextField);  // Add TextField to ArrayList

                Label productPrice = new Label("Product Price:");
                TextField productPriceTextField = new TextField();
                textFields.add(productPriceTextField);  // Add TextField to ArrayList

                Label productQuantity = new Label("Product Quantity:");
                TextField productQuantityTextField = new TextField();
                textFields.add(productQuantityTextField);  // Add TextField to ArrayList

                // Add IDs and styles
                productCategoryName.setId("productCategoryNameLabel");
                productCategoryName.getStyleClass().add("AddItemsSceneLabels");
                productCategoryNameTextField.setId("productCategoryNameTextField");
                productCategoryNameTextField.getStyleClass().add("AddItemsSceneTextFields");

                productName.setId("productNameLabel");
                productName.getStyleClass().add("AddItemsSceneLabels");
                productNameTextField.setId("productNameTextField");
                productNameTextField.getStyleClass().add("AddItemsSceneTextFields");

                productPrice.setId("productPriceLabel");
                productPrice.getStyleClass().add("AddItemsSceneLabels");
                productPriceTextField.setId("productPriceTextField");
                productPriceTextField.getStyleClass().add("AddItemsSceneTextFields");

                productQuantity.setId("productQuantityLabel");
                productQuantity.getStyleClass().add("AddItemsSceneLabels");
                productQuantityTextField.setId("productQuantityTextField");
                productQuantityTextField.getStyleClass().add("AddItemsSceneTextFields");

                addProductButtonCategoryNameHbox.getChildren().addAll(productCategoryName, productCategoryNameTextField);
                addProductButtonNameHbox.getChildren().addAll(productName, productNameTextField);
                addProductButtonPriceHbox.getChildren().addAll(productPrice, productPriceTextField);
                addProductButtonQuantityHbox.getChildren().addAll(productQuantity, productQuantityTextField);

                AddItemsSceneVbox.getChildren().addAll(addProductButtonCategoryNameHbox, addProductButtonNameHbox, addProductButtonPriceHbox, addProductButtonQuantityHbox);
                break;

            case "AddCustomerButton":
                // Create HBoxes and TextFields for Customer
                HBox addCustomerButtonUsernameHbox = new HBox(10);
                addCustomerButtonUsernameHbox.setId("CustomerUsernameHbox");
                addCustomerButtonUsernameHbox.setAlignment(Pos.CENTER_LEFT);
                addCustomerButtonUsernameHbox.setStyle("-fx-padding: 20;");
            
                HBox addCustomerButtonDateOfBirthHbox = new HBox(10);
                addCustomerButtonDateOfBirthHbox.setId("CustomerDateOfBirthHbox");
                addCustomerButtonDateOfBirthHbox.setAlignment(Pos.CENTER_LEFT);
                addCustomerButtonDateOfBirthHbox.setStyle("-fx-padding: 20;");
            
                HBox addCustomerButtonBalanceHbox = new HBox(10);
                addCustomerButtonBalanceHbox.setId("CustomerBalanceHbox");
                addCustomerButtonBalanceHbox.setAlignment(Pos.CENTER_LEFT);
                addCustomerButtonBalanceHbox.setStyle("-fx-padding: 20;");
            
                HBox addCustomerButtonAddressHbox = new HBox(10);
                addCustomerButtonAddressHbox.setId("CustomerAddressHbox");
                addCustomerButtonAddressHbox.setAlignment(Pos.CENTER_LEFT);
                addCustomerButtonAddressHbox.setStyle("-fx-padding: 20;");
            
                HBox addCustomerButtonInterestsHbox = new HBox(10);
                addCustomerButtonInterestsHbox.setId("CustomerInterestsHbox");
                addCustomerButtonInterestsHbox.setAlignment(Pos.CENTER_LEFT);
                addCustomerButtonInterestsHbox.setStyle("-fx-padding: 20;");
            
                Label customerUsernameLabel = new Label("Username:");
                TextField customerUsernameTextField = new TextField();
                textFields.add(customerUsernameTextField);  // Add TextField to ArrayList

                Label customerDateOfBirthLabel = new Label("Date of Birth:");
                TextField customerDateOfBirthTextField = new TextField();
                textFields.add(customerDateOfBirthTextField);  // Add TextField to ArrayList

                Label customerBalanceLabel = new Label("Balance:");
                TextField customerBalanceTextField = new TextField();
                textFields.add(customerBalanceTextField);  // Add TextField to ArrayList

                Label customerAddressLabel = new Label("Address:");
                TextField customerAddressTextField = new TextField();
                textFields.add(customerAddressTextField);  // Add TextField to ArrayList

                Label customerInterestsLabel = new Label("Interests:");
                TextField customerInterestsTextField = new TextField();
                textFields.add(customerInterestsTextField);  // Add TextField to ArrayList

                customerUsernameLabel.setId("customerUsernameLabel");
                customerUsernameLabel.getStyleClass().add("AddItemsSceneLabels");
                customerUsernameTextField.setId("customerUsernameTextField");
                customerUsernameTextField.getStyleClass().add("AddItemsSceneTextFields");

                customerDateOfBirthLabel.setId("customerDateOfBirthLabel");
                customerDateOfBirthLabel.getStyleClass().add("AddItemsSceneLabels");
                customerDateOfBirthTextField.setId("customerDateOfBirthTextField");
                customerDateOfBirthTextField.getStyleClass().add("AddItemsSceneTextFields");

                customerBalanceLabel.setId("customerBalanceLabel");
                customerBalanceLabel.getStyleClass().add("AddItemsSceneLabels");
                customerBalanceTextField.setId("customerBalanceTextField");
                customerBalanceTextField.getStyleClass().add("AddItemsSceneTextFields");

                customerAddressLabel.setId("customerAddressLabel");
                customerAddressLabel.getStyleClass().add("AddItemsSceneLabels");
                customerAddressTextField.setId("customerAddressTextField");
                customerAddressTextField.getStyleClass().add("AddItemsSceneTextFields");

                customerInterestsLabel.setId("customerInterestsLabel");
                customerInterestsLabel.getStyleClass().add("AddItemsSceneLabels");
                customerInterestsTextField.setId("customerInterestsTextField");
                customerInterestsTextField.getStyleClass().add("AddItemsSceneTextFields");

                addCustomerButtonUsernameHbox.getChildren().addAll(customerUsernameLabel, customerUsernameTextField);
                addCustomerButtonDateOfBirthHbox.getChildren().addAll(customerDateOfBirthLabel, customerDateOfBirthTextField);
                addCustomerButtonBalanceHbox.getChildren().addAll(customerBalanceLabel, customerBalanceTextField);
                addCustomerButtonAddressHbox.getChildren().addAll(customerAddressLabel, customerAddressTextField);
                addCustomerButtonInterestsHbox.getChildren().addAll(customerInterestsLabel, customerInterestsTextField);

                AddItemsSceneVbox.getChildren().addAll(addCustomerButtonUsernameHbox, addCustomerButtonDateOfBirthHbox, addCustomerButtonBalanceHbox, addCustomerButtonAddressHbox, addCustomerButtonInterestsHbox);
                break;
            

                

                case "AddAdminButton":
                HBox addAdminButtonUsernameHbox = new HBox(10);
                addAdminButtonUsernameHbox.setId("AdminUsernameHbox");
                addAdminButtonUsernameHbox.setAlignment(Pos.CENTER_LEFT);
                addAdminButtonUsernameHbox.setStyle("-fx-padding: 20;");
            
                HBox addAdminButtonDateOfBirthHbox = new HBox(10);
                addAdminButtonDateOfBirthHbox.setId("AdminDateOfBirthHbox");
                addAdminButtonDateOfBirthHbox.setAlignment(Pos.CENTER_LEFT);
                addAdminButtonDateOfBirthHbox.setStyle("-fx-padding: 20;");
            
                HBox addAdminButtonRoleHbox = new HBox(10);
                addAdminButtonRoleHbox.setId("AdminRoleHbox");
                addAdminButtonRoleHbox.setAlignment(Pos.CENTER_LEFT);
                addAdminButtonRoleHbox.setStyle("-fx-padding: 20;");
            
                HBox addAdminButtonWorkingHoursHbox = new HBox(10);
                addAdminButtonWorkingHoursHbox.setId("AdminWorkingHoursHbox");
                addAdminButtonWorkingHoursHbox.setAlignment(Pos.CENTER_LEFT);
                addAdminButtonWorkingHoursHbox.setStyle("-fx-padding: 20;");
            
                Label adminUsernameLabel = new Label("Username:");
                TextField adminUsernameTextField = new TextField();
                textFields.add(adminUsernameTextField);  

                Label adminDateOfBirthLabel = new Label("Date of Birth:");
                TextField adminDateOfBirthTextField = new TextField();
                textFields.add(adminDateOfBirthTextField);
            
                Label adminRoleLabel = new Label("Role:");
                TextField adminRoleTextField = new TextField();
                textFields.add(adminRoleTextField);
            
                Label adminWorkingHoursLabel = new Label("Working Hours:");
                TextField adminWorkingHoursTextField = new TextField();
                textFields.add(adminWorkingHoursTextField);
            
                adminUsernameLabel.setId("adminUsernameLabel");
                adminUsernameLabel.getStyleClass().add("AddItemsSceneLabels");
                adminUsernameTextField.setId("adminUsernameTextField");
                adminUsernameTextField.getStyleClass().add("AddItemsSceneTextFields");

            
                adminDateOfBirthLabel.setId("adminDateOfBirthLabel");
                adminDateOfBirthLabel.getStyleClass().add("AddItemsSceneLabels");
                adminDateOfBirthTextField.setId("adminDateOfBirthTextField");
                adminDateOfBirthTextField.getStyleClass().add("AddItemsSceneTextFields");
            
                adminRoleLabel.setId("adminRoleLabel");
                adminRoleLabel.getStyleClass().add("AddItemsSceneLabels");
                adminRoleTextField.setId("adminRoleTextField");
                adminRoleTextField.getStyleClass().add("AddItemsSceneTextFields");
            
                adminWorkingHoursLabel.setId("adminWorkingHoursLabel");
                adminWorkingHoursLabel.getStyleClass().add("AddItemsSceneLabels");
                adminWorkingHoursTextField.setId("adminWorkingHoursTextField");
                adminWorkingHoursTextField.getStyleClass().add("AddItemsSceneTextFields");
            
                addAdminButtonUsernameHbox.getChildren().addAll(adminUsernameLabel, adminUsernameTextField);
                addAdminButtonDateOfBirthHbox.getChildren().addAll(adminDateOfBirthLabel, adminDateOfBirthTextField);
                addAdminButtonRoleHbox.getChildren().addAll(adminRoleLabel, adminRoleTextField);
                addAdminButtonWorkingHoursHbox.getChildren().addAll(adminWorkingHoursLabel, adminWorkingHoursTextField);
            
                AddItemsSceneVbox.getChildren().addAll(addAdminButtonUsernameHbox, addAdminButtonDateOfBirthHbox, addAdminButtonRoleHbox, addAdminButtonWorkingHoursHbox);
                break;
                

        }


        

    }

    public void storeInDatabase() {
        try {
            switch (choice) {
                case "AddCategoryButton":
                    if (textFields.get(0).getText().isEmpty()) {
                        showAlert("Error", "Category name is required.", Alert.AlertType.ERROR);
                    } else {
                        if (!textFields.get(0).getText().matches("[A-Za-z ]+")) {
                            showAlert("Error", "Category name should contain only letters and spaces.", Alert.AlertType.ERROR);
                            return;
                        }
                        Database.addCategory(new Category(textFields.get(0).getText()));
                        stage.setScene(AdminDashboardScene);
                    }
                    break;
    
                case "AddProductButton":
                    if (anyFieldEmpty(4)) {
                        showAlert("Error", "All product details must be filled in.", Alert.AlertType.ERROR);
                    } else {
                        try {
                            String categoryName = textFields.get(0).getText();
                            String productName = textFields.get(1).getText();
                            if (productName.isEmpty() || !productName.matches("[A-Za-z0-9 ]+")) {
                                showAlert("Error", "Product name should be alphanumeric.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            float price = Float.parseFloat(textFields.get(2).getText());
                            if (price <= 0) {
                                showAlert("Error", "Price must be a positive number.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            int quantity = Integer.parseInt(textFields.get(3).getText());
                            if (quantity < 0) {
                                showAlert("Error", "Quantity cannot be negative.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            Database.addProduct(productName, price, quantity, categoryName);
                            stage.setScene(AdminDashboardScene);
                        } catch (NumberFormatException ex) {
                            showAlert("Error", "Invalid input: Price and Quantity must be numeric.", Alert.AlertType.ERROR);
                            return;
                        }
                    }
                    break;
    
                case "AddCustomerButton":
                    if (anyFieldEmpty(5)) {
                        showAlert("Error", "All customer details must be filled in.", Alert.AlertType.ERROR);
                    } else {
                        try {
                            String username = textFields.get(0).getText();
                            String dob = textFields.get(1).getText();
                            if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                showAlert("Error", "Date of Birth must be in YYYY-MM-DD format.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            double balance = Double.parseDouble(textFields.get(2).getText());
                            if (balance < 0) {
                                showAlert("Error", "Balance cannot be negative.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            String address = textFields.get(3).getText();
                            if (address.isEmpty()) {
                                showAlert("Error", "Address is required.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            String[] interests = textFields.get(4).getText().split(",");
                            if (interests.length == 0 || interests[0].isEmpty()) {
                                showAlert("Error", "At least one interest must be specified.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            Customer customer = new Customer(username, "default", dob, balance, address, Users.Gender.Male, interests);
                            Database.addUser(customer);
                            stage.setScene(AdminDashboardScene);
                        } catch (NumberFormatException ex) {
                            showAlert("Error", "Balance must be numeric.", Alert.AlertType.ERROR);
                            return;
                        }
                    }
                    break;
    
                case "AddAdminButton":
                    if (anyFieldEmpty(4)) {
                        showAlert("Error", "All admin details must be filled in.", Alert.AlertType.ERROR);
                    } else {
                        try {
                            String username = textFields.get(0).getText();
                            String dob = textFields.get(1).getText();
                            if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                showAlert("Error", "Date of Birth must be in YYYY-MM-DD format.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            String role = textFields.get(2).getText();
                            if (!role.matches("[A-Za-z ]+")) {
                                showAlert("Error", "Role should contain only letters and spaces.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            int workingHours = Integer.parseInt(textFields.get(3).getText());
                            if (workingHours <= 0) {
                                showAlert("Error", "Working hours must be a positive integer.", Alert.AlertType.ERROR);
                                return;
                            }
    
                            Database.addUser(new Admin(username, "default", dob, Users.Gender.Male, role, workingHours));
                            stage.setScene(AdminDashboardScene);
                        } catch (NumberFormatException ex) {
                            showAlert("Error", "Working hours must be numeric.", Alert.AlertType.ERROR);
                            return;
                        }
                    }
                    break;
    
                default:
                    showAlert("Error", "Invalid operation.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException ex) {
            showAlert("Error", "Invalid number format in input fields.", Alert.AlertType.ERROR);
        } catch (ItemFoundException ex) {
            showAlert("Error", ex.getMessage(), Alert.AlertType.ERROR);
        } catch (ItemNotFoundException ex) {
            showAlert("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private boolean anyFieldEmpty(int requiredFields) {
        for (int i = 0; i < requiredFields; i++) {
            if (textFields.get(i).getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
