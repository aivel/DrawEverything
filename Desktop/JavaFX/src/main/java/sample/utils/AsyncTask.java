package sample.utils;

import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * Created by Max on 07.03.2015.
 */
public abstract class AsyncTask<T> {
    public abstract T doInBackground();
    public abstract void postExecute(T result);
    public abstract void exception(Exception e);

    public void start() {
        new Thread(() -> {
            final T result = doInBackground();

            Task<T> task = new Task<T>() {
                @Override
                protected T call() throws Exception {
                    postExecute(result);
                    return result;
                }
            };
            Platform.runLater(task);
        }).start();
    }
}
