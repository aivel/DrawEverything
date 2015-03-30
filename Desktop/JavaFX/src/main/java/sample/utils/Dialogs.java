package sample.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by max on 3/27/15.
 */
public class Dialogs {
    public static void showOneButtonDialog(final String title, final String someText) {
        final Stage ONE_BUTTON_DIALOG;

        ONE_BUTTON_DIALOG = new Stage();
        ONE_BUTTON_DIALOG.initModality(Modality.APPLICATION_MODAL);
        Button okButton = new Button("Ок");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ONE_BUTTON_DIALOG.close();
            }
        });

        final VBox vbox = new VBox();
        final Text text = new Text(someText);

        text.setTextAlignment(TextAlignment.CENTER);
        vbox.setSpacing(20.0);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(text);
        vbox.getChildren().add(okButton);

        final Scene scene = new Scene(vbox);

        ONE_BUTTON_DIALOG.setTitle(title);
        ONE_BUTTON_DIALOG.setScene(scene);
        ONE_BUTTON_DIALOG.show();
    }
}
