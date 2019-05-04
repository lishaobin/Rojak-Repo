package com.Stiw3054.groupProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

public class CheckURL {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        String playerlist = ""; // all player list.

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorservice = Executors.newFixedThreadPool(coreCount);

        Path filePath = Paths.get("C:\\Users\\User\\IdeaProjects\\Rojak-Repo-254301\\groupProject\\url.txt"); // Change to your own path
        String path = String.valueOf(filePath.getParent());
        String fileName = String.valueOf(filePath.getFileName());

        PropertiesFile propertiesFile = new PropertiesFile();
        propertiesFile.WritePropertiesFile(path, fileName);
        //propertiesFile.ReadPropertiesFile();

        List<String> myURLArrayList = Files.readAllLines(filePath);
        List<String> ValidURLlist = new ArrayList<>();
        System.out.println("\nVerifying the URLs ......");

        for (String url : myURLArrayList) {
            Future<String> ValidURL = executorservice.submit(new NetworkConnection(path, url));
            if(!ValidURL.get().equals("0"))
            ValidURLlist.add(ValidURL.get());
        }
        ValidURLlist.forEach(System.out::println);
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

        CheckTable CheckT = new CheckTable(ValidURLlist);
        CheckT.Checktable();
        playerlist = CheckT.getPlayerList();
        System.out.println("\n"+playerlist);


        System.out.println("\nExecution time in milliseconds: " + executeTime);
    }
}


