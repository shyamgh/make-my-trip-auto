package utils;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebDriverInitializer {
	
	//protected WebDriverEventListener eventListener;
	
	public static EventFiringWebDriver getDriver() {
		//eventListener = new MyEventListener();
		return new EventFiringWebDriver(new FirefoxDriver()).register(new MyEventListener());
	}
	
}
