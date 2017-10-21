package tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logger.CustomLogger;

import org.testng.annotations.Test;

import com.logger.MyLogger;


public class UseLogger {
	  // use the classname for the logger, this way you can refactor
	  //private final static Logger LOGGER = Logger.getLogger(UseLogger.class.getName());
	  private final static Logger LOGGER = MyLogger.getLogger();
	
	  public void doSomeThingAndLog() {
	    // ... more code
	    // now we demo the logging
	    // set the LogLevel to Severe, only severe Messages will be written
	    LOGGER.setLevel(Level.ALL);
	    //LOGGER.entering(this.getClass().getName(), "doSomeThingAndLog");
	    CustomLogger.logMethodStart();
	    CustomLogger.logMessage(null, "STEP 1","Info Log", 1);
	    CustomLogger.logMessage(Level.WARNING,"STEP 2", "Info Log", 1);
	    CustomLogger.logMessage(Level.FINEST,"STEP 3", "Really not important", 1);
	    CustomLogger.logMessage(Level.SEVERE,"STEP 4", "Info Log", 1);
	    //LOGGER.exiting(this.getClass().getName(), "doSomeThingAndLog");
	    CustomLogger.logMethodEnd();	    
	  }
	
	
	  @Test
	  public void test1() {
	  //public static void main(String[] args) 
	    UseLogger tester = new UseLogger();
	    /*try {
	      MyLogger.setup();
	    } catch (IOException e) {
	      e.printStackTrace();
	      throw new RuntimeException("Problems with creating the log files");
	    }*/
	    CustomLogger.logMessage(null,"Start -- test1","",1);
	    tester.doSomeThingAndLog();
		CustomLogger.logMessage(null,"End -- test1","",1);
	  }

}
 