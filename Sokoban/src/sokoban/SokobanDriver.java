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

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox componentLayout = new VBox(10);
        componentLayout.setAlignment(Pos.CENTER);

        Scene appSceneMenu = new Scene(componentLayout, 500, 500);
        stage.setScene(appSceneMenu);
        stage.show();
        stage.setTitle("Pontoon Menu");

        Button startButton = new Button("Start New Game");
        componentLayout.getChildren().add(startButton);
        startButton.setOnAction(value -> {

            SokobanGame game = new SokobanGame();

        });

        Button exitButton = new Button("Exit");
        componentLayout.getChildren().add(exitButton);
        exitButton.setOnAction(value -> {

            System.exit(0);

        });
    }

}
