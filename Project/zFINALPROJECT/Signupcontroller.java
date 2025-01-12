// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.input.MouseEvent;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.Hyperlink;
// import javafx.scene.input.MouseEvent;

// public class Controller2 extends MainController implements EventHandler<ActionEvent> {

//     @FXML
//     private Hyperlink login2;

//     @FXML
//     private Button signup2;

//     @FXML
//     void clicklogin2(ActionEvent event) {

//     }

//     @FXML
//     void clicksignup2(ActionEvent event) {

//     }

//     @FXML
//     void login2(MouseEvent event) {

//     }
//     public void handle(ActionEvent e)
//     {

//     }

// }
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signupcontroller extends MainController {

    @FXML
    private TextField addressField = new TextField();

    @FXML
    private PasswordField confirmpassField = new PasswordField();

    @FXML
    private TextField emailField = new TextField();

    @FXML
    private TextField usernamTextField = new TextField();

    @FXML
    private Hyperlink login2;

    @FXML
    private PasswordField passwordField = new PasswordField();;

    @FXML
    private Button signup;

    @FXML
    private Label alertmessage = new Label();

    static boolean validation = false;

    String username;
    String email;
    String address;
    String password ;
    String confirmPassword;
    @FXML
    void clickSignup() throws ItemFoundException {
      
        System.out.println("clicked");
        StringBinding errorbinding = Bindings.createStringBinding(() -> {
         
            username=usernamTextField.getText();
             email = emailField.getText();
             address = addressField.getText();
             password = passwordField.getText();
             confirmPassword = confirmpassField.getText();
            // Simple validation logic
            if (username.isEmpty() || password.isEmpty()|| email.isEmpty() || address.isEmpty()
                    || confirmPassword.isEmpty()) {
                return "Fields cannot be empty.";
            } 
             else if (username.length() < 3) {
                return "lastname must be at least 3 characters .";
            } else if (address.length() < 3) {
                return "address must be at least 3 characters .";
            }
            // Validate password confirmation
            else if (!password.equals(confirmPassword)) {
                return "Passwords do not match.";

            }

            // Validate email format
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return ("Invalid email format.");

            }

            else if (!password.matches(".*\\d.*") && password.length() < 6) { // Ensure password contains a number
                return "Password must contain at least one digit.";
            } else {
                validation = true;
                return "singup successful"; // No error message

            }

        }, usernamTextField.textProperty(), passwordField.textProperty(),
                confirmpassField.textProperty(), emailField.textProperty(), addressField.textProperty());

        alertmessage.textProperty().bind(errorbinding);

        // Clear previous messages
        signup.setOnAction((e) -> {

            if (validation) {
           
              try {
                System.out.println("entered");
                String[] interests = {"Food","Games"};
                Users user1=new Customer(username,password,"2000-07-09",0,address, Users.Gender.Male,interests);
                Database.addUser(user1);
                
                System.out.println(Database.getAllUsers());
            } catch (ItemFoundException e1) {
                e1.printStackTrace();
            }
                switchToLogIn();
            }

        });
    }
}
