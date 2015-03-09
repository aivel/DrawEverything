package sample.providers;

import javafx.scene.image.Image;
import sample.Main;
import sample.utils.DownloadManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Max on 05.03.2015.
 */
public class StepImagesProvider {
    private static final String stepFileExtension = ".png";
    private static final Map<String, Image> images = new HashMap<>();

    private static String constructFilename(final String filenamePrefix, final int step) {
        return "file:" + File.separator + Main.imagesPath + filenamePrefix + "_" + step + stepFileExtension;
    }

    public static void putImage(final String filenamePrefix, final int step,
                                final Image image) {
        final String filename = constructFilename(filenamePrefix, step);
        images.put(filename, image);
    }

    public static void putImage(final String filenamePrefix, final int step,
                                final String url) {
        final String filename = constructFilename(filenamePrefix, step);
        DownloadManager.download(url, filename, false, new DownloadManager.DownloadStatusCallback() {
            @Override
            public void onDownloadComplete() {
                synchronized (images) {
                    images.put(filename, new Image(filename));
                }
            }

            @Override
            public void onDownloadFailed() {
            }
        });
    }

    public static Image getImage(final String filenamePrefix, final int step) {
        final String filename = constructFilename(filenamePrefix, step);
        final Image[] image = new Image[1];
        image[0] = null;

        Optional<Map.Entry<String, Image>> entry = images.entrySet()
                .stream()
                .filter(entity -> entity.getKey().equals(filename))
                .findFirst();

        return entry.isPresent() ? entry.get().getValue() : null;
    }

    private static class Holder {
        static final StepImagesProvider INSTANCE = new StepImagesProvider();
    }

    public static StepImagesProvider getInstance() {
        return Holder.INSTANCE;
    }
}