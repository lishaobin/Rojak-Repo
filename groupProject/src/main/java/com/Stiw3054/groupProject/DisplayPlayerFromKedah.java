package com.Stiw3054.groupProject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

class DisplayPlayerFromKedah {
    private List<String> ValidURLList;
    private Properties prop = new Properties();

    DisplayPlayerFromKedah(List<String> ValidURLList) {
        this.ValidURLList = ValidURLList;
    }

    void RetrievePlayer() throws IOException, ParseException {

        System.out.println("\nAll players From Kedah ......");
        System.out.println("");
        System.out.println("RK\t   |Sno\t\t |Name\t\t\t\t\t\t\t   |Rtg\t\t  |\tState\t  |\tPts\t\t |Category");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (String s : ValidURLList) {
            Document doc = Jsoup.connect(s).get();
            // System.out.println(doc.outerHtml());
            Elements b = doc.select("div:nth-child(2)");
            for (Element header : b) {
                Elements h2 = header.select("h2");
                String category = h2.text();
                if (StringUtils.contains(category, "NATIONAL")) {
                    String showCat = StringUtils.substringAfter(category, "9");
                    Elements a = doc.select("table.CRs1 tr");
                    for (Element row : a) {
                        String state = row.select("td:nth-child(7)").text();
                        String rank = row.select("td:nth-child(1)").text();
                        String sno = row.select("td:nth-child(2)").text();
                        String name = row.select(" td:nth-child(4)").text();
                        String rtg = row.select("td.CRr").text();
                        String pts = row.select("td:nth-child(8)").text();
//                        NumberFormat format = NumberFormat.getInstance(Locale.US);
//                        Number number = format.parse(pts).doubleValue();
//                        System.out.println(number);
                        if (state.contains("KEDAH")) {
                            ShowPlayer(rank, sno, name, rtg, pts, showCat);
                        }
                    }
                }
            }
        }
    }

    private void ShowPlayer(String rank, String sNo, String name, String rtg, String  replace, String showCat) {
        try (InputStream input = new FileInputStream("config.properties")) {    // load a properties file
            prop.load(input);
            System.out.printf("\n%-5s  |  %-5s  |  %-30s | %-8s | %-8s  | %-8s | %-8s", rank, sNo, name, rtg, prop.getProperty("sTATE"), replace, showCat);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
