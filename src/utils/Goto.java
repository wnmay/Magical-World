package utils;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
import javafx.scene.text.TextAlignment;
import pane.GameScreen;
import pane.RootPane;

public class Goto {
    private static RootPane rootPane;
    public static void setRootPane(RootPane rootPane){
        Goto.rootPane = rootPane;
    }
    public static void clear(){
        if (rootPane.getChildren().size()<=1){
            return;
        }
        rootPane.getChildren().remove(1, rootPane.getChildren().size());
    }
    public static Button backToMainPageButton(){
        Button backButton = new Button("Black");
        backButton.setBorder(new Border(new javafx.scene.layout.BorderStroke(Color.DARKCYAN, javafx.scene.layout.BorderStrokeStyle.SOLID, null, new javafx.scene.layout.BorderWidths(2))));

        backButton.setStyle("-fx-background-color: white;");

        backButton.setTextFill(Color.DARKCYAN);

        backButton.setPrefWidth(300);

        backButton.setOnMouseClicked(mouseEvent -> Goto.setRootPane(RootPane.getRootPane()));

        return backButton;
    }
    public static void GamePage(){
        clear();
        Text text = new Text("Game Start");
        text.setFont(Font.font("Press Start 2P", FontWeight.BOLD,48)); // Set font and size
        Button backButton = Goto.backToMainPageButton();
        rootPane.getChildren().addAll(text, backButton);

    }



}
