package com.rojak;

import java.io.*;
import java.util.Properties;

public class propertiesFiles {
    private Properties prop = new Properties();

    public void WritePropertiesFile(){
        try (OutputStream output = new FileOutputStream("config.properties")) {
            // set the properties value
            prop.setProperty("path", "localhost");
            prop.setProperty("textFile", "mkyong");
            prop.setProperty("state", "password");
            prop.setProperty("top", "mkyong");
            prop.setProperty("player", "password");
            // save properties to project root folder
            prop.storeToXML(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    public void ReadPropertiesFile (){
        try (InputStream input = new FileInputStream("config.properties")) {
            // load a properties file
            prop.loadFromXML(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("path"));
            System.out.println(prop.getProperty("textFile"));
            System.out.println(prop.getProperty("state"));
            System.out.println(prop.getProperty("top"));
            System.out.println(prop.getProperty("player"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}