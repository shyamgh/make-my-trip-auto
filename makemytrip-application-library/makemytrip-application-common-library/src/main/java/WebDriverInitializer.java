import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverInitializer {

	// protected WebDriverEventListener eventListener;

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		return new ChromeDriver();
	}

}
