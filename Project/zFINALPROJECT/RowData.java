import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RowData {
   
    private HBox customerHbox;
    private HBox adminHbox;
    private HBox categoryHbox;
    private HBox productHbox;
    private HBox orderHbox;
    private Stage stage;
    private Scene scene;
    private DatabaseSingleItemSceneController singleItemController;


    public RowData(Customer customer, Admin admin, Category category, Product product,Order order,Scene scene,Stage stage,DatabaseSingleItemSceneController singleItemController) {
        this.customerHbox = createCustomerButton(customer);
        this.adminHbox = createAdminButton(admin);
        this.categoryHbox = createCategoryButton(category);
        this.productHbox = createProductButton(product);
        this.orderHbox = createOrderButton(order);
        this.stage = stage;
        this.scene = scene;
        this.singleItemController = singleItemController;
    }

    private HBox createOrderButton(Order order) {
        if (order == null) return null;
        Button button = new Button(order.getUsername());
        button.setOnAction(event -> {
            singleItemController.setObject(order);
            stage.setScene(scene);
        }
             
       );


        button.setStyle("-fx-background-color: #c6a108; -fx-text-fill: white;");
        HBox hbox=new  HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(button);
        return hbox;
    }

    private HBox createCustomerButton(Customer customer) {
        if (customer == null) return null;
        Button button = new Button(customer.getUsername());
        button.setOnAction(event -> {
            singleItemController.setObject(customer);
            stage.setScene(scene);

        }
             
       );


        button.setStyle("-fx-background-color: #c6a108; -fx-text-fill: white;");
        HBox hbox=new  HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(button);
        return hbox;
    }

    private HBox createAdminButton(Admin admin) {
        if (admin == null) return null;
        Button button = new Button(admin.getUsername());
        button.setOnAction(event -> {

            singleItemController.setObject(admin);
            stage.setScene(scene);
        }
             
       );


        button.setStyle("-fx-background-color: #c6a108; -fx-text-fill: white;");
        HBox hbox=new  HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(button);
        return hbox;
    }

    private HBox createCategoryButton(Category category) {
        if (category == null) return null;
        Button button = new Button(category.getCatName());
        button.setOnAction(event -> {
            singleItemController.setObject(category);
            stage.setScene(scene);
        }
             
       );


        button.setStyle("-fx-background-color: #c6a108; -fx-text-fill: white;");
        HBox hbox=new  HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(button);
        return hbox;
    }

    private HBox createProductButton(Product product) {
        if (product == null) return null;
        Button button = new Button(product.getName());
        button.setOnAction(event -> {
            singleItemController.setObject(product);
            stage.setScene(scene);

        }
             
       );


        button.setStyle("-fx-background-color: #c6a108; -fx-text-fill: white;");
        HBox hbox=new  HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(button);
        return hbox;
    }

    

    public HBox getCustomerButton() {
        return customerHbox;
    }

    public HBox getAdminButton() {
        return adminHbox;
    }

    public HBox getCategoryButton() {
        return categoryHbox;
    }

    public HBox getProductButton() {
        return productHbox;
    }

    public HBox getOrderButton() {
        return orderHbox;
    }
}