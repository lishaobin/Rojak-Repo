package com.Stiw3054.groupProject;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class PropertiesFile {

    private Properties prop = new Properties();

    void WritePropertiesFile(String path, String fileName) {
        try (OutputStream output = new FileOutputStream("config.properties")) {
            // set the properties value
            System.out.println("Writing to the properties file ......");
            prop.setProperty("path", path);
            prop.setProperty("textFile", fileName);
//            prop.setProperty("state", "password"); //Please read the details from the url links, try to use Jsoup
//            prop.setProperty("top", "mkyong");
//            prop.setProperty("player", "password");
            // save properties to project root folder
            prop.store(output, null);

            System.out.println("Successfully saved to properties file.");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    void ReadPropertiesFile() {
        try (InputStream input = new FileInputStream("config.properties")) {
            // load a properties file
            prop.load(input);
            System.out.println("\nReading from the properties file .....");
            // get the property value and print it out
            System.out.println("Path      -> " + prop.getProperty("path"));
            System.out.println("File Name -> " + prop.getProperty("textFile"));
//            System.out.println(prop.getProperty("state"));
//            System.out.println(prop.getProperty("top"));
//            System.out.println(prop.getProperty("player"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
