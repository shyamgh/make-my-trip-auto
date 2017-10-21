package pages;

import utils.GenericFunctions;
import java.util.logging.Level;

import logger.CustomLogger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;


public class LoginPage {

	private final WebDriver DRIVER;
	private SoftAssert softassert;

	
	/**
	 * This is constructor with single argument
	 * @param DRIVER
	 * @author shyam
	 */
	public LoginPage(WebDriver DRIVER)
	{
        this.DRIVER = DRIVER;
        PageFactory.initElements(DRIVER, this);
        softassert = new SoftAssert();
        // Check that we're on the right page.
        if (!"Gmail".equals(DRIVER.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the login page");
        }
    }

    // The login page contains several HTML elements that will be represented as WebElements.
    // The locators for these elements should only be defined once.
    @FindBy(xpath = "//input[@id='Email' and @type='email']") WebElement userid;
	    /*@FindBy(xpath = "//input[@id='Email']") WebElement userid;
	    @FindBy(id="Email") public WebElement userid;
	    @FindBy(how = How.ID ,using = "Email") public WebElement userid;
	    By emailLocator = By.id("Email");
	    By passwordLocator = By.id("Passwd");
	    By loginButtonLocator = By.id("signIn");*/
    @FindBy(xpath = "//input[@id='Passwd' and @type='password']") WebElement passwd;
    @FindBy(xpath = "//input[@id='signIn' and @type='submit']") WebElement signIn;
    @FindBy(xpath = "//input[@id='PersistentCookie' and @type='checkbox']") WebElement staySignedin;

    /*public WebElement getUserid()
    {
    	return this.userid;
    }*/
    
    /**
     * This method verifies if the email id field displayed ojn UI
     * @author shyam
     * 
     */
    public void verifyemaildisplayed()
    {
    	CustomLogger.logMethodStart();
    	softassert.assertEquals(userid.isDisplayed(), true);
    	//System.out.println(DRIVER.findElement(emailLocator).isDisplayed());
    	CustomLogger.logMethodEnd();
    }
    
    /**
     * This method verifies if the login error is displayed on UI, can be used for negative testing
     * @author shyam
     */
    public void verifyLoginError()
    {
    	//WebElement loginError = DRIVER.findElement(By.id("errormsg_0_Passwd"));
    	/*try {
    		if (!new GenericFunctions(DRIVER).WaitForErrorObject("errormsg_0_Passwd", 5))
    			Reporter.log("Login successful");
    	} catch (Exception e) {
				try {
					throw new Exception("Invalid user or password error is displayed");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}*/
    	CustomLogger.logMethodStart();
    	new GenericFunctions(DRIVER).WaitForErrorObject("errormsg_0_Passwd", 5);
    	CustomLogger.logMethodEnd();

    }
 
    /**
     * This method is used to type their email into the email field
     * @param email
     * @return LoginPage
     */
    public LoginPage typeEmail(String email) {
        // This is the only place that "knows" how to enter a email
        //DRIVER.findElement(emailLocator).sendKeys(email);
    	CustomLogger.logMethodStart();

    	userid.sendKeys(email);
    	Reporter.log("Entered user email");
    	
    	CustomLogger.logMethodEnd();

    	// Return the current page object as this action doesn't navigate to a page represented by another PageObject
        return this;    
    }

    /**
     * This method is used to type their password into the password field
     * @param password
     * @return LoginPage
     */
    public LoginPage typePassword(String password) {
        // This is the only place that "knows" how to enter a password
        //DRIVER.findElement(passwordLocator).sendKeys(password);
    	CustomLogger.logMethodStart();
    	
    	passwd.sendKeys(password);
    	Reporter.log("Entered password");

    	CustomLogger.logMethodEnd();

        // Return the current page object as this action doesn't navigate to a page represented by another PageObject
        return this;    
    }

    /**
     * This method is used to submit the login form
     * @return HomePage
     */
    public HomePage submitLogin() {
        // This is the only place that submits the login form and expects the destination to be the home page.
        // A seperate method should be created for the instance of clicking login whilst expecting a login failure. 
    	CustomLogger.logMethodStart();
    	
        //DRIVER.findElement(loginButtonLocator).submit();
    	
    	//Uncheck the stay sign in check box
    	if(staySignedin.isSelected()) {
    		staySignedin.click();
    		Reporter.log("'Stay Sign in' check box is in selected state so unchecking it");
    	}
    	else
    		Reporter.log("'Stay Sign in' check box is not selected, so skiping uncheck action");
    	signIn.submit();
    	
    	Reporter.log("Clicked sign in");

    	CustomLogger.logMethodEnd();
    	
        // Return a new page object representing the destination. Should the login page ever
        // go somewhere else (for example, a legal disclaimer) then changing the method signature
        // for this method will mean that all tests that rely on this behaviour won't compile.
        return new HomePage(DRIVER);
    }

    /**
     * This method is used to verify login exception knowing that an invalid email and / or password were entered
     * @author shyam
     * @return LoginPage
     */
    public LoginPage submitLoginExpectingFailure() {
        // This is the only place that submits the login form and expects the destination to be the login page due to login failure.
    	CustomLogger.logMethodStart();
    	
    	//DRIVER.findElement(loginButtonLocator).submit();
    	signIn.submit();
    	Reporter.log("Clicked sign in");

    	CustomLogger.logMethodEnd();
    	
        // Return a new page object representing the destination. Should the user ever be navigated to the home page after submiting a login with credentials 
        // expected to fail login, the script will fail when it attempts to instantiate the LoginPage PageObject.
        return new LoginPage(DRIVER);   
    }

    /**
     * This method used for valid login into application
     * @param email
     * @param password
     * @return HomePage
     */
    public HomePage loginAs(String email, String password) {
        // The PageObject methods that enter email, password & submit login have already defined and should not be repeated here.
    	CustomLogger.logMethodStart();
    	
    	typeEmail(email);
        typePassword(password);
        
        CustomLogger.logMethodEnd();
        
        return submitLogin();
    }
}
