import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerProductsSceneController extends MainController implements Initializable {
    private static ArrayList<Product> products;
    @FXML
    private FlowPane flowPane;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private TextField searchField;

    public static void setProducts(ArrayList<Product> products) {
        CustomerProductsSceneController.products = products;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }
    private static Customer customer;

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        CustomerProductsSceneController.customer = customer;
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
        setSearchField(searchField);

        for (Product product : products) {
            if(product.getnumberOfSameKind()<1){
                continue;
            }
            Image image = new Image(getClass().getResource("books.jpg").toExternalForm());
            ImageView myImageView = new ImageView(image);
            myImageView.setFitHeight(150);
            myImageView.setFitWidth(150);

            Label label = new Label(product.getName());
            label.setFont(new Font(25));
            Label label1 = new Label(product.getCategory());
            label1.setFont(new Font(25));
            Label label2 = new Label(String.valueOf(product.getnumberOfSameKind()));
            label2.setFont(new Font(25));
            Label label3 = new Label(String.valueOf(product.getPrice()));
            label3.setFont(new Font(25));


            Button btn =new Button("Add to Cart");
            btn.setOnAction(actionEvent -> {
                customer.addToCart(product,1);
                label2.setText(String.valueOf(product.getnumberOfSameKind()));
                if(product.getnumberOfSameKind()<1){
                    flowPane.getChildren().remove(btn.getParent());
                }
            });
            VBox vbox = new VBox();
            vbox.getChildren().add(myImageView);
            vbox.getChildren().add(label);
            vbox.getChildren().add(label1);
            vbox.getChildren().add(label2);
            vbox.getChildren().add(label3);
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().add(btn);
            flowPane.getChildren().add(vbox);
            //flowPane.setAlignment(Pos.CENTER);

            flowPane.setHgap(30);
        }
    }
}
