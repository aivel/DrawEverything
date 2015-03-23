package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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
    private static int MAX_PAGE = 33;
    private static final int LESSONS_PER_ROW = 6;
    private final int STARTING_PAGE = 0;
    public TextField edtSearch;
    private int currentPage;
    private final int LESSON_ROWS = 4;
    private final String URL_LESSONS = "http://howtodraw.azurewebsites.net/HowToDraw/API/lessons/%s";
    public GridPane r;

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
        r.getChildren().clear();
        MAX_PAGE = Integer.MAX_VALUE;

        for (int i = 0; i < LESSON_ROWS; i++) {
            final int finalI = i;

            AsyncTask asyncTask = new AsyncTask() {
                    List<Lesson> lessons = new ArrayList<>();
                @Override
                public Object doInBackground() {
                    lessons = API.JSONListToLessonList(
                            API.downloadLessons(String.format(URL_LESSONS, currentPage + finalI)));

                    if (lessons == null || lessons.size() < LESSONS_PER_ROW) {
                        MAX_PAGE = currentPage;
                    }

                    return null;
                }

                @Override
                public void postExecute(Object result) {
                    NodesManager.putLessonsToGridPane(lessons, r, finalI);
                }

                @Override
                public void exception(Exception e) {
                }
            };

            asyncTask.start();
        }
    }

    private void prevPage() {
        if (currentPage <= STARTING_PAGE)
            return;

        if (currentPage > STARTING_PAGE)
            currentPage -=  LESSON_ROWS;

        updateCurrentPage();
    }

    private void nextPage() {
        if (currentPage == MAX_PAGE)
            return;

        if (currentPage < MAX_PAGE)
            currentPage += LESSON_ROWS;

        updateCurrentPage();
    }

    private void updateCurrentPage() {
        onLoad();
        edtSearch.setText("");
    }

    public void onBtnPrevPageAction(ActionEvent event) {
        prevPage();
    }

    public void onBtnNextPageAction(ActionEvent event) {
        nextPage();
    }

    public void onEdtSearchKeyTyped(Event event) {

    }

    public void onBtnSearchAction(ActionEvent actionEvent) {
        r.getChildren().clear();

        for (int i = 0; i < LESSON_ROWS; i++) {
            final int finalI = 0;

            AsyncTask asyncTask = new AsyncTask() {
                List<Lesson> lessons = new ArrayList<>();

                @Override
                public Object doInBackground() {
                    int i = 0;

                    while (true) {
                        final List<Lesson> tmpLessons = API.JSONListToLessonList(
                                API.downloadLessons(
                                        String.format(API.URL_SEARCH, i, edtSearch.getText())
                                ));

                        i += LESSON_ROWS;

                        if (tmpLessons == null || tmpLessons.size() <= 0)
                            break;

                        lessons.addAll(tmpLessons);
                    }

                    return null;
                }

                @Override
                public void postExecute(Object result) {
                    for (int j = 0; j < LESSON_ROWS; j++) {
                        final List<Lesson> sublist = lessons.subList(j * LESSONS_PER_ROW, j * LESSONS_PER_ROW + LESSONS_PER_ROW);

                        NodesManager.putLessonsToGridPane(sublist, r, j);
                    }
                }

                @Override
                public void exception(Exception e) {

                }
            };

            asyncTask.start();
        }

//        System.out.printf(String.valueOf(), false, "")));
    }
}
