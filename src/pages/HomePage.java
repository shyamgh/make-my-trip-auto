package pages;

import logger.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class HomePage {
	private final WebDriver DRIVER;

    public HomePage(WebDriver DRIVER) {
        this.DRIVER = DRIVER;

        /*// Check that we're on the right page.
        if (!"MakeMyTrip".equals(DRIVER.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the home page");
        }*/
    }
    
    /**
     * This method find the given tab and click
     * @param tabName
     * @return HomePage
     */
	public HomePage ClickThisPageLink(String tabName)
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
       // DRIVER.findElement(By.cssSelector("#trip_type > label")).click();
       // DRIVER.findElement(By.id("trip_type_oneway")).click();
	}

}