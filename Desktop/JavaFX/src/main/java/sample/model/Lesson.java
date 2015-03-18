package sample.model;

/**
 * Created by Max on 07.03.2015.
 */
public class Lesson {
    private int id;
    private String title;
    private String titleEn;
    private int rating;
    private int views;
    private String chapter;
    private int localId;
    private int complexity;
    private int steps;
    private String format;
    private String preview;

    public Lesson(int id, String title, String titleEn, int rating, int views, String chapter, int localId, int complexity, int steps, String format, String preview) {
        this.id = id;
        this.title = title;
        this.titleEn = titleEn;
        this.rating = rating;
        this.views = views;
        this.chapter = chapter;
        this.localId = localId;
        this.complexity = complexity;
        this.steps = steps;
        this.format = format;
        this.preview = preview;
    }

    public long getId() {
        return id;
    }

    public String getPreview() {
        return preview;
    }

    public String getTitle() {
        return title;
    }

    public int getLocalId() {
        return localId;
    }

    public int getComplexity() {
        return complexity;
    }

    public int getSteps() {
        return steps;
    }

    public String getFormat() {
        return format;
    }

    public String getChapter() {
        return chapter;
    }

    public int getViews() {
        return views;
    }

    public int getRating() {
        return rating;
    }

    public String getTitleEn() {
        return titleEn;
    }
}
