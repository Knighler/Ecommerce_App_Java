import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerHomePageSceneController extends MainController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TextField searchField;

    private static Customer customer;

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        CustomerHomePageSceneController.customer = customer;
    }
    
    


    private Stage stage;
    private Scene scene;
    private Parent root;

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
        setSearchField(searchField);
        ArrayList<Category> categories =  Database.getAllCategories();
        for (Category category : categories) {
            Image image = new Image(getClass().getResource("books.jpg").toExternalForm());
            ImageView myImageView = new ImageView(image);
            myImageView.setFitHeight(150);
            myImageView.setFitWidth(150);
            myImageView.setOnMouseClicked(event -> {
                try{
                    CustomerProductsSceneController.setProducts(category.getListOfProducts());
                    CustomerProductsSceneController.setCustomer(customer);
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerProductsScene.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            });
            Label label = new Label(category.getCatName());
            label.setFont(new Font(25));
            label.setOnMouseClicked(event -> {
                try {
                    CustomerProductsSceneController.setProducts(category.getListOfProducts());
                    CustomerProductsSceneController.setCustomer(customer);
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerProductsScene.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            });
            VBox vbox = new VBox();
            vbox.getChildren().add(myImageView);
            vbox.getChildren().add(label);
            vbox.setAlignment(Pos.CENTER);

            flowPane.getChildren().add(vbox);
            flowPane.setAlignment(Pos.CENTER);

            flowPane.setHgap(30);
        }

    }
}
