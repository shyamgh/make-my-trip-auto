
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class WebDriverManager extends WebDriverInitializer {
	public static final Map activeDrivers = new HashMap<Object, String>();
	public static final Map openedBrowsers = new HashMap<Object, String>();

	public static void addActiveDriver(WebDriver wd) {
		activeDrivers.put("" + wd.toString(), wd);
	}

	public static void removeActiveDriver(WebDriver wd) {
		activeDrivers.remove("" + wd.toString());
	}

	public static WebDriver getActiveDriver(WebDriver wd) {
		return (WebDriver) activeDrivers.get("" + wd.toString());
	}

	public static void addOpenedBrower(WebDriver wd) {
		for (String winHandle : wd.getWindowHandles()) {
			System.out.println("Adding " + winHandle);
			openedBrowsers.put(wd.getWindowHandle(), wd.getTitle());
		}
	}

	public static void addOpenedBrower(WebDriver wd, String winHandle) {
		System.out.println("Adding " + winHandle);
		String currWin = wd.getWindowHandle();
		wd.switchTo().window(winHandle);
		openedBrowsers.put(winHandle, wd.getTitle());
		wd.switchTo().window(currWin);
	}

	public static void removeOpenedBrower(WebDriver wd) {
		for (String winHandle : wd.getWindowHandles()) {
			System.out.println("Removing " + winHandle);
			openedBrowsers.remove(winHandle);
		}
	}

	public static void removeOpenedBrower(WebDriver wd, String winHandle) {
		System.out.println("Removing " + winHandle);
		openedBrowsers.remove(winHandle);
	}

	public static void update(WebDriver wd) {
		for (String winHandle : wd.getWindowHandles()) {
			try {
				System.out.println("Adding into open browsers map " + winHandle);
				openedBrowsers.put(winHandle, wd.getTitle());
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}

	public static void getHandleToWindow(WebDriver wd) {

	}

	public static WebDriver getDriver() {
		return (WebDriver) WebDriverInitializer.getDriver();
	}
}
