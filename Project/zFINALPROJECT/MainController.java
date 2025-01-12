import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
    protected static Stage stage;
    protected static Scene CustomerHomePageScene;
    protected static Scene CustomerProductsScene;
    protected static Scene ProfileScene;
    protected static Scene AdminProfileScene;
    protected static Scene AdminDashboardScene;
    protected static Scene DatabaseAllItemsScene;
    protected static Scene DatabaseSingleItemScene;
    protected static Scene AddItemsSceneScene;
    protected static Scene SignUpScene;
    protected static Scene LogInSCene;
    protected static TextField searchField;
    private  Users user;

    public void setStageAndScene(Stage stage ,  Scene AdminProfileScene  ,Scene AdminDashboardScene,Scene DatabaseAllItemsScene,Scene DatabaseSingleItemScene,Scene AddItemsSceneScene,Scene SignUpScene,Scene LogInSCene) {
        MainController.stage = stage; 
    
        MainController.AdminProfileScene = AdminProfileScene;
        MainController.AdminDashboardScene = AdminDashboardScene;
        MainController.DatabaseAllItemsScene = DatabaseAllItemsScene;
        MainController.DatabaseSingleItemScene = DatabaseSingleItemScene;
        MainController.AddItemsSceneScene = AddItemsSceneScene;
        MainController.SignUpScene = SignUpScene;
        MainController.LogInSCene = LogInSCene;
        System.out.println(stage);
    }
/* 
    public void switchToHome() {
        Platform.runLater(() -> {
            if (stage != null && CustomerHomePageScene != null) {
                stage.setScene(CustomerHomePageScene);
            }
        });
    }

    public void switchToProducts() {
        
        Platform.runLater(() -> {

            if (stage != null && CustomerProductsScene != null) {
                stage.setScene(CustomerProductsScene);
                
            }
        });
    }


    public void switchToProfile() {
        Platform.runLater(() -> {
            if (stage != null && ProfileScene != null) {
                stage.setScene(ProfileScene);
            }
        });
    }*/
    
    public void switchToAdminProfile() {
        Platform.runLater(() -> {
            if (stage != null && AdminProfileScene != null) {
                System.out.println("afhdsf");
            }
        });
    }

    public void switchToAdminDashboard() {
        Platform.runLater(() -> {
            if (stage != null && AdminDashboardScene != null) {
                AdminDashboardSceneController controller = (AdminDashboardSceneController) AdminDashboardScene.getUserData();
                controller.initialize();
                stage.setScene(AdminDashboardScene);

            }
        });
    }   
    
    public void switchToDatabaseAllItems() {
        Platform.runLater(() -> {
            if (stage != null && DatabaseAllItemsScene != null) {
                DatabaseAllItemsSceneController controller = (DatabaseAllItemsSceneController) DatabaseAllItemsScene.getUserData();
                 DatabaseSingleItemSceneController singleItemController = (DatabaseSingleItemSceneController) DatabaseSingleItemScene.getUserData();
                 controller.initialize(DatabaseSingleItemScene, stage, singleItemController);
                 stage.setScene(DatabaseAllItemsScene);
            }
        });
    }
    
   

    public void switchToAddItems(ActionEvent e) {
        Platform.runLater(() -> {
            if (stage != null && AddItemsSceneScene != null) {
                Button button = (Button) e.getSource();
                String buttonId= button.getId();
                
                
                AddItemsSceneSceneController controller = (AddItemsSceneSceneController) AddItemsSceneScene.getUserData();
                controller.setChoice(buttonId);
                stage.setScene(AddItemsSceneScene);
            }
        });
    }

    
    public void switchToLogIn() {
        Platform.runLater(() -> {
            if (stage != null && SignUpScene != null) {
                stage.setScene(LogInSCene);
            }
        });
    }

    public void switchToSignup() {
        Platform.runLater(() -> {
            if (stage != null && SignUpScene != null) {
                stage.setScene(SignUpScene);
            }
        });
    }

    public void setSearchField(TextField searchField) {

        MainController.searchField = searchField;

    }
    public void setCustomerForSearch(Users user) {
        this.user = user;
    }

    public void handleTextFieldAction(){
        Platform.runLater(() -> {
        
            if (stage != null) {
              
                if (searchField != null) {
                    if(user instanceof Customer){
                        Customer customer=(Customer)user;
                        
                    
                    if(Database.searchCategoriesByName(searchField.getText())){
                        CustomerProductsSceneController.setProducts(Database.getCategoryProducts(searchField.getText()));
                        CustomerProductsSceneController.setCustomer(customer);
                        Object root;
                        searchField.clear();
                        try {
                            
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerProductsScene.fxml")));
                            Scene scene = new Scene((Parent) root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }            
                      
                    
                    }
                    else if(Database.searchProducts(searchField.getText())){
                        ArrayList<Product> products =new ArrayList<>();
                        products.add(Database.getProduct(searchField.getText()));
                        CustomerProductsSceneController.setProducts( products);
                        CustomerProductsSceneController.setCustomer(customer);
                        Object root;
                        searchField.clear();
                        try {
                            
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerProductsScene.fxml")));
                            Scene scene = new Scene((Parent) root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }            

                        
                    }
                    else {
                    // Show an alert if no category or product is found
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Result");
                    alert.setHeaderText("No Match Found");
                    alert.setContentText("No category or product matches your search query: " + searchField.getText());
                    searchField.clear();
                    alert.showAndWait();
                }
                }
                else{
               
                    DatabaseSingleItemSceneController singleItemController = (DatabaseSingleItemSceneController) DatabaseSingleItemScene.getUserData();
                    if(Database.searchCategoriesByName(searchField.getText())){
                        System.out.println("in cat");
                        singleItemController.setObject(Database.getCategory(searchField.getText()));
                        stage.setScene(DatabaseSingleItemScene);
                        searchField.clear();
                    }
                    else if(Database.searchProducts(searchField.getText())){
                        System.out.println("in prod");
                        singleItemController.setObject(Database.getProduct(searchField.getText()));
                        stage.setScene(DatabaseSingleItemScene);
                        searchField.clear();
                    }
                    else if(Database.searchUsersByName(searchField.getText())){
                        singleItemController.setObject(Database.getUserByName(searchField.getText()));
                        stage.setScene(DatabaseSingleItemScene);
                        searchField.clear();
                    }
                    else{
                        searchField.clear();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Search Result");
                        alert.setHeaderText("No Match Found");
                        alert.setContentText("No matches for your search query: ");
                        
                        alert.showAndWait();

                    }


                }
            
            
            
            }
            }
        }   
        
        );

    }


    
        
}
