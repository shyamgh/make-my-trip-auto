package logger;

import java.util.Map;
import java.util.logging.Level;
import org.testng.Reporter;
import com.logger.MyLogger;
import utils.EnvironmentVariables;

public class CustomLogger {
	private static final java.util.logging.Logger LOG = MyLogger.getLogger();
	/**
	 * This method is called by other methods to log end of the method into log file 
	 */
	public static void logMethodStart() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String methodName = e.getMethodName();
		String logMsg ="*** Entering into "+methodName+"..."; 
		//Reporter.log(logMsg);
		LOG.entering(e.getClassName(), e.getMethodName());
		System.out.println(logMsg);
	}
	
	/**
	 * This method is called by other methods to log start of the method into log file 
	 */
	public static void logMethodEnd() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String methodName = e.getMethodName();
		String logMsg ="*** Exiting from "+methodName+"...";
		//Reporter.log(logMsg);
		LOG.exiting(e.getClassName(), e.getMethodName());
		System.out.println(logMsg);
	}
	
	/**
	 * This method is used to log + report the given step and messages.
	 * @author shyam
	 * @param LOG (logger initialized by test class)
	 * @param step (test step name)
	 * @param msg (step message)
	 * @param reportlevel (1: log and report, 2:only report, 3:only log)
	 */
	public static void logMessage(Level logLevel, String step, String msg, int reportlevel) {
		String msg1 = step+" :: "+ msg;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String sourceClass = e.getClassName();
		String sourceMethod = e.getMethodName();
		if (logLevel == null)
			logLevel = Level.INFO;
		switch (reportlevel) {
			case 1:		//Report and Log 
		 		LOG.logp(logLevel, sourceClass, sourceMethod, msg1);
		 		Reporter.log(msg1.replace("::", "=>"));
				break;
		 	case 2:		//Olny Report 
		 		Reporter.log(msg1.replace("::", "=>"));
				break;
		 	case 3:		//Only log
		 		LOG.logp(logLevel, sourceClass, sourceMethod, msg1);
				break;
		 }
	}
	
	/**
	 * This method is used to log + report the given step and messages. This method reads step and message from shared Environment Map variable 'ENVIRONMENT_VARS'
	 * @author shyam
	 * @param LOG (logger initialized by test class)
	 * @param reportlevel (1: log and report, 2:only report, 3:only log)
	 */
	public static void logMessage(Level logLevel, int reportlevel) {
		Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();
		String msg1 = ENVIRONMENT_VARS.get("step").toString()+" :: "+ ENVIRONMENT_VARS.get("msg").toString();
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String sourceClass = e.getClassName();
		String sourceMethod = e.getMethodName();
		if (logLevel == null)
			logLevel = Level.INFO;
		switch (reportlevel) {
			case 1:		//Report and Log 
		 		LOG.logp(logLevel, sourceClass, sourceMethod, msg1);
		 		Reporter.log(msg1.replace("::", "=>"));
				break;
		 	case 2:		//Olny Report 
		 		Reporter.log(msg1.replace("::", "=>"));
				break;
		 	case 3:		//Only log
		 		LOG.logp(logLevel, sourceClass, sourceMethod, msg1);
				break;
		 }
	}
/*	public static void logMessage(String step, String msg, Level loglevel) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		msg = step + "::"+ msg;
		LOG.logp(loglevel, e.getClassName(), e.getMethodName(), msg);
		Reporter.log(msg.replace("::", " => "));
	}
*/	
	/**
	 * This method used to set step and message into shared Environment Map variable 'ENVIRONMENT_VARS', which can be used to report and log
	 * @param step
	 * @param msg
	 */
	public static void setStep_Msg(String step, String msg) {
		Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();
		ENVIRONMENT_VARS.put("step", step);
		ENVIRONMENT_VARS.put("msg", msg);
	}

	/**
	 * This method used to remove step and message previously set in shared Environment Map variable 'ENVIRONMENT_VARS'.
	 */
	public static void removeStep_Msg() {
		Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();
		ENVIRONMENT_VARS.remove("step");
		ENVIRONMENT_VARS.remove("msg");
	}

	/**
	 * This method is used to log + report the step start message.
	 * @author shyam
	 * @param step (test step name)
	 * @param msg (step message)
	 */
	public static void startStep(String step, String msg) {
		String msg1 = "Start -- "+step+" :: "+ msg;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String sourceClass = e.getClassName();
		String sourceMethod = e.getMethodName();
 		LOG.logp(null, sourceClass, sourceMethod, msg1);
 		Reporter.log(msg1.replace("::", "=>"));
	}
	/**
	 * This method is used to log + report the step end message.
	 * @author shyam
	 * @param step (test step name)
	 * @param msg (step message)
	 */
	public static void endStep(String step, String msg) {
		String msg1 = "End -- "+step+" :: "+ msg;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String sourceClass = e.getClassName();
		String sourceMethod = e.getMethodName();
 		LOG.logp(null, sourceClass, sourceMethod, msg1);
 		Reporter.log(msg1.replace("::", "=>"));
	}
}