package com.Stiw3054.groupProject;

import java.io.*;
import java.util.Properties;

class PropertiesFile {

    private Properties prop = new Properties();

    void WritePropertiesFile(String path, String fileName) {
        try (OutputStream output = new FileOutputStream("config.properties")) {
            // set the properties value
            System.out.println("Writing to the properties file ......");
            prop.setProperty("path", path);
            prop.setProperty("textFile", fileName);
            prop.setProperty("sTATE", "KEDAH");
            prop.setProperty("top 3", "3");
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
