package dccpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import logger.CustomLogger;

public class DeviceManagerPage extends AbstractPage{

	private static final String OBJ_LOCATOR_FILE = "dm.locators.properties";
    private WebElement selectDevice;
    private WebElement register;
    private WebElement selectArea;
    private WebElement selectGroup;
    private WebElement regSuccessfulMsg;
    
    static By bySelectDevice;
    static By byRegister;
    static By bySelectArea;
    static By bySelectGroup;
    static By byRegSuccessfulMsg;
    
    public DeviceManagerPage(WebDriver DRIVER) {
        super(DRIVER, OBJ_LOCATOR_FILE);
        /*// Check that we're on the right page.
        if (!"DeviceManager".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the Device manager page");
        }*/
        initPageObject();
    }
    
    
    /**
     * This method is used to set by object for this page
     * @author shyam
     */
    @Override
    protected void initPageObject()
    {
        bySelectDevice = By.xpath(LOCATOR_PROPS.getProperty("dm.selectdevice.list"));
        byRegister = By.xpath(LOCATOR_PROPS.getProperty("dm.registerdevice.btn"));
        bySelectArea = By.xpath(LOCATOR_PROPS.getProperty("dm.selectarea.list"));
        bySelectGroup = By.xpath(LOCATOR_PROPS.getProperty("dm.selectgroup.list"));
        byRegSuccessfulMsg = By.name(LOCATOR_PROPS.getProperty("dm.regsuccessful.msg"));
    }
	
	
	public void selectDevice(String deviceID) {
		CustomLogger.logMethodStart();
		CustomLogger.setStep_Msg("", "Verify select device element present");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Verify given device ID present");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Selected given device ID");
		CustomLogger.logMessage(null, 1);
		CustomLogger.logMethodEnd();
	}
	
	public void clickRegister() {
		CustomLogger.logMethodStart();
		CustomLogger.setStep_Msg("", "Verify register device element present");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Device register initiated");
		CustomLogger.logMessage(null, 1);
		CustomLogger.logMethodEnd();
	}
	public void registerDevice(String deviceID) {
		CustomLogger.logMethodStart();
		selectDevice(deviceID);
		clickRegister();
		CustomLogger.logMethodEnd();
	}
	
	public void selectArea(String area) {
		CustomLogger.logMethodStart();
		CustomLogger.setStep_Msg("", "Verify select area element present");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Verify given area present");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Selected given area");
		CustomLogger.logMessage(null, 1);
		CustomLogger.logMethodEnd();		
	}
	public void selectGroup(String groupName) {
		CustomLogger.logMethodStart();
		CustomLogger.setStep_Msg("", "Verify select group element present");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Verify given group present");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Selected given group");
		CustomLogger.logMessage(null, 1);
		CustomLogger.logMethodEnd();
	}
	public void verifyUIMessage(By byMsg) {
		CustomLogger.logMethodStart();
		CustomLogger.setStep_Msg("", "Verify given message ["+byMsg.toString()+"] displayed on UI");
		CustomLogger.logMessage(null, 1);
		CustomLogger.setStep_Msg("", "Message ["+byMsg.toString()+"] displayed on UI (yet to be implemented)");
		CustomLogger.logMessage(null, 1);
		CustomLogger.logMethodEnd();
	}
	public void isDeviceRegSuccessful() {
		CustomLogger.logMethodStart();
		verifyUIMessage(byRegSuccessfulMsg);
		CustomLogger.logMethodEnd();
	}
	
	
	public void DCCLogin(String DCCUrl, String user, String pass) {
		CustomLogger.logMethodStart();
		
		CustomLogger.logMethodEnd();
	}
	
	public void navigateToTab(String tabName) {
		CustomLogger.logMethodStart();
		CustomLogger.logMethodEnd();
	}

	public void isScreenDisplayed(String screenName) {
		CustomLogger.logMethodStart();
		CustomLogger.logMethodEnd();
	}

}
