
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class UIUtility {
	private final WebDriver driver;
	private ITestContext context;

	public UIUtility(WebDriver driver) {
		this.driver = driver;
	}

	public UIUtility(WebDriver driver, ITestContext context) {
		this.driver = driver;
		this.context = context;
	}

	/**
	 * This will open the given URL
	 * 
	 * @param url
	 * @author shyam
	 */
	public void OpenThisUrl(String url) {
		CustomLogger.logMessage(null, "", "Opening url " + url, 1);
		driver.get(url);
	}

	/**
	 * This will open the given URL
	 * 
	 * @param driver
	 * @param url
	 * @author shyam
	 */
	public void OpenThisUrl(WebDriver driver, String url) {
		CustomLogger.logMessage(null, "", "Opening url " + url, 1);
		driver.get(url);
	}

	/**
	 * This method opens given url and wait till page loads
	 * 
	 * @author shyam
	 * @param url
	 * @param verifyPageTitle
	 */
	public void OpenThisUrlAndWait(String url, String verifyPageTitle) {
		CustomLogger.logMessage(null, "", "Opening url " + url, 1);
		driver.get(url);
		GenericFunctions.WaitTillPageLoad(driver, verifyPageTitle);
	}

	/**
	 * This method opens given url and wait till page loads
	 * 
	 * @author shyam
	 * @param driver
	 * @param url
	 * @param verifyPageTitle
	 */
	public void OpenThisUrlAndWait(WebDriver driver, String url, String verifyPageTitle) {
		CustomLogger.logMessage(null, "", "Opening url " + url, 1);
		driver.get(url);
		GenericFunctions.WaitTillPageLoad(driver, verifyPageTitle);
	}

	/**
	 * This function closes and quits the driver
	 * 
	 * @author Shyam
	 */
	public void CloseBrowser() {
		CustomLogger.logMessage(null, "", "Closing the browser and driver instance.", 1);
		driver.close();
		driver.quit();
		CustomLogger.logMessage(null, "", "Closed.", 1);
	}

	/**
	 * This function closes and quits the driver
	 * 
	 * @author Shyam
	 * @param driver
	 */
	public void CloseBrowser(WebDriver driver) {
		CustomLogger.logMessage(null, "", "Closing the browser and driver instance.", 1);
		driver.close();
		driver.quit();
		CustomLogger.logMessage(null, "", "Closed.", 1);
	}

}
