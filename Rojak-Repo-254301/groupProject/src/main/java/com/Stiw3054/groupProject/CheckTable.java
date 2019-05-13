package com.Stiw3054.groupProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import java.io.IOException;

public class CheckTable {
    List <String> ValidURLList;
    Document doc;
    String xxx= "";
    ObjectTable [] playerlist = new ObjectTable[10];
    int countvtable = 0;
    String cat;


    public CheckTable(List <String> ValidURLList){

        this.ValidURLList =ValidURLList;

    }

    public void Checktable () throws IOException {

        for (int x = 0; x < ValidURLList.size(); x++) {
            int count = 0;
            boolean ifvalid = false;

            ObjectTable objectTable = new ObjectTable();
            doc = Jsoup.connect(ValidURLList.get(x)).get();
            Elements table = doc.select("table.CRs1 tr");
            for (Element row : table) {
                Elements tds = row.getElementsByTag("td");
                ifvalid =tds.hasText();
                if (ifvalid) {
                    Elements table2 = doc.select("div.defaultDialog h2");
                    for (Element row1 : table2) {
                        Elements tds1 = row1.getElementsMatchingText("\\d{4}?");
                        if (tds1.hasText()){
                            cat =tds1.text();
                            //System.out.println(tds1.text());
                        }
                    }
                    objectTable.setArrayRK(row.select("td:nth-child(1)").text(), count);//
                    objectTable.setArraySno(row.select("td:nth-child(2)").text(),count);
                    objectTable.setArrayName(row.select(" td:nth-child(4)").text(),count);
                    objectTable.setArrayRtg( row.select("td.CRr").text(),count);
                    objectTable.setArrayState(row.select("td:nth-child(7)").text(),count);
                    objectTable.setArrayPts(row.select("td:nth-child(8)").text(),count);
                    objectTable.setArrayCat(cat,count);
                    count ++;

                }
                else{// enter log file

                }


            }
            //System.out.println(ifvalid);
            if (ifvalid) {
                playerlist[countvtable] = objectTable;
                countvtable++;
            }
        }

        /*for(String ppp:playerlist[1].getArrayRK()){
            System.out.println(ppp);
        }*/
    }

    public ObjectTable [] getPlayerList() {
        return playerlist;
    }
}

