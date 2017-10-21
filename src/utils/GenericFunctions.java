package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import logger.CustomLogger;
import org.apache.commons.io.FileUtils;
import org.apache.xpath.functions.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.testng.asserts.LoggingAssert;
import org.testng.internal.annotations.ITest;
import com.logger.MyLogger;
import java.io.InputStream;


public class GenericFunctions {
	private final WebDriver driver;
	private ITestContext context;
	private SoftAssert softassert;
	private LoggingAssert lassert;
	
	public GenericFunctions() {
		this.driver = (WebDriver)EnvironmentVariables.getEnvironmentVars().get("driver");
		softassert = new SoftAssert();
	}
	public GenericFunctions(WebDriver driver){
		this.driver = driver;
		softassert = new SoftAssert();
	}
	public GenericFunctions(WebDriver driver, ITestContext context){
		this.driver = driver;
		this.context = context;
		softassert = new SoftAssert();
		lassert = new LoggingAssert();
	}
	
	/**
	 * 
	 * This will open the given URL
	 * 
	 * @param url
	 * @author shyam
	 * 
	 */
	public void OpenThisUrl(String url)
	{
		//Reporter.log("Opening url "+url);
		CustomLogger.logMessage(null,"","Opening url "+url,1);
		driver.get(url);
	}
	public void OpenThisUrl(WebDriver wd, String url)
	{
		//Reporter.log("Opening url "+url);
		CustomLogger.logMessage(null,"","Opening url "+url,1);
		wd.get(url);
		WaitTillPageLoad(wd, "BAC");
	}
	
	/**
	 * This function closes and quits the driver
	 * @author Shyam
	 */
	public void CloseBrowser() {
		//Reporter.log("Closing the browser and driver instance");
		CustomLogger.logMessage(null,"","Closing the browser and driver instance.",1);
		driver.close();
		driver.quit();
		CustomLogger.logMessage(null,"","Closed.",1);
	}
	
	/**
	 * This function waits for page to load by verifying the given text in page title
	 * @param vText:text to be verify for ensure correct page opened
	 * @author shyam
	 * 
	 */	
	public void WaitTillPageLoad(String vText)
	{	
		final String verifyText = vText; 
		//Reporter.log("Wait till page to load");
		CustomLogger.logMessage(null,"Wait till page load","Wait till page to load",1);
		try {
	       (new WebDriverWait(driver, SystemWaits.STANDARD_WAIT)).until(new ExpectedCondition<Boolean>() {
	           public Boolean apply(WebDriver d) {
	        	   return d.getTitle().startsWith(verifyText);
	          }
	       });
	       //Reporter.log("Text '"+verifyText+"' found in page title. Page loaded.");
	       CustomLogger.logMessage(null,"Wait till page load","Text '"+verifyText+"' found in page title. Page loaded.",1);
		} catch (Exception e) {
			//Reporter.log("FAIL: Wait for page to load, Exception while waiting for page to laod, Searched page title '"+verifyText+"'. "+e.getMessage());\
			CustomLogger.logMessage(Level.SEVERE,"Wait till page load","Exception while waiting for page to laod, Searched page title '"+verifyText+"'. "+e.getMessage(),1);			
		}
	}

	/**
	 * This function waits for page to load by verifying the given text in page title
	 * @param vText:text to be verify for ensure correct page opened
	 * @author shyam
	 * 
	 */	
	public static void WaitTillPageLoad(WebDriver driver, String vText)
	{	
		final String verifyText = vText; 
		//Reporter.log("Wait till page to load");
		CustomLogger.logMessage(null,"Wait till page load","Wait till page to load",1);
		try {
	       (new WebDriverWait(driver, SystemWaits.STANDARD_WAIT)).until(new ExpectedCondition<Boolean>() {
	           public Boolean apply(WebDriver d) {
	        	   return d.getTitle().startsWith(verifyText);
	          }
	       });
	       //Reporter.log("Text '"+verifyText+"' found in page title. Page loaded.");
	       CustomLogger.logMessage(null,"Wait till page load","Text '"+verifyText+"' found in page title. Page loaded.",1);
		} catch (Exception e) {
			//Reporter.log("FAIL: Wait for page to load, Exception while waiting for page to laod, Searched page title '"+verifyText+"'. "+e.getMessage());\
			CustomLogger.logMessage(Level.SEVERE,"Wait till page load","Exception while waiting for page to laod, Searched page title '"+verifyText+"'. "+e.getMessage(),1);			
		}
	}
	
	/**
	 * This function reads testng.xml parameter with given name
	 * @param paramName
	 * @author shyam
	 * 
	 */	
	public String GetSystemParameter(String paramName)

