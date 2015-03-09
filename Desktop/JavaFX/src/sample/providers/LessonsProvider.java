package sample.providers;

import sample.model.Lesson;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Max on 05.03.2015.
 */
public class LessonsProvider {
    private static List<Lesson> lessons;

    public LessonsProvider() {
        lessons = new LinkedList<>();
    }

    public void putLesson(final Lesson lesson) {
        lessons.add(lesson);
    }

    public Lesson getLesson(final long i) {
        return lessons.stream().filter(lesson -> lesson.getId() == i).findFirst().get();
    }

    private static class Holder {
        static final LessonsProvider INSTANCE = new LessonsProvider();
    }

    public static LessonsProvider getInstance() {
        return Holder.INSTANCE;
    }
}