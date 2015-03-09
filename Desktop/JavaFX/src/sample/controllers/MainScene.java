package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.providers.StepImagesProvider;
import sample.utils.ActionStatusChangedCallback;

import java.util.ArrayList;
import java.util.List;

public class MainScene {
    public GridPane gridPopularLessons;
    public GridPane gridNewLessons;
    public GridPane gridNewThemes;
    public GridPane gridPopularThemes;

    public void onBtnMainSceneAction(ActionEvent actionEvent) {
        final List<GridPane> grids = new ArrayList<>(4);
        grids.add(gridPopularLessons);
        grids.add(gridNewLessons);
        grids.add(gridNewThemes);
        grids.add(gridPopularThemes);

        StepImagesProvider.putImage("z", 0, "http://www.entrepreneurmag.co.za/wp-content/uploads/2014/05/Multiplying-money_personal-finance-120x150.jpg",
                new ActionStatusChangedCallback() {
                    @Override
                    public void onSuccess() {
                        Image image = StepImagesProvider.getImage("z", 0);

                        if (image == null)
                            return;

                        for (int k = 0; k < grids.size(); k++) {
                            grids.get(k).getChildren().clear();

                            for (int i = 0; i < 4; i++) {
                                final Node imageNode =
                                        new ImageView(image);
                                final int finalI = i;
                                imageNode.setOnMouseClicked(event -> {
                                    System.out.println("Mouse clicked: " + finalI);
                                });
                                imageNode.setId("");
                                final Node label = new Label("qqq");
                                label.setTranslateX(15.0);
                                label.setTranslateY(83.0);

                                grids.get(k).add(imageNode, i, 0);
                                grids.get(k).add(label, i, 0);
                            }
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    public void onBtnCurrentLessonAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().setRoot(Main.drawRoot);
    }
}