	{
		return context.getCurrentXmlTest().getParameter(paramName);
	}
	
	public boolean WaitForObject(WebElement webEle) throws WebElementNotFound
	/**
	 * This function waits for the given object to appear on UI
	 * @param webEle
	 * @author shyam
	 * @return boolean
	 * 
	 */
	{
		boolean found = false;
		for(int i=0;i<SystemWaits.STANDARD_WAIT;i++)
		{
			if (webEle.isDisplayed()) {
				//Reporter.log("Web element "+webEle.toString()+ " displayed");
				CustomLogger.logMessage(null,"Verify object displayed","Web element "+webEle.toString()+ " displayed",1);
				break;				
			}
		}
		if (!found) {
			CustomLogger.logMessage(Level.WARNING,"Verify object displayed","Web element "+webEle.toString()+ " not displayed",1);
			throw new WebElementNotFound("Web element "+webEle.toString()+ " displayed");
		} else
			return true;
	}
	
	/**
	 * This function waits for the given object to appear on UI
	 * @param webEle, timeout
	 * @return boolean
	 * @author shyam
	 * 
	 */
	public boolean WaitForObject(WebElement webEle, int timeout) throws WebElementNotFound

	{
		boolean found = false;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for(int i=0;i<timeout;i++)
		{
			try {
				if (webEle.isDisplayed()) {
					//Reporter.log("Web element "+webEle.toString()+ " is displayed");
					CustomLogger.logMessage(null,"Verify object displayed","Web element "+webEle.toString()+ " displayed",1);
					found = true;
					break;				
				}
			} catch (NoSuchElementException ne) {
				//
			}
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!found) {
			CustomLogger.logMessage(Level.WARNING,"Verify object displayed","Web element "+webEle.toString()+ " not displayed",1);
			throw new WebElementNotFound("Web element not displayed");
		} else
			return true;
	}
	
	/**
	 * This function waits for the given error object to appear on UI, thows exception if error appears
	 * @param webErr, timeout
	 * @return boolean
	 * @author shyam
	 * 
	 */
	public boolean WaitForErrorObject(String erID, int timeout)
	{
		WebElement webErr = driver.findElement(By.id(erID));
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		System.out.println(webErr.isDisplayed());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		softassert.assertEquals(!(webErr.isDisplayed()), true, "Verify if errro not displayed");
		//lassert.assertEquals(!(webErr.isDisplayed()), true, "Verify if errro not displayed");
		//assert !(webErr.isDisplayed()) : "Verify if errror not displayed";
		if (webErr.isDisplayed())
			assert false : "Verify if errror not displayed \n";
		else
			Reporter.log("Login successful \n");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return false;
	}
	
	/**
	 * This function will log the error message in the test report, it will be exception
	 * @param errorMsg
	 * @author shyam
	 * @return none 
	 */
	public static void LogError(String errorMsg)

	{
		Throwable t = new Throwable(new Exception(errorMsg));
		Reporter.getCurrentTestResult().setThrowable(t);
	}

	/**
	 * This function will log the given exception in the test report
	 * @param except
	 * @author shyam
	 * @return none 
	 */
	public static void LogError(Exception except)
	{
		Throwable t = new Throwable(except);
		Reporter.getCurrentTestResult().setThrowable(t);
	}

	/**
	 * This method is used to search string in given array list 
	 * @param al
	 * @param srchString
	 * @return boolean : true if found
	 */
	public static boolean searchThisArrayList(ArrayList al, String srchString)
	{
		boolean found = false;
		Iterator<String> itr = al.iterator();
        while(itr.hasNext()){
        	String s = itr.next();
        	if (s.contains(srchString)) {
        		//Reporter.log("Given search string "+srchString+" found in line "+s);
        		CustomLogger.logMessage(null, "Search message in array list", "Given search string ["+srchString+"] found in line "+s, 1);
        		found = true;
        		break;
        	}
        }
        if (!found)
        	CustomLogger.logMessage(Level.WARNING, "Search message in array list", "Given search string ["+srchString+"] not found", 1);
		return found;
	}
	
/*	public void FindElement(WebDriver driver, By by, int timeout)
	{
		 // Tell webdriver to wait
		WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(4));
		 
		// Test the autocomplete response - Explicit Wait
		IWebElement autocomplete = wait.Until(x => x.FindElement(By.ClassName("ac-row-110457")));
		 
	}*/
	
