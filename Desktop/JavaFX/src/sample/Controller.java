package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label label;

    public void onAction(ActionEvent actionEvent) {
        label.setText("Supertext");
    }
}
