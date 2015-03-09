package sample.model;

/**
 * Created by Max on 07.03.2015.
 */
public class Lesson {
    private long id;
    private int complexity;
    private int steps;
    private String title;
    private String filenamePrefix;

    public Lesson(long id, final int complexity, final int steps,
                  final String title, final String filenamePrefix) {
        this.id = id;
        this.complexity = complexity;
        this.steps = steps;
        this.title = title;
        this.filenamePrefix = filenamePrefix;
    }

    public long getId() {
        return id;
    }
}
