
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTestClass {

	protected GenericFunctions genFunct;
	protected WebDriver driver;
	protected ITestContext context;

	@BeforeSuite
	public void setUP(ITestContext context) {
		CustomLogger.logMessage(null, "INIT", "Initializing Selenium Firefox driver...", 1);
		driver = WebDriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(SystemWaits.PAGE_WAIT, TimeUnit.SECONDS);
	}

	@AfterSuite
	public void tearDown() {
		driver.close();
		if (driver != null) {
			driver.quit();
		}
	}
}