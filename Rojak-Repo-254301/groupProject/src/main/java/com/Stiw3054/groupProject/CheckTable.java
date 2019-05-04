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

                        if (!xxx.equals(table2.text())){
                            tabletext += table2.text()+"\n";
                            xxx = table2.text();
                            //System.out.println(tabletext);
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
