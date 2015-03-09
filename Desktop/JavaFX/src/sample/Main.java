package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    static public String workingDirectory = System.getProperty("user.dir");
    static public String imagesPath = workingDirectory + File.separator + "resources"
            + File.separator + "images" + File.separator;
    static public Parent mainRoot;
    static public Parent drawRoot;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainRoot = FXMLLoader.load(getClass().getResource("scenes/main.fxml"));
        drawRoot = FXMLLoader.load(getClass().getResource("scenes/draw.fxml"));
        primaryStage.setTitle("MONEY TREE");
        primaryStage.setScene(new Scene(mainRoot, 1024, 850));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
