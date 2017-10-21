package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import logger.CustomLogger;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import dccpages.DCCHomePage;
import dccpages.DCCLoginPage;
import dccpages.DeviceManagerPage;
import utils.EnvironmentVariables;
import utils.GenericFunctions;
import utils.MyTestListener;
import utils.RemoteCommandExec;

//@Listeners({MyTestListener.class})
public class DCCTestClass extends InitTestClass{
	private DCCLoginPage dlogin;
	private DCCHomePage dhome;
	private DeviceManagerPage dm;
	
	public DCCTestClass() {
		super();
	}
	
	
	@Test(description="To test DCC login with valid credentials")
	public void DCCUseridPresentTest() {
		genFunct.OpenThisUrl(genFunct.GetSystemParameter("dcc.url"));
		dlogin = new DCCLoginPage(driver);		
		CustomLogger.logMessage(null, "Start -- "+this.toString(), "", 1);
		dlogin.verifyuseriddisplayed();
		CustomLogger.logMessage(null, "End -- "+this.toString(), "", 1);
	}
	
	@Test(description="To test DCC login with valid credentials")
	public void DCCLoginTest() {
		genFunct.OpenThisUrl(genFunct.GetSystemParameter("dcc.url"));
		dlogin = new DCCLoginPage(driver);		
		CustomLogger.logMessage(null, "Start -- "+this.getClass().getName(), "", 1);
		dlogin.loginAs(genFunct.GetSystemParameter("dcc.user"), genFunct.GetSystemParameter("dcc.pass"));
		CustomLogger.logMessage(null, "End -- "+this.getClass().getName(), "", 1);
	}
	
	
	@Test(description="To test DCC login with valid credentials")
	public void DCCDMLinkPresentTest() {
		dhome = new DCCHomePage(driver);	
		CustomLogger.logMessage(null, "Start -- "+this.getClass().getName(), "", 1);
		dhome.verifyDMLinkPresent();
		CustomLogger.logMessage(null, "End -- "+this.getClass().getName(), "", 1);
	}
	
	@Test(description="To test device registration end to end")
	public void DCCRegDeviceEndToEndTest() {
		CustomLogger.logMessage(null, "Start -- "+this.getClass().getName(), "", 1);
		dm = new DeviceManagerPage(driver);
		dm.registerDevice(genFunct.GetSystemParameter("dm.device"));
		dm.selectArea(genFunct.GetSystemParameter("dm.area"));
		dm.selectGroup(genFunct.GetSystemParameter("dm.group"));
		dm.isDeviceRegSuccessful();
		CustomLogger.logMessage(null, "End -- "+this.getClass().getName(), "", 1);
	}
	
	@Test(description="To test select device")
	public void DCCSelectDeviceTest() {
		CustomLogger.logMessage(null, "Start -- "+this.getClass().getName(), "", 1);
		dm = new DeviceManagerPage(driver);
		dm.selectDevice(genFunct.GetSystemParameter("dm.device"));
		dm.clickRegister();
		CustomLogger.logMessage(null, "End -- "+this.getClass().getName(), "", 1);
	}
		
	@Test(description="To test select area")
	public void DCCSelectAreaTest() {
		CustomLogger.logMessage(null, "Start -- "+this.getClass().getName(), "", 1);
		dm = new DeviceManagerPage(driver);
		dm.selectArea(genFunct.GetSystemParameter("dm.area"));
		CustomLogger.logMessage(null, "End -- "+this.getClass().getName(), "", 1);
	}
	
	@Test(description="To test select group")
	public void DCCSelectGroupTest() {
		CustomLogger.logMessage(null, "Start -- "+this.getClass().getName(), "", 1);
		dm = new DeviceManagerPage(driver);
		dm.selectGroup(genFunct.GetSystemParameter("dm.group"));
		CustomLogger.logMessage(null, "End -- "+this.getClass().getName(), "", 1);
	}
	
	@Test(description="To test system logs")
	public void DCCLogTest() {
		String searchString  = ".log";
		EnvironmentVariables.getEnvironmentVars().put("step", "Verify System logs");
		
		try {
			ArrayList al = new RemoteCommandExec().ExecuteCmdOnThisHost("127.0.0.1", "", "", "ls");
			CustomLogger.logMessage(null, EnvironmentVariables.getEnvironmentVars().get("step").toString(), "Verifying ["+searchString+"] present in command output.", 1);
			Assert.assertTrue(GenericFunctions.searchThisArrayList(al, searchString), "Verify System logs");			
		} catch (IOException e) {
			CustomLogger.logMessage(Level.SEVERE, EnvironmentVariables.getEnvironmentVars().get("step").toString(), "Exception while verofying system logs", 1);
			e.printStackTrace();
		}
	}

}
