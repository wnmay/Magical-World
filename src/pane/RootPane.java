package pane;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Goto;

public class RootPane extends VBox {
    private static RootPane instance;
    public Image gifImage;
    private RootPane() {
        ///////////////////////
        setImageByPath("BG1.gif");
        // Create an ImageView to display the GIF image
        ImageView imageView = new ImageView(gifImage);

        // Create a Text node
        Text text = new Text("lnwza");
        text.setFont(Font.font("Press Start 2P", FontWeight.BOLD,48)); // Set font and size
        text.setFill(Color.WHITE); // Set text color
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        GridPane.setColumnIndex(text, 1);
        GridPane.setRowIndex(text, 0);

        Rectangle background = new Rectangle();
        background.setFill(Color.RED); // Set background color
        background.setWidth(270); // Set width
        background.setHeight(70); // Set height


        Button playButton = new Button("PLAY");
        playButton.setMaxWidth(100);
        playButton.setPrefHeight(50);
        playButton.setFont(Font.font("Press Start 2P", FontWeight.BOLD,16));
        playButton.setTextFill(Color.WHITE);
        playButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        playButton.setOnMouseClicked(mouseEvent -> Goto.GamePage());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, background, text, playButton);

        // Set alignment of the stackPane, background, and text to center
        StackPane.setAlignment(stackPane, Pos.CENTER);
        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(text, Pos.CENTER);
        stackPane.setAlignment(playButton, Pos.BOTTOM_LEFT);

        // Create a StackPane to overlay the text on top of the GIF image
        this.getChildren().addAll(stackPane);


    }
    public Image getGifImage() {
        return gifImage;
    }

    public void setGifImage(Image gifImage) {
        this.gifImage = gifImage;
    }
    public void setImageByPath(String imagePath) {
        try {
            String classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();
            Image gifImage = new Image(classLoaderPath);
            setGifImage(gifImage);
        } catch (Exception e) {
            String classLoaderPath = ClassLoader.getSystemResource("6.png").toString();
            Image gifImage = new Image(classLoaderPath);
            setGifImage(gifImage);
        }
    }

    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}


