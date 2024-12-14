package com.example.cincuentazo.View;

import com.example.cincuentazo.Controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

/**
 * Represents the main stage of the game, using the Singleton pattern.
 */
public class GameStage extends Stage {
    private GameController gameController;

    public GameStage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cincuentazo/hello-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        Scene scene = new Scene(root);
        setTitle("Cincuentazo");
        this.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/cincuentazo/images/icono.png")));
        setResizable(false);
        setScene(scene);
        show();

    }

    public GameController getController() {
        return gameController;
    }

    /**
     * Returns the singleton instance of GameStage.
     *
     * @return the singleton instance of GameStage
     * @throws IOException if the FXML file cannot be loaded
     */
    public static GameStage getInstance() throws IOException {
        if(GameStageHolder.INSTANCE == null){
            GameStageHolder.INSTANCE = new GameStage();
        }
        return GameStageHolder.INSTANCE;
    }

    /**
     * Holder class for the singleton instance of GameStage.
     */
    public static class GameStageHolder{
        private static  GameStage INSTANCE;
    }

}
