package dccpages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import utils.EnvironmentVariables;
import utils.GenericFunctions;


abstract class AbstractPage {

	protected final WebDriver DRIVER;
	protected SoftAssert softassert;
	protected final Properties LOCATOR_PROPS;
	private DCCLoginPage dlp;
	private DCCHomePage dhp;
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
	
	public DCCLoginPage dlp() {
		if (dlp == null) {
			dlp = new DCCLoginPage(this.DRIVER);
		}
		return dlp;
	}
	
	public DCCHomePage dhp() {
		if (dhp == null) {
			dhp = new DCCHomePage(this.DRIVER);
		}
		return dhp;
	}
    /**
     * This method is used to set by object for this page
     * @author shyam
     */
    protected abstract void initPageObject();
    
}