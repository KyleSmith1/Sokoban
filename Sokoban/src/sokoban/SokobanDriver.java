package sokoban;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author 14001835
 */
public class SokobanDriver extends Application {

    public static void main(String[] args) {
        //Runs the program, starting with the main menu
        launch(args);
    }

    @Override
    //Creates the main menu for selecting a level
    public void start(Stage stage) throws Exception {

        VBox componentLayout = new VBox(10);
        componentLayout.setAlignment(Pos.CENTER);

        Scene appSceneMenu = new Scene(componentLayout, 500, 500);
        stage.setScene(appSceneMenu);
        stage.show();
        stage.setTitle("Sokoban Menu");

        //Opens level select menu
        Button selectButton = new Button("Select Level");
        componentLayout.getChildren().add(selectButton);

        //Button to close program
        Button exitButton = new Button("Exit");
        componentLayout.getChildren().add(exitButton);

        //Buttons for each level currently in the game
        Button level1Button = new Button("Level 1");
        Button level2Button = new Button("Level 2");
        Button level3Button = new Button("Level 3");
        Button level4Button = new Button("Level 4");
        Button level5Button = new Button("Level 5");
        Button backButton = new Button("Back");

        //Creates an instance of the SokobanGame class
        SokobanGame game = new SokobanGame();

        //Set the actions for the select button, which opens up the level select menu
        selectButton.setOnAction(value -> {

            componentLayout.getChildren().remove(selectButton);
            componentLayout.getChildren().remove(exitButton);

            componentLayout.getChildren().add(level1Button);
            componentLayout.getChildren().add(level2Button);
            componentLayout.getChildren().add(level3Button);
            componentLayout.getChildren().add(level4Button);
            componentLayout.getChildren().add(level5Button);
            componentLayout.getChildren().add(backButton);

        });

        //Set the action for the exit button which closes the program
        exitButton.setOnAction(value -> {

            System.exit(0);

        });

        //Sets the game's level to level 1
        level1Button.setOnAction(value -> {
            game.setLevel(1);
            stage.close();
        });

        //Sets the game's level to level 2
        level2Button.setOnAction(value -> {
            game.setLevel(2);
            stage.close();
        });

        //Sets the game's level to level 3
        level3Button.setOnAction(value -> {
            game.setLevel(3);
            stage.close();
        });

        //Sets the game's level to level 4
        level4Button.setOnAction(value -> {
            game.setLevel(4);
            stage.close();
        });

        //Sets the game's level to level 5
        level5Button.setOnAction(value -> {
            game.setLevel(5);
            stage.close();
        });

        //Returns from the select level menu to the main menu
        backButton.setOnAction(value -> {
            componentLayout.getChildren().remove(level1Button);
            componentLayout.getChildren().remove(level2Button);
            componentLayout.getChildren().remove(level3Button);
            componentLayout.getChildren().remove(level4Button);
            componentLayout.getChildren().remove(level5Button);
            componentLayout.getChildren().remove(backButton);

            componentLayout.getChildren().add(selectButton);
            componentLayout.getChildren().add(exitButton);
        });
    }

}
