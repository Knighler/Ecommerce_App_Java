import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DatabaseSingleItemSceneController extends MainController {

    @FXML
    private VBox detailsVBox;
    @FXML
    private TextField searchField;

    // Method to set the object and populate details

    public void handleTextFieldActionAux() {
        setSearchField(searchField);
        handleTextFieldAction();
    
    }
    public void setObject(Object obj) {
        
        detailsVBox.getChildren().clear(); // Clear existing content in the VBox

        if (obj instanceof Customer) {
            Customer customer = (Customer) obj;
            addFieldWithLabel("Username: ", customer.getUsername(), false);
            addFieldWithLabel("Date of Birth: ", customer.getDateOfBirth(), false);
            addFieldWithLabel("Gender: ", customer.getGender().toString(), true);
            addFieldWithLabel("Address: ", customer.getAddress(), false);
            addFieldWithLabel("Balance: ", String.valueOf(customer.getBalance()), false);
        } else if (obj instanceof Admin) {
            Admin admin = (Admin) obj;
            addFieldWithLabel("Username: ", admin.getUsername(), false);

            addFieldWithLabel("Date of Birth: ", admin.getDateOfBirth(), false);
            addFieldWithLabel("Gender: ", admin.getGender().toString(), true);
            addFieldWithLabel("Role: ", admin.getRole(), false);
            addFieldWithLabel("Working Hours: ", String.valueOf(admin.getWorkingHours()), false);
        } else if (obj instanceof Product) {
            Product product = (Product) obj;
            addFieldWithLabel("Name: ", product.getName(), true);
            addFieldWithLabel("Price: ", String.valueOf(product.getPrice()), false);
            addFieldWithLabel("Number: ", String.valueOf(product.getnumberOfSameKind()), false);
            addFieldWithLabel("Category: ", product.getCategory(), true);
        } else if (obj instanceof Category) {
            Category category = (Category) obj;
            addFieldWithLabel("Name: ", category.getCatName(), false);
        }

        // Add Delete and Update Buttons
        addActionButtons(obj);
    }

    // Helper method to create a Label and TextField pair
    private void addFieldWithLabel(String labelText, String fieldValue,boolean isLocked) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        TextField textField = new TextField(fieldValue);
        textField.setPrefWidth(200);
        textField.setDisable(isLocked);

        hbox.getChildren().addAll(label, textField);
        detailsVBox.getChildren().add(hbox);
    }

    // Helper method to add action buttons
    private void addActionButtons(Object obj) {
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);

        // Delete Button
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #ff4c4c; -fx-text-fill: white; -fx-font-weight: bold;");
        deleteButton.setOnAction(event -> {
            System.out.println("Delete action triggered for: " + obj);

            // Call the specific delete method based on the object type
            try{
            if (obj instanceof Customer) {
               
                Database.removeUser((Customer) obj);
            } else if (obj instanceof Admin) {
                Database.removeUser((Admin) obj);
            } else if (obj instanceof Product) {
                Database.removeProduct((Product) obj);
            } else if (obj instanceof Category) {
                Database.removeCategory((Category) obj);
            }

            System.out.println("Object deleted: " + obj);
            Platform.runLater(() -> detailsVBox.getChildren().clear());}
            catch(Exception e){
                System.out.println(e);
            }
        });

        buttonBox.getChildren().add(deleteButton);

        // Conditionally add Update Button for non-Category objects
       if (!(obj instanceof Category)) {
    Button updateButton = new Button("Update");
    updateButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold;");
    updateButton.setOnAction(event -> {
        System.out.println("Update action triggered. New values:");

        // Extract and update values from text fields
        for (int i = 0; i < detailsVBox.getChildren().size() - 1; i++) {
            HBox hbox = (HBox) detailsVBox.getChildren().get(i);
            TextField textField = (TextField) hbox.getChildren().get(1);
            String newValue = textField.getText();
            Label label = (Label) hbox.getChildren().get(0);

            // Match the label text and call corresponding update method
            if (obj instanceof Customer) {
                Customer customer = (Customer) obj;
                if (label.getText().startsWith("Username")) {
                    if (newValue.isEmpty() || !newValue.matches("[A-Za-z0-9_]+")) {
                        showAlert("Error", "Invalid username. Use only alphanumeric characters and underscores.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateUsername(customer, newValue);
                } else if (label.getText().startsWith("Password")) {
                    if (newValue.length() < 3) {
                        showAlert("Error", "Password must be at least 6 characters long.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updatePassword(customer, newValue);
                } else if (label.getText().startsWith("Date of Birth")) {
                    if (!newValue.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        showAlert("Error", "Date of Birth must be in YYYY-MM-DD format.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateDateOfBirth(customer, newValue);
                } else if (label.getText().startsWith("Address")) {
                    if (newValue.isEmpty()) {
                        showAlert("Error", "Address cannot be empty.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateCustomerAddress(customer, newValue);
                } else if (label.getText().startsWith("Balance")) {
                    try {
                        double balance = Double.parseDouble(newValue);
                        if (balance < 0) {
                            showAlert("Error", "Balance cannot be negative.", Alert.AlertType.ERROR);
                            return;
                        }
                        Database.updateCustomerBalance(customer, balance);
                    } catch (NumberFormatException e) {
                        showAlert("Error", "Balance must be a numeric value.", Alert.AlertType.ERROR);
                        return;
                    }
                }
            } else if (obj instanceof Admin) {
                Admin admin = (Admin) obj;
                if (label.getText().startsWith("Username")) {
                    if (newValue.isEmpty() || !newValue.matches("[A-Za-z0-9_]+")) {
                        showAlert("Error", "Invalid username. Use only alphanumeric characters and underscores.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateUsername(admin, newValue);
                } else if (label.getText().startsWith("Password")) {
                    if (newValue.length() < 3) {
                        showAlert("Error", "Password must be at least 6 characters long.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updatePassword(admin, newValue);
                } else if (label.getText().startsWith("Date of Birth")) {
                    if (!newValue.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        showAlert("Error", "Date of Birth must be in YYYY-MM-DD format.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateDateOfBirth(admin, newValue);
                } else if (label.getText().startsWith("Role")) {
                    if (newValue.isEmpty() || !newValue.matches("[A-Za-z ]+")) {
                        showAlert("Error", "Role must contain only letters and spaces.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateAdminRole(admin, newValue);
                } else if (label.getText().startsWith("Working Hours")) {
                    try {
                        double workingHours = Double.parseDouble(newValue);
                        if (workingHours <= 0) {
                            showAlert("Error", "Working hours must be a positive number.", Alert.AlertType.ERROR);
                            return;
                        }
                        Database.updateAdminWorkingHours(admin, workingHours);
                    } catch (NumberFormatException e) {
                        showAlert("Error", "Working hours must be a numeric value.", Alert.AlertType.ERROR);
                        return;
                    }
                }
            } else if (obj instanceof Product) {
                Product product = (Product) obj;
                if (label.getText().startsWith("Name")) {
                    if (newValue.isEmpty() || !newValue.matches("[A-Za-z0-9 ]+")) {
                        showAlert("Error", "Product name must be alphanumeric.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateProductName(product, newValue);
                } else if (label.getText().startsWith("Price")) {
                    try {
                        float price = Float.parseFloat(newValue);
                        if (price <= 0) {
                            showAlert("Error", "Price must be a positive number.", Alert.AlertType.ERROR);
                            return;
                        }
                        Database.updateProductPrice(product, price);
                    } catch (NumberFormatException e) {
                        showAlert("Error", "Price must be a numeric value.", Alert.AlertType.ERROR);
                        return;
                    }
                } else if (label.getText().startsWith("Number")) {
                    try {
                        int number = Integer.parseInt(newValue);
                        if (number < 0) {
                            showAlert("Error", "Number cannot be negative.", Alert.AlertType.ERROR);
                            return;
                        }
                        Database.updateProductNumber(product, number);
                    } catch (NumberFormatException e) {
                        showAlert("Error", "Number must be a numeric value.", Alert.AlertType.ERROR);
                        return;
                    }
                } else if (label.getText().startsWith("Category")) {
                    if (newValue.isEmpty() || !newValue.matches("[A-Za-z ]+")) {
                        showAlert("Error", "Category name must contain only letters and spaces.", Alert.AlertType.ERROR);
                        return;
                    }
                    Database.updateProductCategoryName(product, newValue);
                }
            }
        }
    });
    buttonBox.getChildren().add(updateButton);
}
detailsVBox.getChildren().add(buttonBox);


            
        }
        private void showAlert(String title, String message, Alert.AlertType alertType) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        
    }
