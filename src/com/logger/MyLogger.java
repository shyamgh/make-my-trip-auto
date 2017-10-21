package com.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import utils.EnvironmentVariables;


public class MyLogger{
	private static final String LOGFILE_TXT = EnvironmentVariables.PROJECT_PATH+"\\test-output\\Logging.txt";
	private static final String LOGFILE_HTML = EnvironmentVariables.PROJECT_PATH+"\\test-output\\Logging.html";
	static private final FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;
	static private FileHandler fileHTML;
	static private Formatter formatterHTML;
	private static final Logger LOGGER;
	
	public static Logger getLogger() {
		return LOGGER;
	}
  
	public static String getLogfileTxt() {
		return LOGFILE_TXT;
	}
	
	public static String getLogfileHtml() {
		return LOGFILE_HTML;
	}

	/*static {
		LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		try {
		    MyLogger.setup();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problems with creating the log files");
		}
	}*/
	  
	//static public void setup() throws IOException {
	static {
		LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		try {
			// Get the global LOGGER to configure it
			LOGGER.setLevel(Level.ALL);
			fileTxt = new FileHandler(LOGFILE_TXT);
			fileHTML = new FileHandler(LOGFILE_HTML);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problems with creating the log files");
		}
		// create txt Formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		LOGGER.addHandler(fileTxt);
		
		// create HTML Formatter
		formatterHTML = new MyHtmlFormatter();
		fileHTML.setFormatter(formatterHTML);
		LOGGER.addHandler(fileHTML);
	}

}
 