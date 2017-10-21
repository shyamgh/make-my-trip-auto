package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import logger.CustomLogger;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.BusSearchResultPage;
import pages.BusTicketBookingPage;

import reporter.CustomReporter;

import com.logger.MyLogger;
import com.thoughtworks.selenium.Wait.WaitTimedOutException;

import utils.EnvironmentVariables;
import utils.GenericFunctions;
import utils.RemoteCommandExec;
import utils.SystemWaits;

public class RawTestClass {

	private GenericFunctions genFunct;
	WebDriver driver;
	private ITestContext context;
	private static final Logger LOG = MyLogger.getLogger();
	private final static Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();
	public static final Map activeDrivers = new HashMap<Object, String>();
	public static final Map openedBrowsers = new HashMap<Object, String>();

	
	@BeforeClass
	public void setUP(ITestContext context)	{
		CustomLogger.logMessage(null,"INIT","Initializing Selenium Firefox driver...",1);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(SystemWaits.PAGE_WAIT, TimeUnit.SECONDS);
		this.context = context;		
		genFunct = new GenericFunctions(driver, this.context);
		CustomLogger.logMessage(null,"","Driver initiated",1);
		EnvironmentVariables.getEnvironmentVars().put("driver", driver);
	}
	@AfterClass
	public void tearDown()
	{
		CustomLogger.logMessage(null,"","Closing Driver instance",1);
		genFunct.CloseBrowser();
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		/*String cmd = "ls -ltr";
		try {
			ArrayList output = new RemoteCommandExec().ExecuteCmdOnThisHost("localhost", "user", "pwd", cmd);
			Iterator<String> itr = output.iterator();
		    while(itr.hasNext()){
		        System.out.println(itr.next());
		    }
		    String srchString = "exiteed";
		    Assert.assertTrue(GenericFunctions.searchThisArrayList(output, srchString), "Verify remote command output contains '"+srchString+"'");    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//System.out.println(EnvironmentVariables.configData.get("serverip1"));
		
/*		WebDriver wd = new FirefoxDriver();
		String date = "18";
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//wd.manage().window().maximize();
		GenericFunctions genf = new GenericFunctions(wd);
		genf.OpenThisUrl("http://www.makemytrip.com/bus-tickets/");
		new BusTicketBookingPage(wd).SelectDateOfJourney(false, date);
		WebElement wb = genf.waitForElement(By.xpath("//a[@class='date_field_tab flL make_relative right']"));
		wb.click();
		wb=null;
		wb = genf.waitForElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//a[.='"+date+"']"));
        wb.click();
		
        
		wd.close();
		wd.quit();
*/
		/*WebDriver wd = new FirefoxDriver();
		wd.get("http://bustickets.makemytrip.com/BusIntegration/buslisting.htm?userEmail=&userPnrNo=&isDirect=Y&session_cId=139565727182856387&departure=MMTCC1744%2CPune&arrival=MMTCC1619%2CNagpur&departureDate=24%2F04%2F2014&track=chn-17-11&channel=B2C_LST_MOD");
		//Thread.sleep(4000);
		new GenericFunctions(wd).WaitTillPageLoad("Online");
		//m2(wd, By.xpath("//div[@class='listing_section shadow_genrator_1 clearFix append_bottomHalf ng-scope']//p[@class='traveler_name fontsize14 ng-binding']"));
		//m3(wd, By.xpath("//p[@class='traveler_name fontsize14 ng-binding']"));
		new BusSearchResultPage(wd).selectThisBus(wd, "Rajhans Travels");
		Thread.sleep(8000);
		wd.quit();*/
		
		
		
		
		/*WebDriver d1 = new FirefoxDriver();
		open(d1, "http://www.google.com");	//### Open google.com
		GenericFunctions.WaitTillPageLoad(d1, "Google");	//### wait for page load
	
		WebDriver d2 = new FirefoxDriver();
		open(d2, "http://www.gmail.com");		//### Open gmail.com
		GenericFunctions.WaitTillPageLoad(d1, "Gmail");	//### wait for page to load
		
		//### print all open window handles and currently opened site page titles 
		Collection c = openedBrowsers.values();
		Iterator i = c.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		System.out.println(openedBrowsers.get(d1.getWindowHandle()));	//### print window handle and site page title
		System.out.println(openedBrowsers.get(d2.getWindowHandle()));	//### print window handle and site page title
		
		
		open(d1, "https://github.com/");		//### open github.com in first browser
		GenericFunctions.WaitTillPageLoad(d1, "GitHub");	//### wait for page to load
		System.out.println(openedBrowsers.get(d1.getWindowHandle()));	//### print window handle and new site page title
		
		close(d1);
		close(d2);*/
		
		
		WebDriver d1 = new FirefoxDriver();
		open(d1, "https://intranet.gslab.com");
		System.out.println("----------");
		for(String winHandle : d1.getWindowHandles()){
		    d1.switchTo().window(winHandle);
		    System.out.println(d1.getWindowHandle());
		}
		GenericFunctions.WaitTillPageLoad(d1, "Home");	//### wait for page load
		d1.findElement(By.xpath("//a[contains(@title,'kPoint')]")).click();
		GenericFunctions.WaitTillPageLoad(d1, "kPoint");	//### wait for page load
		System.out.println("----------");
		for(String winHandle : d1.getWindowHandles()){
		    d1.switchTo().window(winHandle);
		    System.out.println(d1.getWindowHandle());
		}
		close(d1);
	}
	
	
	public static WebDriver open(WebDriver wd, String url) {
		wd.get(url);
		openedBrowsers.put(wd.getWindowHandle(), wd.getTitle());
		return wd;
	}
	
