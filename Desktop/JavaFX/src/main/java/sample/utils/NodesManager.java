package sample.utils;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Main;
import sample.controllers.DrawScene;
import sample.model.Lesson;
import sample.providers.StepImagesProvider;

import java.util.List;

/**
 * Created by Max on 18.03.2015.
 */
public class NodesManager {
    public static void putLessonsToGridPane(final List<Lesson> lessons, final GridPane gridPane) {
        gridPane.getChildren().clear();
        final int PREVIEW_INDEX = -1;

        for(int i = 0; i < lessons.size(); i++) {
            final Lesson lesson = lessons.get(i);
            final String imgName = lesson.getLocalId() + "_" + lesson.getId();
            final int finalI = i;

            StepImagesProvider.putImage(imgName, PREVIEW_INDEX, lesson.getPreview(),
                    new ActionStatusChangedCallback() {
                        @Override
                        public void onSuccess() {
                            Image image = StepImagesProvider.getImage(imgName, PREVIEW_INDEX);

                            if (image == null)
                                return;

                            final ImageView imageNode = new ImageView(image);
                            imageNode.setOnMouseClicked(event -> {
                                for (int j = 0; j < lesson.getSteps(); j++) {
                                    StepImagesProvider.putImage(imgName,
                                            j, String.format(API.URL_DOWNLOAD_LESSON_STEP, lesson.getId(),
                                                    j), new ActionStatusChangedCallback() {
                                                @Override
                                                public void onSuccess() {
                                                    ((Node) event.getSource()).getScene().setRoot(Main.drawRoot.getRoot());
                                                    ((DrawScene) Main.drawRoot.getController()).onLoadLesson(lesson);
                                                }

                                                @Override
                                                public void onFail() {

                                                }
                                            });
                                }
                            });
                            imageNode.setFitHeight(130);
                            imageNode.setFitWidth(100);
                            imageNode.preserveRatioProperty().set(false);

                            final Label label = new Label(lesson.getTitleEn());
                            label.getStyleClass().add("centered_text");

                            final VBox pane = new VBox();
                            pane.getChildren().add(imageNode);
                            pane.getChildren().add(label);
                            pane.getStyleClass().add("card");
                            pane.setAlignment(Pos.CENTER);

                            gridPane.add(pane, finalI, 0);
                        }

                        @Override
                        public void onFail() {
                        }
                    });
        }
    }
}
