package com.Stiw3054.groupProject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkConnection extends Thread {
    private String url, path;

    NetworkConnection(String path, String url) {
        this.path = path;
        this.url = url;
    }

    private boolean checkURL(String url) {

        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
            connection.setRequestMethod("HEAD");
            return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void run() {
        if (checkURL(url)) {
            System.out.println(Thread.currentThread().getName() + "-" + url + ": Exist");
        } else{
            try {
                LogFile logFile = new LogFile();
                logFile.createLogFile(path, url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