	public static void update(WebDriver wd) {
		for(String winHandle : wd.getWindowHandles()){
			try {
				System.out.println("Adding into open browsers map "+winHandle);
				openedBrowsers.put(winHandle, wd.getTitle());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(WebDriver wd) {
		for(String winHandle : wd.getWindowHandles()){
			System.out.println("Removing "+winHandle);
			openedBrowsers.remove(winHandle);
		}
		wd.quit();
	}
	
	public static void getHandleToWindow(WebDriver wd) {
		
	}
	
	private static void m2(WebDriver wd, By by) {
		
		List ls = wd.findElements(by);
		Iterator i = ls.iterator();
		while(i.hasNext()) {
			WebElement wb = (WebElement) i.next();
			System.out.println(wb.getText());
		}
		
	}
	
	
	
	/*private static void m3(WebDriver wd, By by) {
		WebElement wb = getBusDiv(wd, "Rajhans Travels");
		System.out.println(wb.getText());
		WebElement wb1 = wb.findElement(by);
		System.out.println(wb1.getText());
		System.out.println(wb1.getTagName());
		System.out.println(wb1.toString());
	}*/
	
	
	/*private static WebElement getBusDiv(WebDriver wd, String busName) {
		WebElement wb_ret = null;
		List ls = wd.findElements(By.xpath("//div[@class='listing_section shadow_genrator_1 clearFix append_bottomHalf ng-scope']"));
		for(int i=0;i<ls.size();i++) {
			WebElement wb = (WebElement) ls.get(i);
			if(wb.getText().trim().contains(busName)) {
				//System.out.println(wb.getText());
				wb_ret = wb;
				break;
			}
		}
		return wb_ret;
	}*/
	
	
	private static void m1()
	{
		logMethodStart();
	}
	
	public static void logMethodStart() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		
		StackTraceElement e1 = stacktrace[1];
		String methodName = e1.getMethodName();
		String logMsg ="*** Entering into "+methodName+"..."; 
		System.out.println(logMsg);
		
		StackTraceElement e2 = stacktrace[2];
		methodName = e2.getMethodName();
		logMsg ="*** Entering into "+methodName+"..."; 
		System.out.println(logMsg);
		
		StackTraceElement e3 = stacktrace[3];
		methodName = e3.getMethodName();
		logMsg ="*** Entering into "+methodName+"..."; 
		System.out.println(logMsg);
	}

	@Test
	public void test1()
	{
		/*CustomLogger.setStep_Msg("Step 1", "Login to application");
		CustomLogger.logMessage(null, 1);
		printStep_Msg();
		CustomLogger.setStep_Msg("Step 2", "Open DM page");
		CustomLogger.logMessage(Level.SEVERE, 1);
		printStep_Msg();
		CustomLogger.removeStep_Msg();
		printStep_Msg();*/
		
		
		String date = "18";
		driver.manage().window().maximize();
		genFunct.OpenThisUrl("http://www.makemytrip.com");
		String verifyText = "MakeMyTrip";
		genFunct.WaitTillPageLoad(verifyText);
		BusTicketBookingPage btp = new BusTicketBookingPage(driver);
		btp.ClickThisWidgetTab("Bus");
		btp.SelectDateOfJourney(false, date);
	}

	
	@Test
	public void RegisterDeviceTest()
	{
		String msg = "";
		String step ="Start -- test1";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		
		step = "Step 1 : Log in to the DCC UI";
		msg = "Log in to the DCC UI with valid credentials";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		String url = genFunct.GetSystemParameter("selenium.url");
		genFunct.OpenThisUrl(url);
		String verifyText = "MakeMyTrip";
		genFunct.WaitTillPageLoad(verifyText);

		step = "Step 2: Click tab";
		msg = "Click the Register tab.";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		step = "";
		msg = "Verify Device Manager screen is displayed.";
		CustomLogger.logMessage(Level.INFO,step,msg,1);

		step = "Step 3: Enter a new Device ID";
		msg = "Enter a new Device ID in the Select Device text box";
		CustomLogger.logMessage(Level.INFO,step,msg,1);

		step = "Step 4: Click tab";
		msg = "Click the Register tab.";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		step = "";
		msg = "Verify Device Registration screen is displayed";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		step = ""; 
		msg = "Verify Device ID is automatically populated in the Device Registration screen";
		CustomLogger.logMessage(Level.INFO,step,msg,1);

		step = "Step 5: Select the area";
		msg = "Select the area to be assigned to the device from the Area drop-down list";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		step = "";
		msg = "Verify area is present";
		CustomLogger.logMessage(Level.INFO,step,msg,1);

		step = "Step 6: Select the group";
		msg = "Select the group name from the RF Profile drop-down list if required";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		step = "";
		msg = "Verify group name is present";
		CustomLogger.logMessage(Level.INFO,step,msg,1);

		step = "Step 7: Click Register";
		msg = "";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		step = "";
		msg = "Verify the successful message is displayed for valid device";
		CustomLogger.logMessage(Level.INFO,step,msg,1);
		
		CustomLogger.logMessage(Level.INFO,"End -- test1","",1);
	}
	
	
	
	/*public static void setStep_Msg(String step, String msg) {
		Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();
		ENVIRONMENT_VARS.put("step", step);
		ENVIRONMENT_VARS.put("msg", msg);
	}
	public static void removeStep_Msg() {
		Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();
		ENVIRONMENT_VARS.remove("step");
		ENVIRONMENT_VARS.remove("msg");
	}*/
	public void printStep_Msg()
	{
		Map ENVIRONMENT_VARS = EnvironmentVariables.getEnvironmentVars();
		System.out.println(ENVIRONMENT_VARS.get("step") +" => "+ ENVIRONMENT_VARS.get("msg"));
	}

	

	
}
