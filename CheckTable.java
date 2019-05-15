package com.Stiw3054.groupProject.groupProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.ArrayList;
public class CheckTable {
    List <String> ValidURLList;
    Document doc;
    String tabletext = "";
    String xxx= "";
    List<List> Alldata = new ArrayList();

    public CheckTable(List <String> ValidURLList){

        this.ValidURLList =ValidURLList;

    }

    public void Checktable () throws IOException {

        for (String uri : ValidURLList) {
            String Category = "";
            try{
                doc = Jsoup.connect(uri).get();
                Pattern p=Pattern.compile("(?<=\\()[^\\)]+");
                Matcher m = p.matcher(doc.select("div.defaultDialog h2").first().text().trim());
                while(m.find())
                    Category = m.group();
                Elements table = doc.select("table.CRs1 tr");
                if(table.hasText()){
                    table.remove(0);
                    for (Element tr : table) {
                        List<String> Rowdata = new ArrayList();
                        Elements tds = tr.select("td");
                        for(Element td:tds){
                            Rowdata.add(td.text().trim().replace(",", "."));
                        }
                        Rowdata.add(Category);
                        Alldata.add(Rowdata);
                    }
                }
            } catch(IllegalStateException | NullPointerException e){}
        }
    }

    public List getPlayerList() {
        return Alldata;
    }

}