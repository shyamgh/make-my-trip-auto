package utils;

import logger.CustomLogger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class MyEventListener implements WebDriverEventListener {
    // All the methods of the WebDriverEventListener need to
    // be implemented here. You can leave most of them blank.
    // For example...
	@Override
    public void onException(Throwable arg0, WebDriver arg1) {
        //String file = new GenericFunctions(arg1).takeScreenShot(arg0.getMessage());
        //System.out.println("Screen shot taken for exception ["+file+"]");
    }

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		String msg = "Value of ["+arg0.toString()+"] changed to '"+arg0.getText()+"'";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Change value", msg, 1);	
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		String msg = "WebElement ["+arg0.toString()+"] clicked.";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Click element", msg, 1);	
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		String msg = "WebElement ["+arg0.toString()+"] found.";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Find element", msg, 1);
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		String msg = "Navigated to url ["+arg0+"].";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Navigate", "Navigate to url ["+arg0+"].", 1);
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}


}