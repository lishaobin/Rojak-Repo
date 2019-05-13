package com.Stiw3054.groupProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class DisplayPlayerFromKedah {
    private ObjectTable[] playerlist;
    private Properties prop = new Properties();

    DisplayPlayerFromKedah(ObjectTable[] playerlist) { //<-------------------- bring in player list
        this.playerlist = playerlist;
    }

    void RetrievePlayer() {
        try (InputStream input = new FileInputStream("config.properties")) {
            // load a properties file
            prop.load(input);
            for (int y = 0; y < 10; y++) {
                for (int x = playerlist[1].getArrayRK().length-1; x>0; x--) { //Sorting winning points in ascending order.
                    if (playerlist[y].getArrayRK()[x] != null) {
                        if ((playerlist[y].getArrayState()[x].equals("KEDAH"))) {
                            System.out.printf("| %-3s  |  %-3s  |  %-42s | %-4s | %-15s  | %-4s | %-50s |\n", playerlist[y].getArrayRK()[x], playerlist[y].getArraySno()[x], playerlist[y].getArrayName()[x], playerlist[y].getArrayRtg()[x], prop.getProperty("sTATE"), playerlist[y].getArrayPts()[x], playerlist[y].getArrayCat()[x]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

