package tests;
import utils.EnvironmentVariables;
import utils.RemoteCommandExec;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
//import org.testng.log4testng.Logger;

import static org.testng.Assert.*;

import java.util.Date;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;
import org.openqa.selenium.Keys;

import com.logger.MyLogger;

import pages.BusSearchResultPage;
import pages.BusTicketBookingPage;
import reporter.CustomReporter;
import utils.GenericFunctions;

import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import logger.CustomLogger;

public class TestClassTest extends InitTestClass{
	//Variables
	//WebDriver driver;
	
	
	private String verifyText = "";
	private BusSearchResultPage bsr;
	
	/* private ITestContext context;
	private SoftAssert softassert;
	private GenericFunctions genFunct;
	private static final Logger LOG1 = Logger.getLogger(TestClassTest.class.getName());
	private static final Logger LOG = MyLogger.getLogger();
	//private static Logger LOG;
	static private SimpleFormatter formatterTxt;
	private static FileHandler fh;*/
	
	
	public TestClassTest() {
		super();
	}
	
	/*static{
		try {
			fh = new FileHandler(EnvironmentVariables.PROJECT_PATH+"\\test-output\\testsexec.log");
			// create txt Formatter
		    formatterTxt = new SimpleFormatter();
		    fh.setFormatter(formatterTxt);
		    LOG.addHandler(fh);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}*/
	/*@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.context = context;		
		genFunct = new GenericFunctions(driver, this.context);
		Reporter.log("The Firefox driver is instantiated");
		softassert = new SoftAssert();
		//Reporter.log("From setupBeforeSuite");
	}
	@AfterSuite(alwaysRun = true)
	public void setupAfterSuite() {
		//genFunct.CloseBrowser();
		//Reporter.log("From setupAfterSuite");
	}*/

/*	@BeforeClass
	public void setUP(ITestContext context)	{
		String msg = "INIT :: Initializing Selenium Firefox driver...";
		//CustomLogger.logMessage(LOG,"INIT","Initializing Selenium Firefox driver...",null, 1);
		LOG.info(msg);
		Reporter.log(msg.replace("::", "=>"));

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		this.context = context;		
		genFunct = new GenericFunctions(driver, this.context);
		//softassert = new SoftAssert();

		msg = "Driver initiated";
		//CustomLogger.logMessage(LOG,"","Driver initiated",null, 1);
		LOG.info(msg);
		Reporter.log(msg.replace("::", "=>"));
		try {
			// create txt Formatter
		    fh = new FileHandler(EnvironmentVariables.projectPath+"\\test-output\\testsexec.log");
		    formatterTxt = new SimpleFormatter();
		    fh.setFormatter(formatterTxt);
			LOG.addHandler(fh);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new TestClassTest();
	}
	@AfterClass
	public void tearDown()
	{
		//genFunct.CloseBrowser();
	}*/


	@Test(description="To Test home page loading within page load time")
	public void LoadHomePageTest()
	{
		String url = genFunct.GetSystemParameter("makemytrip.url");
		CustomLogger.logMessage(null,"Start -- Load Home Page","", 1);		
		genFunct.OpenThisUrl(url);
		verifyText = "MakeMyTrip";
		genFunct.WaitTillPageLoad(verifyText);
		CustomLogger.logMessage(null,"End -- Load Home Page",""+url, 1);		
	}
	
	@Test(description="To Test bus search functionality")
	public void BusSearchTest()
	{
		String email = genFunct.GetSystemParameter("makemytrip.email");
		CustomLogger.logMessage(null,"Start -- Bus search","", 1);
		BusTicketBookingPage btp = new BusTicketBookingPage(driver);		
		btp.ClickThisWidgetTab("Bus");
		btp.SelectToAndFrom("Pune", "Nagpur");
		btp.SelectDateOfJourney(false, "24");
		btp.EnterEmail(email);
		btp.SubmitSearch();
		btp.HandleSpecialSearchPopup();
		CustomLogger.logMessage(null,"End -- Bus search","", 1);
	}
	
	@Test(description="To Test home page loading within page load time")
	public void SelectSeatTest()
	{
		//SelectBusName("Vrl", "seat1");
	}
	
