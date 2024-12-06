package com.example.cincuentazo.View;

import com.example.cincuentazo.Controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    private GameController gameController;

    public GameStage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cincuentazo/hello-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        Scene scene = new Scene(root);
        setTitle("Cincuentazo");
        setResizable(false);
        setScene(scene);
        show();

    }

    public GameController getController() {
        return gameController;
    }

    public static GameStage getInstance() throws IOException {
        if(GameStageHolder.INSTANCE == null){
            GameStageHolder.INSTANCE = new GameStage();
        }
        return GameStageHolder.INSTANCE;
    }

    public static class GameStageHolder{
        private static  GameStage INSTANCE;
    }

}
