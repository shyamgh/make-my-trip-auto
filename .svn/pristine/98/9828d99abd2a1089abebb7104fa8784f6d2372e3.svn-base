package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import utils.EnvironmentVariables;
import utils.GenericFunctions;
import dccpages.DCCHomePage;
import dccpages.DCCLoginPage;

public abstract class AbstractPage {

	protected final WebDriver DRIVER;
	public WebDriver getDriver() {
		return DRIVER;
	}
	protected SoftAssert softassert;
	protected final Properties LOCATOR_PROPS;
	private BusTicketBookingPage btbp;
	private HomePage hp;
	protected final GenericFunctions genFun;
	
	/**
	 * This is constructor with single argument
	 * @param DRIVER
	 * @author shyam
	 */
	public AbstractPage(WebDriver DRIVER, String propFile)
	{
        this.DRIVER = DRIVER;
        this.LOCATOR_PROPS = GenericFunctions.loadPropertiesFromThisFile(EnvironmentVariables.CONFIG_PATH+"\\"+propFile);
        genFun = new GenericFunctions(this.DRIVER);
        softassert = new SoftAssert();
    }
	
	public BusTicketBookingPage btbp() {
		if (btbp == null) {
			btbp = new BusTicketBookingPage(this.DRIVER);
		}
		return btbp;
	}
	
	public HomePage dhp() {
		if (hp == null) {
			hp = new HomePage(this.DRIVER);
		}
		return hp;
	}
    /**
     * This method is used to set by object for this page
     * @author shyam
     */
    protected abstract void initPageObject();
    
}
