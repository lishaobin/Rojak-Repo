package com.Stiw3054.groupProject;

public class displayAll {
    ObjectTable [] playerlist;

    public displayAll( ObjectTable [] playerlist) { //<-------------------- bring in player list
        this.playerlist = playerlist;
    }
                                                                                                  // THis  is category         this is each row data
    public void  displayall(){
        for(int y = 0; y < 10;y++ ) {                                                                      //    ^               ^
            for (int x = 0; x < playerlist[1].getArrayRK().length; x++) {                                 //    /|\             /|\
                if (playerlist[y].getArrayRK()[x] != null) {                                              //     |               |
                    System.out.printf("\n| %-3s  |  %-3s  |  %-42s | %-4s | %-15s  | %-4s | %-50s |", playerlist[y].getArrayRK()[x], playerlist[y].getArraySno()[x], playerlist[y].getArrayName()[x], playerlist[y].getArrayRtg()[x], playerlist[y].getArrayState()[x], playerlist[y].getArrayPts()[x], playerlist[y].getArrayCat()[x]);
                }
            }System.out.println();
        }

    }
}
