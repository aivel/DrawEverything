package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.MainScene;

import java.io.File;

public class Main extends Application {
    static public String workingDirectory = System.getProperty("user.dir");
    static public String imagesPath = workingDirectory + File.separator + "resources"
            + File.separator + "images" + File.separator;
    static public FXMLLoader mainRoot;
    static public FXMLLoader drawRoot;
    static public FXMLLoader allRoot;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainRoot = new FXMLLoader(new File(workingDirectory + "/resources/scenes/main.fxml").toURI().toURL());
        drawRoot = new FXMLLoader(new File(workingDirectory + "/resources/scenes/draw.fxml").toURI().toURL());
        allRoot  = new FXMLLoader(new File(workingDirectory + "/resources/scenes/all.fxml").toURI().toURL());
        mainRoot.load();
        drawRoot.load();
        allRoot.load();
        ((MainScene)mainRoot.getController()).onLoad();

        primaryStage.setTitle("Draw Everything");
        primaryStage.setScene(new Scene(mainRoot.getRoot(), 880, 850));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
