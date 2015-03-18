package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.model.Lesson;
import sample.utils.API;
import sample.utils.AsyncTask;
import sample.utils.NodesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 18.03.2015.
 */
public class AllLessonsScene {
    private static final int MAX_PAGE = 3;
    private final int STARTING_PAGE = 0;
    private int currentPage;
    private final int LESSON_ROWS = 4;
    private final String URL_LESSONS = "http://howtodraw.azurewebsites.net/HowToDraw/API/lessons/%s";
    public GridPane r2;
    public GridPane r1;
    public GridPane r3;
    public GridPane r4;
    private List<GridPane> grids;

    public void onBtnCurrentLessonAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().setRoot(Main.drawRoot.getRoot());
    }

    public void onBtnMainSceneAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().setRoot(Main.mainRoot.getRoot());
    }

    public void onBtnPrevStepAction(ActionEvent event) {

    }

    public void onBtnNextStepAction(ActionEvent event) {

    }

    public void onLoad() {
        grids = new ArrayList<>(LESSON_ROWS);
        grids.add(r1);grids.add(r2);grids.add(r3);grids.add(r4);

        for (int i = 0; i < LESSON_ROWS; i++) {
            final int finalI = i;

            AsyncTask asyncTask = new AsyncTask() {
                    List<Lesson> lessons = new ArrayList<>();
                @Override
                public Object doInBackground() {
                    lessons = API.JSONListToLessonList(
                            API.downloadLessons(String.format(URL_LESSONS, currentPage + finalI)));
                    return null;
                }

                @Override
                public void postExecute(Object result) {
                    for (int j = 0; j < lessons.size(); j++) {
                        NodesManager.putLessonsToGridPane(lessons, grids.get(finalI));
                    }
                }

                @Override
                public void exception(Exception e) {

                }
            };

            asyncTask.start();
        }
    }

    private void prevPage() {
        if (currentPage < STARTING_PAGE)
            return;

        if (currentPage > STARTING_PAGE)
            currentPage--;

        updateCurrentPage();
    }

    private void nextPage() {
        if (currentPage < MAX_PAGE)
            currentPage++;

        updateCurrentPage();
    }

    private void updateCurrentPage() {
        onLoad();
    }

    public void onBtnPrevPageAction(ActionEvent event) {
        prevPage();
    }

    public void onBtnNextPageAction(ActionEvent event) {
        nextPage();
    }
}
