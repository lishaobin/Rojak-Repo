package com.Stiw3054.groupProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CheckURL {

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorservice = Executors.newFixedThreadPool(coreCount);

        Path filePath = Paths.get("C:\\Users\\USER\\Desktop\\Sem 4\\realtime\\Group Project\\groupProject\\url.txt"); // Change to your own path
        String path = String.valueOf(filePath.getParent());
        String fileName = String.valueOf(filePath.getFileName());

        PropertiesFile propertiesFile = new PropertiesFile();
        propertiesFile.WritePropertiesFile(path, fileName);
        propertiesFile.ReadPropertiesFile();

        List<String> myURLArrayList = Files.readAllLines(filePath);

        System.out.println("\nVerifying the URLs ......");

        myURLArrayList.forEach((String url) -> {
            Thread checkurl = new Thread(new NetworkConnection(path, url));
            executorservice.execute(checkurl);
        });

        executorservice.shutdown();

        while (!executorservice.isTerminated()) {
        }

        try {
            if (!executorservice.awaitTermination(60, TimeUnit.SECONDS)) { //wait for existing tasks to terminate
                executorservice.shutdownNow(); //cancel currently executing task
                if (!executorservice.awaitTermination(60, TimeUnit.SECONDS)) { //wait for tasks to respond to being cancelled
                    System.err.println("Service didn't terminate!");
                }
            }
        } catch (InterruptedException e) {
            executorservice.shutdownNow(); //re-cancel if current thread also interrupted
            Thread.currentThread().interrupt(); //preserve interrupt status
        }

        System.out.println("\nExecution time in milliseconds: " + executeTime);
    }
}


