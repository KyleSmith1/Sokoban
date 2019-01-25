package sokoban;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author 14001835
 */
public class SokobanGame {

    private Level CurrentLevel;

    public static AnchorPane anchorPane = new AnchorPane();

    public SokobanGame() {

    }

    //Sets the game's level
    public void setLevel(int levelNumber) {
        //Sets the current level to the level passed into the parameter
        CurrentLevel = new Level(levelNumber);
        displayGame();
    }

    //Get the current level
    public Level getMap(int LevelNumber) {
        //Gets the current level
        return CurrentLevel;
    }

    //Display's the game's window
    public void displayGame() {

        BorderPane borderPane = new BorderPane();
        borderPane.setMinSize(800, 600);

        GridPane gridPane = new GridPane();
        HBox topBorder = new HBox();
        Stage window = new Stage();

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        topBorder.setPadding(new Insets(10, 10, 10, 10));
        topBorder.setAlignment(Pos.CENTER);

        borderPane.setTop(topBorder);
        borderPane.setCenter(anchorPane);
        borderPane.setBottom(gridPane);

        topBorder.setMinSize(800, 100);
        gridPane.setMinSize(800, 100);
        anchorPane.setMinSize(800, 100);

        //Window cannot be resized
        window.setResizable(false);

        Scene appSceneGame = new Scene(borderPane);
        window.setScene(appSceneGame);
        window.show();

        Button upButton = new Button("\u2191");
        Button downButton = new Button("\u2193");
        Button leftButton = new Button("\u2190");
        Button rightButton = new Button("\u2192");

        //Adds the controller buttons to the gridpane
        gridPane.add(upButton, 2, 1);
        gridPane.add(downButton, 2, 2);
        gridPane.add(leftButton, 1, 2);
        gridPane.add(rightButton, 3, 2);

        //Displays the level number for the user
        Label levelLabel = new Label("Level " + CurrentLevel.getLevelNumber());
        levelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        topBorder.getChildren().add(levelLabel);
        
        
        Label movesLabel = new Label("Number of Moves: " + CurrentLevel.getNumberOfMoves());
        movesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        movesLabel.setTranslateX(100);
        topBorder.getChildren().add(movesLabel);

        //Sets the title for the window
        window.setTitle("Sokoban Level " + CurrentLevel.getLevelNumber());

        
        //What happens when each of the directional buttons are clicked
        upButton.setOnAction(value -> {

            CurrentLevel.controlKeeper("Up");
            movesLabel.setText("Number of Moves: " + CurrentLevel.getNumberOfMoves());

        });

        downButton.setOnAction(value -> {

            CurrentLevel.controlKeeper("Down");
            movesLabel.setText("Number of Moves: " + CurrentLevel.getNumberOfMoves());

        });

        leftButton.setOnAction(value -> {

            CurrentLevel.controlKeeper("Left");
            movesLabel.setText("Number of Moves: " + CurrentLevel.getNumberOfMoves());

        });

        rightButton.setOnAction(value -> {

            CurrentLevel.controlKeeper("Right");
            movesLabel.setText("Number of Moves: " + CurrentLevel.getNumberOfMoves());

        });

    }
    
    

}
