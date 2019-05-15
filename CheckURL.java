package com.Stiw3054.groupProject.groupProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CheckURL {

    public static void main(String[] args) throws Exception {

        List playerlist ; // all player list.

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorservice = Executors.newFixedThreadPool(coreCount);

        Path filePath = Paths.get("D:\\url.txt"); // Change to your own path
        String path = String.valueOf(filePath.getParent());
        String fileName = String.valueOf(filePath.getFileName());

        PropertiesFile propertiesFile = new PropertiesFile();
        propertiesFile.WritePropertiesFile(path, fileName);
        propertiesFile.ReadPropertiesFile();

        List<String> myURLArrayList = Files.readAllLines(filePath);
        List<String> ValidURLlist = new ArrayList<>();
        System.out.println("\nVerifying the URLs ......");

        for (String url : myURLArrayList) {
            Future<String> ValidURL = executorservice.submit(new NetworkConnection(path, url));
            if (!ValidURL.get().equals("0"))
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
        List<List> Alldata = CheckT.getPlayerList();
        System.out.println("\n\n| RK     |  Sno    |  Name                                     | Rtg      | State             | Pts      | Category  |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        for(List<String> Rowdata:Alldata){
            String rank = Rowdata.get(0);
            String sNo = Rowdata.get(1);
            String name = Rowdata.get(3);
            String rtg = Rowdata.get(5);
            String state = Rowdata.get(6);
            String pts = Rowdata.get(7);
            String cat = Rowdata.get(11);
            System.out.printf("| %-5s  |  %-5s  |  %-40s | %-8s | %-16s  | %-8s | %-8s  |\n", rank, sNo, name, rtg, state, pts, cat);
        }

        DisplayPlayerFromKedah displayPlayerFromKedah = new DisplayPlayerFromKedah(ValidURLlist);
        displayPlayerFromKedah.RetrievePlayer();
        
        CountWinningPoints c=new CountWinningPoints(ValidURLlist);
        c.count(playerlist);

//        DisplayStatistics displayStatistics=new DisplayStatistics(ValidURLlist);
//        displayStatistics.run();

        System.out.println("\n\nExecution time in milliseconds: " + executeTime);
    }
}

