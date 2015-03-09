package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import sample.Main;

/**
 * Created by Max on 07.03.2015.
 */
public class DrawScene {
    public ColorPicker colorPicker;
    public Canvas canvas;

    public void onBtnMainSceneAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().setRoot(Main.mainRoot);
    }

    public void onBtnCurrentLessonAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().setRoot(Main.drawRoot);
    }

    public void onCanvasMouseMoved(Event event) {

    }

    public void onCanvasMousePressed(Event event) {
        MouseEvent me = (MouseEvent)event;
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
        canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
        canvas.getGraphicsContext2D().setLineWidth(2.0);
        canvas.getGraphicsContext2D().beginPath();
        canvas.getGraphicsContext2D().moveTo(me.getX(), me.getY());
        canvas.getGraphicsContext2D().stroke();
    }

    public void onCanvasMouseReleased(Event event) {
        canvas.getGraphicsContext2D().closePath();
    }

    public void onCanvasMouseDragged(Event event) {
        MouseEvent me = (MouseEvent)event;
        canvas.getGraphicsContext2D().lineTo(me.getX(), me.getY());
        canvas.getGraphicsContext2D().stroke();
    }

    public void onBtnPrevStepAction(ActionEvent event) {

    }

    public void onBtnNextStepAction(ActionEvent event) {

    }
}
