package pages;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import logger.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import com.logger.MyLogger;

import utils.EnvironmentVariables;
import utils.GenericFunctions;


public class BusTicketBookingPage extends AbstractPage {

	private static final String OBJ_LOCATOR_FILE = "locators.properties";
	private WebElement departureCity;
    private WebElement destinationCity;
    private WebElement email;
    private WebElement datepicker;
    private WebElement datepickerCurrMonth;
    private WebElement datepickerNextMonth;
    
    static By byDepartureCity;
    static By byDestinationCity;
    static By byEmail;
    static By byDatepicker;
    static By byDatepickerCurrMonth;
    static By byDatepickerNextMonth;
    
    public BusTicketBookingPage(WebDriver DRIVER) {
        super(DRIVER, OBJ_LOCATOR_FILE);
    	initPageObject();        
        /*// Check that we're on the right page.
        if (!"MakeMyTrip".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the home page");
        }*/
    }
    
    /**
     * This method is used to set by object for this page
     * @author shyam
     */
    protected void initPageObject()
    {
        byDepartureCity = By.xpath(LOCATOR_PROPS.getProperty("busticketbooking.departureCity.txt"));
        byDestinationCity = By.xpath(LOCATOR_PROPS.getProperty("busticketbooking.destinationCity.txt"));
        byEmail = By.name(LOCATOR_PROPS.getProperty("busticketbooking.email.txt"));
        byDatepicker = By.xpath(LOCATOR_PROPS.getProperty("busticketbooking.datepicker.dtp"));
    }
    
    /**
     * This method find the given tab and click
     * @param tabName
     * @return BusTicketBookingPage
     * @author shyam
     */
	public BusTicketBookingPage ClickThisPageLink(String tabName)
	{
		CustomLogger.logMethodStart();
		CustomLogger.logMessage(Level.INFO, "Verify page link" , "Verifying if the tab ["+tabName+"] is displayed", 1);
		Assert.assertTrue(DRIVER.findElement(By.linkText(tabName)).isDisplayed(), "Verify if the tab ["+tabName+"] is displayed"); 
		CustomLogger.logMessage(Level.INFO, "Click given page link", "Click the tab ["+tabName+"]", 1);
		DRIVER.findElement(By.linkText(tabName)).click();
		CustomLogger.logMethodEnd();
		return this;
	}
	
	/**
	 * This method is used to verify if the given widget tab is displayed
	 * @param tabName
	 * @return widget tab web element
	 * @author shyam
	 */
	public WebElement getThisWidgetTab(String tabName)
	{
		CustomLogger.logMethodStart();
		CustomLogger.logMessage(Level.INFO, "Verify widget tab displayed", "Verifying if the tab ["+tabName+"] is displayed", 1);
		Assert.assertTrue(DRIVER.findElement(By.xpath("//div[@class='widget_tabs_info']//span[.='"+tabName+"']")).isDisplayed(), "Verify widget tab ["+tabName+"] is displayed");
		CustomLogger.logMethodEnd();
		return DRIVER.findElement(By.xpath("//div[@class='widget_tabs_info']//span[.='"+tabName+"']"));
	}
	
	/**
	 * This method is used to click given widget tab
	 * @param tabName
	 * @author shyam
	 */
	public BusTicketBookingPage ClickThisWidgetTab(String tabName)
	{
		CustomLogger.logMethodStart();
		WebElement tab = getThisWidgetTab(tabName);
		CustomLogger.logMessage(Level.INFO, "Click widget tab", "Click widget tab ["+tabName+"]", 1);
		tab.click();
		CustomLogger.logMethodEnd();
		return this;
	}
	
