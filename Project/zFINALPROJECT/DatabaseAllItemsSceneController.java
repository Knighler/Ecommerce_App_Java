import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class DatabaseAllItemsSceneController extends MainController {
    @FXML
    private TableView<RowData> tableviewDatabase;
    @FXML
    private TableColumn<RowData, HBox> customersColumn;
    @FXML
    private TableColumn<RowData, HBox> adminsColumn;
    @FXML
    private TableColumn<RowData, HBox> categoriesColumn;
    @FXML
    private TableColumn<RowData, HBox> productsColumn;
    @FXML
    private TableColumn<RowData, HBox> ordersColumn;

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    private Scene scene;
    private Stage stage;
    private DatabaseSingleItemSceneController singleItemController;
    @FXML private TextField searchField;


    @FXML
    public void initialize(Scene scene, Stage stage, DatabaseSingleItemSceneController singleItemController) {
        
        this.singleItemController = singleItemController;
        this.scene = scene;
        this.stage = stage;
        setupData();
        setupTable();

        
    }

    public void handleTextFieldActionAux(){
        setSearchField(searchField);
        handleTextFieldAction();
   
    }

    

    private void setupData() {
        categories.clear();
        products.clear();
        admins.clear();
        customers.clear();
        orders.clear();


        for(int i=0;i<Database.getAllCategories().size();i++){
            categories.add(Database.getAllCategories().get(i));

        }

        for(int i=0;i<Database.getAllProducts().size();i++){
            products.add(Database.getAllProducts().get(i));

        }

        for(int i=0;i<Database.getAllUsers().size();i++){
            if(Database.getAllUsers().get(i) instanceof Admin){
                admins.add((Admin)Database.getAllUsers().get(i));
            }
            

        }

        for(int i=0;i<Database.getAllUsers().size();i++){
            if(Database.getAllUsers().get(i) instanceof Customer){
                customers.add((Customer)Database.getAllUsers().get(i));
            }
        }
        for(int i=0;i<Database.getAllOrders().size();i++){
            orders.add(Database.getAllOrders().get(i));

        }
   
    }

    private void setupTable() {
        // Prepare the table
        System.out.println("lwlwlwlwl");
        tableviewDatabase.getItems().clear();

        int maxSize = Math.max(
            Math.max(customers.size(), admins.size()),
            Math.max(categories.size(), products.size())
        );

        for (int i = 0; i < maxSize; i++) {
            Customer customer = i < customers.size() ? customers.get(i) : null;
            Admin admin = i < admins.size() ? admins.get(i) : null;
            Category category = i < categories.size() ? categories.get(i) : null;
            Product product = i < products.size() ? products.get(i) : null;
            Order order = i < orders.size() ? orders.get(i) : null;

            tableviewDatabase.getItems().add(new RowData(customer, admin, category, product,order, scene,stage,singleItemController));
            
        }

        customersColumn.setCellValueFactory(new PropertyValueFactory<>("customerButton"));
        adminsColumn.setCellValueFactory(new PropertyValueFactory<>("adminButton"));
        categoriesColumn.setCellValueFactory(new PropertyValueFactory<>("categoryButton"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("productButton"));
        ordersColumn.setCellValueFactory(new PropertyValueFactory<>("orderButton"));
    }

    
}