	/**
	 * This function is used to verify if the web element with given By object is present
	 * @author shyam
	 * @param by
	 * @return boolean : true if the element is present 
	 */
	public boolean verifyElementPresent(By by)
	{
		CustomLogger.logMethodStart();

		boolean found = false;
		driver.manage().timeouts().implicitlyWait(SystemWaits.NORMAL_WAIT, TimeUnit.SECONDS);
		if (driver.findElements(by).size() != 0) {
			found = true;
        } else {
        	CustomLogger.logMessage(Level.WARNING, "Verify element present", "element ["+by.toString()+"] not found.",1);
        	System.out.println("verifyElementPresent failed, element ["+by.toString()+"] not found.");
        }
		driver.manage().timeouts().implicitlyWait(SystemWaits.PAGE_WAIT, TimeUnit.SECONDS);
		CustomLogger.logMethodEnd();
		return found;
	}
	
	/**
	 * This function return the properties object loaded using given input file
	 * @author shyam
	 * @param prop
	 * @return Properties
	 */
	public static Properties loadPropertiesFromThisFile(String prop)
	{
		Properties props = new Properties();
		InputStream in = null;
    	try {
    	    in = new FileInputStream(prop);//getClass().getResourceAsStream(prop);
    	    props.load(in);
    	} catch (IOException e) {
    		//Reporter.log("Error while loading properties file");
    		//Reporter.log(e.getMessage());
    		CustomLogger.logMessage(Level.SEVERE, "Load properties file", "Error while loading properties file", 1);
    	} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return props;
	}

	/**
	 * This method can be used to capture screen shot during error or some important step
	 * @author shyam
	 * @param stepName
	 * @return path of image
	 */
	public String takeScreenShot(String stepName)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date = new Date();
		stepName = removeSpecialChalrs(stepName);
		String file = "snapshot\\"+stepName+"_"+dateFormat.format(date)+".png";
		CustomLogger.logMessage(null, "", "Takeing snapshot ["+file+"]", 3);
		File screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(EnvironmentVariables.PROJECT_PATH+"\\"+file));
		} catch (IOException e) {
		    e.printStackTrace();
		    CustomLogger.logMessage(Level.SEVERE, "", "Exception while taking snapshot", 3);
		}
		return EnvironmentVariables.PROJECT_PATH+"\\"+file;
	}

	/**
	 * This function repeatedly searches for the element till timeout after given polling interval.
	 * @author shyam
	 * @param by
	 * @return WebElement (Throws exception if the element not found within given time.)
	 */
	public WebElement waitForElement(final By by) {
		/*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(SystemWaits.WEBELEMENT_WAIT, TimeUnit.SECONDS)
	            .pollingEvery(SystemWaits.WEBELEMENT_POLLING_WAIT, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	   WebElement element = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });*/
		
		WebDriverWait wait = new WebDriverWait(driver, SystemWaits.WEBELEMENT_WAIT);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}
	
	/**
	 * This method remove special chars from string
	 * @author shyam
	 * @param str
	 * @return original String after removing special chars
	 */
	public String removeSpecialChalrs(String str) {
		CustomLogger.logMessage(null, "Remove special characters", "Removing special chars from string ["+str+"].", 3);
		try
		{
			str = str.replaceAll("[^\\w\\s-]", "");
		} catch (Exception e) {
			CustomLogger.logMessage(Level.WARNING, "Remove special characters", "Exception while removing special chars from string ["+str+"]. "+e.getMessage(), 3);
		}
		return str;
	}
	
	/**
	 * This method is used to select given value from dropdown
	 * @param by
	 * @param value
	 * @return
	 */
	public boolean selectValue(By by, String value) {
		CustomLogger.logMethodStart();
		boolean found = false;
		WebElement select = waitForElement(by);
		List<WebElement> allOptions = select.findElements(By.tagName("option"));
		for (WebElement option : allOptions) {
		    if (option.getAttribute("value").contains(value)) {
		    	option.click();
		    	found = true;
		    	break;
		    }
		}
		if (!found)
			CustomLogger.logMessage(Level.WARNING, "Select value from dropdown", "Could not find value ["+value+"]", 3);
		else
			CustomLogger.logMessage(null, "Select value from dropdown", "Value ["+value+"] selected", 3);
		CustomLogger.logMethodEnd();
		return found;
	}


	/**
	* Pause for X milliseconds.
	* @param iTimeInMillis
	*/
	public static void pause(final int iTimeInMillis)
	{
		try {
			Thread.sleep(iTimeInMillis);
		} catch(InterruptedException ex) {
			CustomLogger.logMessage(Level.SEVERE, "Sleep thread", ex.getMessage(), 1);
		}
	}
}