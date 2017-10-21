package dccpages;

import logger.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.EnvironmentVariables;


public class DCCHomePage extends AbstractPage{
	private static final String OBJ_LOCATOR_FILE = "dm.locators.properties";

	private WebElement deviceManagerLink;
    static By byDeviceManagerLink;
    
    public DCCHomePage(WebDriver DRIVER) {
        super(DRIVER, OBJ_LOCATOR_FILE);
        /*// Check that we're on the right page.
        if (!"DCCHome".equals(DRIVER.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the home page");
        }*/
    }
    
    /**
     * This method is used to set by object for this page
     * @author shyam
     */
    @Override
    protected void initPageObject()
    {
    	byDeviceManagerLink =  By.xpath(LOCATOR_PROPS.getProperty("dcchome.devicemanager.link"));
    }
    
    /**
     * This method find the given tab and click
     * @param tabName
     * @return DCCHomePage
     */
	public DCCHomePage ClickThisPageLink(String tabName)
	{
		CustomLogger.logMethodStart();
		Assert.assertTrue(DRIVER.findElement(By.linkText(tabName)).isDisplayed(), "Verify if the tab ["+tabName+"] is displayed"); 
		DRIVER.findElement(By.linkText(tabName)).click();
		CustomLogger.logMethodEnd();
		return this;
	}
	
	/**
	 * This method is used to verify if the given widget tab is displayed
	 * @param tabName
	 * @return widget tab web element
	 */
	public WebElement getThisWidgetTab(String tabName)
	{
		Assert.assertTrue(DRIVER.findElement(By.xpath("//div[@class='widget_tabs_info']//span[.='"+tabName+"']")).isDisplayed(), "Verify widget tab ["+tabName+"] is displayed");
		return DRIVER.findElement(By.xpath("//div[@class='widget_tabs_info']//span[.='"+tabName+"']"));
	}
	
	/**
	 * This method is used to click given widget tab
	 * @param tabName
	 */
	public void ClickThisWidgetTab(String tabName)
	{
		WebElement tab = getThisWidgetTab(tabName);
		tab.click();
	}

	/**
     * This method verifies if the user id field displayed on UI
     * @author shyam
     * @return DCCLoginPage
     */
    public DCCHomePage verifyDMLinkPresent()
    {
    	CustomLogger.logMethodStart();
    	CustomLogger.setStep_Msg("", "Verify device manager link present");
    	CustomLogger.logMessage(null, 1);
    	Assert.assertTrue(genFun.verifyElementPresent(byDeviceManagerLink), EnvironmentVariables.getEnvironmentVars().get("msg").toString());
    	CustomLogger.logMethodEnd();
    	return this;
    }
}