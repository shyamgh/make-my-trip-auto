package dccpages;

import utils.EnvironmentVariables;
import utils.SystemWaits;
import java.util.logging.Level;
import logger.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DCCLoginPage extends AbstractPage{

	private static final String OBJ_LOCATOR_FILE = "dm.locators.properties";
	private WebElement userId;
    private WebElement password;
    private WebElement signIn;
    private WebElement loginMsg;
    
    static By byUserId;
    static By byPassword;
    static By bySignIn;
    static By byLoginMsg;

	
	/**
	 * This is constructor with single argument
	 * @param DRIVER
	 * @author shyam
	 */
	public DCCLoginPage(WebDriver DRIVER)
	{
		
		super(DRIVER, OBJ_LOCATOR_FILE);
		initPageObject();
        /*// Check that we're on the right page.
        if (!"DCCLogin".equals(DRIVER.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the DCC login page");
        }*/
    }
    
    	
    /**
     * This method is used to set by object for this page
     * @author shyam
     */
    @Override
    protected void initPageObject()
    {
    	
    	byUserId = By.xpath(LOCATOR_PROPS.getProperty("dcc.userId.txt"));
    	byPassword = By.xpath(LOCATOR_PROPS.getProperty("dcc.password.txt"));
    	bySignIn = By.xpath(LOCATOR_PROPS.getProperty("dcc.signIn.btn"));
       	byLoginMsg = By.xpath(LOCATOR_PROPS.getProperty("dcc.login.msg"));
    }
    
    
    /**
     * This method verifies if the user id field displayed on UI
     * @author shyam
     * @return DCCLoginPage
     */
    public DCCLoginPage verifyuseriddisplayed()
    {
    	CustomLogger.logMethodStart();
    	CustomLogger.setStep_Msg("", "Verify user id displayed");
    	CustomLogger.logMessage(null, 1);
    	//softassert.assertEquals(userId.isDisplayed(), true, EnvironmentVariables.getEnvironmentVars().get("msg").toString());
    	Assert.assertTrue(genFun.verifyElementPresent(byUserId), EnvironmentVariables.getEnvironmentVars().get("msg").toString());
    	CustomLogger.logMethodEnd();
    	return this;
    }
    

    /**
     * This method is used to type their email into the email field
     * @param email
     * @return LoginPage
     */
    public DCCLoginPage typeUserId(String sUserId) 
    {
    	CustomLogger.logMethodStart();
    	CustomLogger.logMessage(null, "Enter user id", "Enter user id "+sUserId, 1);
    	userId.sendKeys(sUserId);
    	CustomLogger.logMethodEnd();
        return this;    
    }

    /**
     * This method is used to type their password into the password field
     * @param pass
     * @return DCCLoginPage
     */
    public DCCLoginPage typePassword(String sPassword)
    {
    	CustomLogger.logMethodStart();
    	CustomLogger.logMessage(null, "Enter password", "Enter password "+sPassword, 1);
    	password.sendKeys(sPassword);
    	CustomLogger.logMethodEnd();
        return this;    
    }

    /**
     * This method is used to submit the login form
     * @author shyam
     * @return DCCHomePage
     */
    public DCCHomePage submitLogin()
    {
    	CustomLogger.logMethodStart();
    	CustomLogger.logMessage(null, "", "Submit the login form", 1);    	
    	signIn.submit();
       	CustomLogger.logMethodEnd();
        return new DCCHomePage(this.DRIVER);
    }

    /**
     * This method verify if the login successful message displayed.
     * @return DCCHomePage
     */
    public DCCHomePage verifyLoginSuccessfull() {
    	CustomLogger.logMethodStart();
    	CustomLogger.logMessage(null, "Verify login successful", "Verify message ["+byLoginMsg.toString()+"] displayed.", 1);
    	try {
			genFun.WaitForObject(loginMsg, SystemWaits.STANDARD_WAIT);
		} catch (Exception e) {
			CustomLogger.logMessage(Level.SEVERE, "Verify login successful", "Verify message ["+byLoginMsg.toString()+"] not displayed.", 1);
			CustomLogger.logMessage(Level.SEVERE, "", e.getMessage(), 1);
		}
    	genFun.verifyElementPresent(byLoginMsg);
    	CustomLogger.logMethodEnd();
        return new DCCHomePage(this.DRIVER);   
    }

    /**
     * This method used for valid login into application
     * @author shyam
     * @param sUserId
     * @param sPassword
     * @return DCCHomePage
     */
    public DCCHomePage loginAs(String sUserId, String sPassword) {
    	CustomLogger.logMethodStart();
    	verifyuseriddisplayed();
    	typeUserId(sUserId);
        typePassword(sPassword);
        submitLogin();
        CustomLogger.logMethodEnd();
        return verifyLoginSuccessfull();
    }
    
  
}
