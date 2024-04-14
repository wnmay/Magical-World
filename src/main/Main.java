package main;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Main extends Application {
    private Image gifImage;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        setImageByPath("BG1.gif");
        // Create an ImageView to display the GIF image
        ImageView imageView = new ImageView(gifImage);

        // Create a Text node
        Text text = new Text("lnwza");
        text.setFont(Font.font("Press Stert 2P", FontWeight.BOLD,48)); // Set font and size
        text.setFill(Color.WHITE); // Set text color
        GridPane.setColumnIndex(text, 1);
        GridPane.setRowIndex(text, 0);

        Rectangle background = new Rectangle();
        background.setFill(Color.RED); // Set background color
        background.setWidth(200); // Set width
        background.setHeight(50); // Set height
        StackPane.setAlignment(background, Pos.CENTER);


        // Create a StackPane to overlay the text on top of the GIF image
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, background, text);

        // Set alignment of the text to center
        StackPane.setAlignment(text, Pos.CENTER);

        // Create a Scene and set it to the stage
        Scene scene = new Scene(stackPane, gifImage.getWidth(), gifImage.getHeight());
        stage.setScene(scene);
        stage.setTitle("lnwza");
        stage.show();

    }
    public void setImageByPath(String imagePath) {
        try {
            String classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();
            this.gifImage=new Image(classLoaderPath);
        } catch (Exception e) {
            String classLoaderPath = ClassLoader.getSystemResource("6.png").toString();
            this.gifImage=new Image(classLoaderPath);
        }
    }


    }



