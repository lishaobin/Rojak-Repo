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
    String tabletext = "";
    String xxx= "";
    String yyy= "";

    public CheckTable(List <String> ValidURLList){

        this.ValidURLList =ValidURLList;

    }

    public void Checktable () throws IOException {

        for (int x = 0; x < ValidURLList.size(); x++) {
            doc = Jsoup.connect(ValidURLList.get(x)).get();
            Elements table = doc.select("table.CRs1 tr");
            for (Element tr : table) {
                Elements tds = tr.getElementsByTag("td");

                if (tds.hasText()){
                    Elements table2 = doc.select("div.defaultDialog h2");
                    for (Element tr1 : table2) {
                        Elements tds1 = tr1.getElementsMatchingText("\\d{4}?");
                        if (!xxx.equals(tds1.text())&&(tds1.hasText())){
                            tabletext += tds1.text() + "\n";
                            xxx = tds1.text();
                            //System.out.println(tabletext);
                        }

                    }
                    tabletext+=tds.text()+"\n";
                }

            }

        }

    }

    public String getPlayerList() {

        return tabletext;
    }

}
