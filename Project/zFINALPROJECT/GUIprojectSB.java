
import javafx.application.Application;
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

public class GUIprojectSB extends Application {

    @Override
    public void start(Stage stage) {

        //  images 
        Image logo = new Image("bettercallmallLogo.png");
        ImageView imageView = new ImageView(logo);


        Image image1 = new Image("fashion.jpg");
        ImagePattern imagePattern = new ImagePattern(image1);


        Circle circle1 = new Circle(100);
        Circle circle2 = new Circle(100);
        Circle circle3 = new Circle(100);

        //middle
        Pane paneForCats = new Pane();

    
        paneForCats.getChildren().addAll(circle1, circle2, circle3);

 
        paneForCats.setStyle("-fx-background-color: #6C2222;");

        // circles
        circle1.centerXProperty().bind(paneForCats.widthProperty().divide(2).add(-350)); 
        circle2.centerXProperty().bind(paneForCats.widthProperty().divide(2)); 
        circle3.centerXProperty().bind(paneForCats.widthProperty().divide(2).add(350)); 
        circle1.centerYProperty().bind(paneForCats.heightProperty().divide(2));
        circle2.centerYProperty().bind(paneForCats.heightProperty().divide(2));
        circle3.centerYProperty().bind(paneForCats.heightProperty().divide(2));

   
        circle1.setFill(imagePattern);
        circle2.setFill(imagePattern);
        circle3.setFill(imagePattern);


        // labels
        Font font1 = Font.font("Agency FB", FontPosture.ITALIC, 20);
        Label label1 = new Label("Better Call Mall");
        Label label2 = new Label("Better Call Mall");
        Label label3 = new Label("Better Call Mall");

       
        label1.setTextFill(Color.WHITE);
        label1.setFont(font1);

        label2.setTextFill(Color.WHITE);
        label2.setFont(font1);

        label3.setTextFill(Color.WHITE);
        label3.setFont(font1);

        label1.layoutXProperty().bind(circle1.centerXProperty().add(-50)); 
        label2.layoutXProperty().bind(circle2.centerXProperty().add(-50)); 
        label3.layoutXProperty().bind(circle3.centerXProperty().add(-50)); 
        label1.layoutYProperty().bind(circle1.centerYProperty().add(-135));
        label2.layoutYProperty().bind(circle2.centerYProperty().add(-135));
        label3.layoutYProperty().bind(circle3.centerYProperty().add(-135));

        // Add the labels to the pane
        paneForCats.getChildren().addAll(label1, label2, label3);

        //hero secstion at the top
        Pane paneForHero = new Pane();

        imageView.fitWidthProperty().bind(paneForHero.widthProperty().multiply(0.3)); // 30% of the width
        //imageView.fitHeightProperty().bind(paneForHero.heightProperty().multiply(0.8)); // 50% of the height
        imageView.layoutXProperty().bind(paneForHero.widthProperty().multiply(0.1)); // 10% margin from the left
        imageView.layoutYProperty().bind(paneForHero.heightProperty().multiply(0.3)); // Center vertically (25% margin from top)
        
        Text paragraph = new Text("This is a paragraph of text. It will wrap automatically when the window size changes, "
                + "ensuring that the text remains readable and well-positioned. "
                + "This is great for providing detailed descriptions or any other long content.");
        paragraph.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        paragraph.setWrappingWidth(400); // Initial wrapping width
        paragraph.setTextAlignment(TextAlignment.JUSTIFY); // Justify the text

        // Bind the text position and wrapping width
        paragraph.layoutXProperty().bind(imageView.layoutXProperty().add(imageView.fitWidthProperty()).add(20)); // Right of the image
        paragraph.layoutYProperty().bind(imageView.layoutYProperty().add(50)); // Align with the top of the image
        paragraph.wrappingWidthProperty().bind(paneForHero.widthProperty().subtract(imageView.layoutXProperty().add(imageView.fitWidthProperty()).add(60))); // Adjust wrapping width dynamically



       
        paneForHero.getChildren().addAll(imageView, paragraph);
        paneForHero.setStyle("-fx-background-color: #ffffff;");

        //main Pane . Border
        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(paneForCats);
        mainPane.setTop(paneForHero);

        // Scene and stage setup
        Scene customerScene = new Scene(mainPane, 600, 400);

        Image icon = new Image("bettercallmallLogo.png");
        stage.getIcons().add(icon);
        stage.setTitle("Better Call Mall");
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.setScene(customerScene);
        stage.show();
    }

    public static void main(String[] args) {
        GUIproject.launch(args);
    }
}
