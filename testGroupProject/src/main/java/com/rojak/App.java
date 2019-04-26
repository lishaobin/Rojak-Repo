package com.rojak;

import javax.swing.text.Document;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    private String line;
    Path path = Paths.get("C:\\Users\\Lai Yee\\IdeaProjects\\testGroupProject\\URL.txt");

    public App(String line) {
        this.line = line;
    }
    public void checkURL(){

        try {
            String line = String.valueOf(Files.readAllLines(path));
            if (validURL(line)) {
                System.out.println(Thread.currentThread().getName() + " --> " + line);
            } else {
                logFile lDemo = new logFile();
                lDemo.testlogFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean validURL(String link) {

        try {
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection check= (HttpURLConnection) new URL(line).openConnection();
            check.setConnectTimeout(1000);
            check.setReadTimeout(1000);
            check.setRequestMethod("GET");
            return (check.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }

    }

}
