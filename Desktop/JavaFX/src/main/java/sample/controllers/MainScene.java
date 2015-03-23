package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.model.Lesson;
import sample.utils.AsyncTask;
import sample.utils.NodesManager;

import java.util.List;

import static sample.utils.API.JSONListToLessonList;
import static sample.utils.API.downloadLessons;

public class MainScene {
    public GridPane gridPopularLessons;
    public GridPane gridNewLessons;
    public GridPane gridNewThemesLessons;
    public GridPane gridPopularThemesLessons;
    public List<Lesson> newLessons;
    public List<Lesson> popularLessons;
    public List<Lesson> newThemesLessons;
    public List<Lesson> popularThemesLessons;

    public void onBtnMainSceneAction(ActionEvent actionEvent) {
        onLoad();
    }

    public void onBtnCurrentLessonAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().setRoot(Main.drawRoot.getRoot());
    }

    public void onBtnAllLessonsAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().setRoot(Main.allRoot.getRoot());
        ((AllLessonsScene)Main.allRoot.getController()).onLoad();
    }

    public void onLoad() {
        final String urlNewLessons = "http://howtodraw.azurewebsites.net/HowToDraw/API/lessons/0?sort=NEW";
        final String urlPopularLessons = "http://howtodraw.azurewebsites.net/HowToDraw/API/lessons/0?sort=VIEWS";
        final String urlNewThemesLessons = "http://howtodraw.azurewebsites.net/HowToDraw/API/lessons/0?sort=OLD";
        final String urlPopularThemesLessons = "http://howtodraw.azurewebsites.net/HowToDraw/API/lessons/0?sort=RATING";

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            public Object doInBackground() {
                popularLessons = JSONListToLessonList(downloadLessons(urlPopularLessons));
                newLessons = JSONListToLessonList(downloadLessons(urlNewLessons));
                popularThemesLessons = JSONListToLessonList(downloadLessons(urlPopularThemesLessons));
                newThemesLessons = JSONListToLessonList(downloadLessons(urlNewThemesLessons));
                return null;
            }

            @Override
            public void postExecute(Object result) {
                NodesManager.putLessonsToGridPane(popularLessons, gridPopularLessons, 0);
                NodesManager.putLessonsToGridPane(newLessons, gridNewLessons, 0);
                NodesManager.putLessonsToGridPane(popularThemesLessons, gridPopularThemesLessons, 0);
                NodesManager.putLessonsToGridPane(newThemesLessons, gridNewThemesLessons, 0);
            }

            @Override
            public void exception(Exception e) {

            }
        };
        asyncTask.start();
    }
}