	/**
	 * This method is used to enter departure and destination cities for searching bus
	 * @param depCity
	 * @param destCity
	 * @author shyam
	 */
	public BusTicketBookingPage SelectToAndFrom(String depCity, String destCity)
	{
		CustomLogger.logMethodStart();
		DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		CustomLogger.logMessage(Level.INFO, "Verify element displayed","Verifying if text box ["+byDepartureCity+"] is displayed", 1);
		Assert.assertTrue(genFun.verifyElementPresent(byDepartureCity), "Verify departure city text box is displayed");
		departureCity = DRIVER.findElement(byDepartureCity);
		CustomLogger.logMessage(Level.INFO, "Enter text", "Enter text ["+depCity+"]", 1);
		departureCity.sendKeys(depCity);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		departureCity.sendKeys(Keys.ARROW_DOWN);
		departureCity.sendKeys(Keys.ENTER);
		
		CustomLogger.logMessage(Level.INFO, "Verify element displayed", "Verifying if text box ["+byDestinationCity+"] is displayed", 1);
		Assert.assertTrue(genFun.verifyElementPresent(byDestinationCity), "Verify destination city text box is displayed");
		destinationCity = DRIVER.findElement(byDestinationCity);
		CustomLogger.logMessage(Level.INFO, "Enter text", "Enter text ["+destCity+"]", 1);
		destinationCity.sendKeys(destCity);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		destinationCity.sendKeys(Keys.ARROW_DOWN);
		destinationCity.sendKeys(Keys.ENTER);
		
		CustomLogger.logMethodEnd();
		return this;
	}
	
	/**
	 * This function is used to select date of journey
	 * @author shyam
	 * @param currMonth : if the date from current month
	 * @param date : date of journey
	 */
	public BusTicketBookingPage SelectDateOfJourney(boolean currMonth, String date)
	{
		CustomLogger.logMethodStart();
		WebElement wb = genFun.waitForElement(By.xpath("//a[@class='date_field_tab flL make_relative right']"));
		wb.click();
		wb=null;
		if (currMonth)
			wb = genFun.waitForElement(By.xpath(LOCATOR_PROPS.getProperty("busticketbooking.datepickercurrmonth.dtp")+"//a[.='"+date+"']"));
		else
			wb = genFun.waitForElement(By.xpath(LOCATOR_PROPS.getProperty("busticketbooking.datepickernextmonth.dtp")+"//a[.='"+date+"']"));
        wb.click();
        CustomLogger.logMethodEnd();
        return this;
	}
    
	/**
	 * This method is used to enter email of user
	 * @author shyam
	 * @param email
	 * @return BusTicketBookingPage
	 */
	public BusTicketBookingPage EnterEmail(String eml)
	{
		CustomLogger.logMethodStart();
		CustomLogger.logMessage(Level.INFO, "Verify element displayed", "Verifying text box ["+byEmail+"] is displayed", 1);
		Assert.assertTrue(genFun.verifyElementPresent(byEmail), "Verify email text box is displayed");
		email = DRIVER.findElement(byEmail);
		CustomLogger.logMessage(Level.INFO, "Enter text", "Enter text ["+eml+"]", 1);
        email.sendKeys(eml);
        CustomLogger.logMethodEnd();
        return this;
	}   

	/**
	 * This function submit and initiates bus search
	 * @author shyam 
	 */
	public void SubmitSearch()
	{
		CustomLogger.logMethodStart();
		CustomLogger.logMessage(Level.INFO, "Verify element displayed", "Verifying submit button is displayed", 1);
		Assert.assertTrue(genFun.verifyElementPresent(By.name("submit_name")), "Verify submit button is displayed");
		CustomLogger.logMessage(Level.INFO, "Submit search", "Click submit button to initiate search", 1);
		DRIVER.findElement(By.name("submit_name")).click();
		CustomLogger.logMethodEnd();
	}
 
	/**
     * This method clicks confirmation popup window after search bus submitted 
     * @author shyam 
     */
	public void HandleSpecialSearchPopup()
	{
		CustomLogger.logMethodStart();
		By element = By.xpath("//div[@id='popBusSearch']//button[normalize-space(.)='Search Bus']");
		CustomLogger.logMessage(Level.INFO, "Verify element displayed","Verifying ["+element.toString()+"] is displayed", 1);
		Assert.assertTrue(genFun.verifyElementPresent(By.name("submit_name")), "Verifying ["+element.toString()+"] is displayed");		
		CustomLogger.logMessage(Level.INFO, "Click search button", "Click search button", 1);
		DRIVER.findElement(element).click();		
		CustomLogger.logMessage(Level.INFO, "Click Select Bus", "", 1);
		DRIVER.findElement(By.linkText("Select Bus")).click();
		CustomLogger.logMessage(Level.INFO, "Closing popup", "", 1);
        DRIVER.findElement(By.cssSelector("a.close_popup")).click();
        CustomLogger.logMethodEnd();
	}   

}