package com.Stiw3054.groupProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
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
                for (int x = 0; x < playerlist[1].getArrayRK().length; x++) {
                    if (playerlist[y].getArrayRK()[x] != null) {
                        if ((playerlist[y].getArrayState()[x].equals("KEDAH"))) {
                            int finalX = x;
                            Arrays.sort(new String[]{playerlist[y].getArrayPts()[x], String.valueOf(new Comparator<String[]>() {   //* Sorting
                                @Override
                                public int compare(String[] o1, String[] o2) {
                                    Double S1, S2;
                                    S1 = Double.valueOf(o1[finalX]);
                                    S2 = Double.valueOf(o2[finalX]);
                                    return S1.compareTo(S2);
                                }
                            })});                                                                                                 //* Sorting
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
