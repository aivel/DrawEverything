package sample.utils;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.*;
import java.net.URL;

/**
 * Created by Max on 05.03.2015.
 */
public class DownloadManager {
    public static void download(final String downloadUrl, final String savePath,
                                final boolean forced, final ActionStatusChangedCallback callback) {
        AsyncTask<Void> asyncTask = new AsyncTask<Void>() {
            @Override
            public Void doInBackground() {
                if (downloadUrl == null || savePath == null
                        || callback == null || (new File(savePath).exists() && !forced)) {
                    if (callback != null)
                        callback.onFail();

                    return null;
                }

                URL url = null;

                try {
                    url = new URL(downloadUrl);

                    InputStream in = new BufferedInputStream(url.openStream());
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int n = 0;

                    while (-1 != (n = in.read(buf))) {
                        out.write(buf, 0, n);
                    }

                    out.close();
                    in.close();

                    byte[] response = out.toByteArray();

                    if (forced || !(new File(savePath).exists())) {
                        FileOutputStream fos = new FileOutputStream(new URL(savePath).getFile());
                        fos.write(response);
                        fos.close();
                    } else
                        exception(null);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                    exception(e);
                }

                return null;
            }

            @Override
            public void postExecute(Void result) {
                callback.onSuccess();
            }

            @Override
            public void exception(Exception e) {
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        callback.onFail();
                        return null;
                    }
                };
                Platform.runLater(task);
            }
        };
        asyncTask.start();
    }

}
