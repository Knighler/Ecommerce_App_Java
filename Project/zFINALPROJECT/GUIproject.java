
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GUIproject  extends Application {

    @Override
    public void start(Stage stage) throws IOException, ItemFoundException, ItemNotFoundException {

        

        Database.addUser(new Admin("a1","123","2000-12-12",Users.Gender.Male,"not so great",23.0));
        String [] i={"none"};


        Database.addUser(new Customer("user1","123","2000-12-12",12.0,"afdf",Users.Gender.Male,i));
        
        Database.addCategory(new Category("books"));
        Database.addProduct("book",10.0,10,"books");

        MainController mainController = new MainController();
/* 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHomePageScene.fxml"));
        CustomerHomePageSceneController customerHomePageSceneController = new CustomerHomePageSceneController();
        //loader.setController(customerHomePageSceneController); // Set the custom controller
        Parent root = loader.load();
        Scene CustomerHomePageScene = new Scene(root);
       
   
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("CustomerProductsScene.fxml"));
        CustomerProductsSceneController customerProductsSceneController = new CustomerProductsSceneController();        
       // loader2.setController(customerProductsSceneController); // Set the custom controller
        Parent root2 = loader2.load();
        Scene CustomerProductsScene = new Scene(root2);

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("ProfileScene.fxml"));
        CustomerProfileSceneController CustomerProfileSceneController = new CustomerProfileSceneController();
       //loader3.setController(CustomerProfileSceneController); // Set the custom controller
        Parent root3 = loader3.load();
        Scene CustomerProfileScene = new Scene(root3);*/
  
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("AdminProfile.fxml"));
        AdminProfileSceneController adminProfileSceneController = new AdminProfileSceneController();
        loader4.setController(adminProfileSceneController); // Set the custom controller
        Parent root4 = loader4.load();
        Scene AdminProfileScene = new Scene(root4);
 
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
        AdminDashboardSceneController adminDashboardSceneController = new AdminDashboardSceneController();
        loader5.setController(adminDashboardSceneController); // Set the custom controller
        Parent root5 = loader5.load();
        Scene AdminDashboardScene = new Scene(root5);
        AdminDashboardScene.setUserData(adminDashboardSceneController);

        FXMLLoader loader6 = new FXMLLoader(getClass().getResource("DatabaseAllItems.fxml"));
        DatabaseAllItemsSceneController databaseAllItemsSceneController = new DatabaseAllItemsSceneController();
        loader6.setController(databaseAllItemsSceneController); // Set the custom controller
        Parent root6 = loader6.load();
        Scene DatabaseAllItemsScene = new Scene(root6);
        DatabaseAllItemsScene.setUserData(databaseAllItemsSceneController);

        FXMLLoader loader7 = new FXMLLoader(getClass().getResource("DatabaseSingleItem.fxml"));
        DatabaseSingleItemSceneController databaseSingleItemSceneController = new DatabaseSingleItemSceneController();
        loader7.setController(databaseSingleItemSceneController); // Set the custom controller
        Parent root7 = loader7.load();
        Scene DatabaseSingleItemScene = new Scene(root7);
        DatabaseSingleItemScene.setUserData(databaseSingleItemSceneController);

        FXMLLoader loader8 = new FXMLLoader(getClass().getResource("AddItemsScene.fxml"));
        AddItemsSceneSceneController addItemsSceneSceneController = new AddItemsSceneSceneController();
        loader8.setController(addItemsSceneSceneController); // Set the custom controller
        Parent root8 = loader8.load();
        Scene AddItemsSceneScene = new Scene(root8);
        AddItemsSceneScene.setUserData(addItemsSceneSceneController);

        
        FXMLLoader loader10 = new FXMLLoader(getClass().getResource("loginupdatedd.fxml"));
        Logincontroller logInSceneController = new Logincontroller();
        loader10.setController(logInSceneController); // Set the custom controller
        Parent root10 = loader10.load();
        Scene logInScene = new Scene(root10);

        FXMLLoader loader9 = new FXMLLoader(getClass().getResource("signupupdatedd.fxml"));
        Signupcontroller signUpSceneController = new Signupcontroller();
        loader9.setController(signUpSceneController); // Set the custom controller
        Parent root9 = loader9.load();
        Scene SignUpScene = new Scene(root9);






    
   

        
        
        mainController.setStageAndScene(stage,AdminProfileScene  ,AdminDashboardScene,DatabaseAllItemsScene,DatabaseSingleItemScene,AddItemsSceneScene,SignUpScene,logInScene);
        


        

   
        stage.setTitle("Better Call Mall");
        stage.initStyle(StageStyle.DECORATED);
        //stage.setMaximized(true);
        stage.getIcons().add(new Image("bettercallmallLogo.png"));
        stage.setScene(logInScene);
        stage.show();
    }

    public static void main(String[] args) {
        GUIproject.launch(args);
    }

}
