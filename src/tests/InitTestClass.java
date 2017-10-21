package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.CustomLogger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.logger.MyLogger;

import utils.EnvironmentVariables;
import utils.GenericFunctions;
import utils.MyEventListener;
import utils.WebDriverInitializer;

public class InitTestClass {

	protected GenericFunctions genFunct;
	protected WebDriver driver;
	protected ITestContext context;
	protected static final Logger LOG = MyLogger.getLogger();
	//protected WebDriverEventListener eventListener;
	protected final static Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();

	
	/*protected EventFiringWebDriver getDriverWithEventlistener() {
		eventListener = new MyEventListener();
		return new EventFiringWebDriver(new FirefoxDriver()).register(eventListener);
	}*/
	
	@BeforeClass
	public void setUP(ITestContext context)	{
        CustomLogger.logMessage(null,"INIT","Initializing Selenium Firefox driver...",1);
		//driver = new FirefoxDriver();
		//driver = getDriverWithEventlistener();
        driver = WebDriverInitializer.getDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		this.context = context;		
		genFunct = new GenericFunctions(driver, this.context);
		CustomLogger.logMessage(null,"","Driver initiated",1);
		EnvironmentVariables.getEnvironmentVars().put("driver", driver);
		CustomLogger.logMessage(null,"","Stored driver into global Map - ENVIRONMENT_VARS",1);
	}
	
	@AfterClass
	public void tearDown()
	{
		genFunct.CloseBrowser();
	}
	
	@AfterMethod(alwaysRun=true)
	public void catchExceptions(ITestResult result){
		String methodName = result.getMethod().getMethodName();
		if((!result.isSuccess()) && (!methodName.equals("removeSpecialChalrs"))){
	    	//String snap = new GenericFunctions().takeScreenShot(result.getName());
	    	//String msg = "<br/> <img src=..\\"+snap+"/> <br/>";
	    	//String msg = "<br/> <A href='..\\"+snap+"'>Link</A> <br/>";
	    	//String msg = "<br /><img src=\"file:///./"+snap+"\" alt=\"errorPic\" height=\"50\" width=\"200\"/><br />";
			String fileName = result.getThrowable().getMessage().substring(0, 35);
	    	String snap = new GenericFunctions().takeScreenShot(fileName);
	    	String msg = "<br /><a href=\""+snap+"\">"
	    			+"<img src=\""+snap+"\" alt=\"errorPic\" height=\"50\" width=\"200\"/>"
	    			+"</a><br />";
	    	Reporter.setCurrentTestResult(result); 
	    	Reporter.setEscapeHtml(false);
	    	Reporter.log(msg);
	    	MyLogger.getLogger().log(Level.SEVERE, "ERROR -> "+result.getThrowable().getMessage()+" "+msg);
	    	Reporter.setCurrentTestResult(null);
	    	//CustomLogger.logMessage(Level.SEVERE, "Exception "+result.getThrowable().getMessage(), msg, 3);	    	
	    }
	}
}