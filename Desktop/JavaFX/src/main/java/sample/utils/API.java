package sample.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.model.Lesson;
import sample.providers.LessonsProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 18.03.2015.
 */
public class API {
    public static final String URL_DOWNLOAD_LESSON_STEP = "http://howtodraw.azurewebsites.net/HowToDraw/API/lesson/%s?step=%s";

    public static JSONObject callAPI(final String url) {
        String result = null;

        try {
            URL request_url = new URL(url);
            HttpURLConnection http_conn;

            http_conn = (HttpURLConnection)request_url.openConnection();
            http_conn.setFollowRedirects(true);
            http_conn.setConnectTimeout(3000);
            http_conn.setReadTimeout(3000);
            http_conn.setInstanceFollowRedirects(true);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(http_conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            result = response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) new JSONParser().parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static List<JSONObject> downloadLessons(final String url) {
        JSONObject jsonObject = API.callAPI(url);
        boolean success = (boolean) jsonObject.get("success");

        if (!success)
            return new ArrayList<>(0);

        List<JSONObject> lessons = (List<JSONObject>) jsonObject.get("lessons");

        return lessons;
    }

    public static List JSONListToLessonList(final List<JSONObject> lessons) {
        final String urlPreview = "http://howtodraw.azurewebsites.net/HowToDraw/API/lesson_prev/";

        List<Lesson> lessonsLst = new ArrayList<>(lessons.size());

        for(JSONObject lesson: lessons) {
            Lesson l = new Lesson(
                    ((Long) lesson.get("id")).intValue(),
                    (String)lesson.get("title"),
                    (String)lesson.get("titleEn"),
                    ((Long) lesson.get("rating")).intValue(),
                    ((Long) lesson.get("views")).intValue(),
                    (String)lesson.get("chapter"),
                    ((Long) lesson.get("localId")).intValue(),
                    ((Long) lesson.get("complexity")).intValue(),
                    ((Long) lesson.get("steps")).intValue(),
                    (String)lesson.get("format"),
                    urlPreview + lesson.get("id").toString());
            lessonsLst.add(l);
            LessonsProvider.getInstance().putLesson(l);
        }

        return lessonsLst;
    }
}
