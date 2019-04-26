package com.rojak;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class logFile {
   private Logger logger = Logger.getLogger("MyLog");

   public void testlogFile(){
      try {
         // This block configure the logger with handler and formatter
         FileHandler fh = new FileHandler("C:\\Users\\Lai Yee\\IdeaProjects\\testGroupProject\\LogFile.log");
         logger.addHandler(fh);
         SimpleFormatter formatter = new SimpleFormatter();
         fh.setFormatter(formatter);


         // the following statement is used to log any message
         logger.info( " (not exist)");

      } catch (SecurityException e) {
         e.printStackTrace();

      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}