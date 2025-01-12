// import javafx.event.EventHandler;
// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.stage.Stage;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Hyperlink;

// import java.io.IOException;

// import javafx.scene.Scene;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.ImagePattern;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontPosture;
// import javafx.scene.text.Text;
// import javafx.scene.text.TextAlignment;
// import javafx.scene.control.Label;
// import javafx.stage.Stage;
// import javafx.stage.StageStyle;

// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.control.Alert;
// import javafx.scene.input.MouseEvent;

// class Controller   extends MainController implements EventHandler<ActionEvent>{
//     public void handle(ActionEvent e){

//     }

//     @FXML
//     private Hyperlink fgp;

//     @FXML
//     private Button login;

//     @FXML
//     private Hyperlink signup;
//     private Parent root;
//     private Stage signupstage;
//     private Scene scene;
//     @FXML
//     void clickfgp(ActionEvent event) {
           
//     }

//     @FXML
//    public  void clicklogin(ActionEvent event)throws IOException {
       
         
//     }

//     @FXML
//    public void clicksignup(ActionEvent event)throws IOException {
//         // root =FXMLLoader.load(getClass().getResource("signup.fxml"));
//         // signupstage=(Stage)((Node)event.getSource()).getScene().getWindow();
//         // scene=new Scene(root);
//         // signupstage.setScene(scene);
//         // signupstage.show();
        


//     }



//     }

        

//         // @FXML
//         // private Button login;
    
//         // @FXML
//         // void loginclick(MouseEvent event) {

//         // }
//         // @FXML
//         // public void intialize()
//         // {
            
//         // }
//         // public void nextScene()
//         // {
//         //     try{
                
//         //         FXMLLoader loader=new FXMLLoader(getClass().getResource("signup.fxml"));
//         //         Parent root=loader.load();
//         //         Scene signupscene=new Scene(root);

//         //     }
//         //     catch(Exception e)
//         //     {
//         //         e.getMessage();
//         //     }
//         // }
            
     





// //      Alert alert = new Alert(Alert.AlertType.INFORMATION);
// //         alert.setTitle("Mouse Clicked");
// //         alert.setHeaderText(null);
// //         alert.setContentText("Button was clicked!");
// //         alert.showAndWait();
// //         }

import java.io.IOException;
import java.lang.classfile.components.ClassPrinter.Node;
import java.time.temporal.Temporal;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;








public class Logincontroller extends MainController
{
    @FXML
    private Hyperlink fgp;

    @FXML
    private Button login;

    @FXML
    private Hyperlink signup;
    @FXML
    private Label labelmessage=new Label();
    @FXML
    private TextField username= new TextField();
    @FXML
    private PasswordField password=new PasswordField();
    public PasswordField getPassword() {
        return password;
    }
    public TextField getUsername() {
        return username;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

   


//    public  void clicklogin(ActionEvent event) {
//      Database database = new Database() {};
//        if( username.getText().isBlank()==false && password.getText().isBlank()==false )
//        {

//         if(database.searchUsersByName("jj")==username){

//         labelmessage.setText("login successful");
//        }
//        else{
//         labelmessage.setText("enter username and password");
//        }
//     }
        
//     }
public void clickLogin(ActionEvent event) {



    // Check if both username and password fields are not blank
    if (!username.getText().isBlank() && !password.getText().isBlank()) {
       



        // try {
        //     // user=new Customer(inputUsername,inputPassword);
        //     // Database.addUser(user);
        // } catch (ItemFoundException e) {
        //     e.printStackTrace();
        // }
       
        // Users user = Database.getUserByName(inputUsername);

        
        // Validate login credentials
        login.setOnAction(e->
        
        {
            
            if (Database.searchUsersByName(username.getText())) {
                System.out.println(username.getText());
                System.out.println(Database.getUserByName(username.getText()).getUsername());
                Users user=Database.getUserByName(username.getText());

                // System.out.println(user.getPassword());
                if(user.getUsername().equals(username.getText()) && user.getPassword().equals(password.getText())){
                    
                    username.clear();
                    password.clear();

                    if(user instanceof Admin){
                        switchToAdminDashboard();
                    }
                    else if(user instanceof Customer){
                        
                        try {
                            setCustomerForSearch((Customer) user);
                            CustomerHomePageSceneController.setCustomer((Customer) user);
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerHomePageScene.fxml")));
                            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                        catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    
                
                labelmessage.setText("Login successful");
                }
                
                  else if(user.getUsername().equals(username.getText()) ) 
            {
                labelmessage.setText("Invalid password");
            }
        }
            else 
            {
                labelmessage.setText("Invalid username");
            }
        
          
            
        });
        // else {
        //     // Display message when either username or password is blank
        //     labelmessage.setText("Please enter both username and password");
        // }
       
        
        }

}
// @FXML
// void switchToSignup(ActionEvent event) {

// }
// @FXML
// void clickFgp(ActionEvent event) {

// }

}


  

    