	@Test//(expectedExceptions = ArithmeticException.class)
	public void testPrintMessage() {
		CustomLogger.logMessage(null,"Start -- Print messages","", 1);
		/*int i = 0;
        int j = 1/i;*/
        try {
			new RemoteCommandExec().ExecuteCmdOnThisHost("localhost", "user", "pwd", "ls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        LOG.info("Test testPrintMessage executed successfully");
        LOG.warning("warning");
        LOG.severe("error");
		CustomLogger.logMessage(Level.INFO, "End -- Print messages","This is test message", 1);        
	 }
	
	@Test(description="To test search bus end to end scenario")
	public void SearchBusEndToEndTest() {
		String url = genFunct.GetSystemParameter("selenium.url");
		String email = genFunct.GetSystemParameter("selenium.email");
		String step = "Load MakeMyTrip Home Page";
		CustomLogger.logMessage(null, "Start -- "+this.getClass().getName(), "", 1);
		
		
		
		//CustomLogger.startStep(step, "");	//-------------------------------------------
		genFunct.OpenThisUrl(url);
		verifyText = "MakeMyTrip";
		genFunct.WaitTillPageLoad(verifyText);
		//CustomLogger.endStep(step, "");	//-------------------------------------------
		
		step = "Bus search";		//-------------------------------------------
		//CustomLogger.startStep(step, "");
		BusTicketBookingPage btp = new BusTicketBookingPage(driver);
		CustomLogger.logMessage(null, "Go to Bus search page", "", 1);
		btp.ClickThisWidgetTab("Bus");
		CustomLogger.logMessage(null, "Enter bus search related parameters", "", 1);
		btp.SelectToAndFrom("Pune", "Nagpur");
		btp.SelectDateOfJourney(false, "24");
		btp.EnterEmail(email);
		CustomLogger.logMessage(null, "Initiate bus search", "", 1);
		btp.SubmitSearch();
		CustomLogger.logMessage(null, "Select bus search on popup window", "", 1);
		btp.HandleSpecialSearchPopup();
		//CustomLogger.endStep(step, "");	//-------------------------------------------

		
		step = "Search in progress";		//-------------------------------------------
		//CustomLogger.startStep(step, "");
		bsr = new BusSearchResultPage(driver);
		bsr.verifySearchInitiated();
		//CustomLogger.endStep(step, "");	//-------------------------------------------
		
		
		step = "Select bus";		//-------------------------------------------
		//CustomLogger.startStep(step, "");
		bsr.selectThisBus(driver, "Neeta");
		//CustomLogger.endStep(step, "");	//-------------------------------------------
		
		step = "Select seat";		//-------------------------------------------
		//CustomLogger.startStep(step, "");
		bsr.selectSeat("A2");
		//CustomLogger.endStep(step, "");	//-------------------------------------------
		
		step = "Select boarding point";		//-------------------------------------------
		//CustomLogger.startStep(step, "");
		bsr.selectBP("Viman");
		//CustomLogger.endStep(step, "");	//-------------------------------------------
		
		step = "Verify traveler details page opened";		//-------------------------------------------
		//CustomLogger.startStep(step, "");
		bsr.verifyPageHeadingDisplayed();
		//CustomLogger.endStep(step, "");	//-------------------------------------------
		
		CustomLogger.logMessage(null, "End -- "+this.getClass().getName(), "", 1);
	}
	
	//-----------------------------------------------------------
	
/*	
	public void ClickThisPageLink(String tabName)
	{
		driver.findElement(By.linkText(tabName)).click();
	}
	
	public void ClickThisWidgetTab(String tabName)
	{
		driver.findElement(By.xpath("//div[@class='widget_tabs_info']//span[.='"+tabName+"']")).click();
       // driver.findElement(By.cssSelector("#trip_type > label")).click();
       // driver.findElement(By.id("trip_type_oneway")).click();
	}
	 
	public void SelectToAndFrom(String departureCity, String destinationCity)
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement w1 = driver.findElement(By.xpath("//div[@class='carousel-li']/div[2]/div/div[1]/div[2]/span/input"));
		Assert.assertTrue(w1.isDisplayed(), "Verify departure city text box is displayed");
		w1.sendKeys(departureCity);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w1.sendKeys(Keys.ARROW_DOWN);
		w1.sendKeys(Keys.ENTER);
		
		
		WebElement w2 =driver.findElement(By.xpath("//div[@class='carousel-li']/div[2]/div/div[1]/div[3]/span/input"));
		Assert.assertTrue(w2.isDisplayed(), "Verify departure city text box is displayed");
		w2.sendKeys(destinationCity);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w2.sendKeys(Keys.ARROW_DOWN);
		w2.sendKeys(Keys.ENTER);
		
		        
		driver.findElement(By.xpath("//div[@class='carousel-li']/div[2]/div/div[1]/div[2]/span/input")).sendKeys("Pune, India");
        driver.findElement(By.id("ui-id-172")).click();
        
        driver.findElement(By.xpath("//div[@class='carousel-li']/div[2]/div/div[1]/div[3]/span/input")).sendKeys("Nagpur, India");
        driver.findElement(By.id("ui-id-212")).click();
	}
		
	public void SelectDateOfJourney()
	{
		driver.findElement(By.linkText("07Mar'14(Fri)")).click();
        driver.findElement(By.linkText("8")).click();
	}
    
	public void EnterEmail(String email)
	{
        driver.findElement(By.id("email")).sendKeys(email);
	}   
     
	public void SubmitSearch()
	{
		driver.findElement(By.name("submit_name")).click();
	}
     
	public void HandleSpecialSearchPopup()
	{
        driver.findElement(By.xpath("//div[@id='popBusSearch']//button[normalize-space(.)='Search Bus']")).click();
        new Actions(driver).doubleClick(driver.findElement(By.xpath("//div[@id='content']/div[5]/div[2]/div"))).build().perform();
        
        driver.findElement(By.cssSelector("div.ng-scope")).click();
        driver.findElement(By.linkText("Select Bus")).click();
        driver.findElement(By.cssSelector("a.close_popup")).click();
	}   
 
	public void SelectBusName(String bName, String seatNum)
	{
		//driver.findElement(By.xpath("//div[@class='bus_listing']//p[.='"+bName+"']")).click();
        //driver.findElement(By.xpath("//div[@class='bus_listing']/div[5]/div[20]/div[1]/div[3]/p[1]")).click();
        driver.findElement(By.xpath("//div[@class='bus_listing']/div[5]/div[2]/div[1]/a")).click();
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}							
        
        driver.findElement(By.id(seatNum)).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("a.close_popup")).isDisplayed() , "Verify if the close popup displayed");
        driver.findElement(By.cssSelector("a.close_popup")).click();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	
	public static boolean isAlertPresent(FirefoxDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
*/	
}