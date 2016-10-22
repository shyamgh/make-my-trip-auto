
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.TestResult;

public class CustomWebDriverEventListener implements WebDriverEventListener {

	public void onException(Throwable arg0, WebDriver arg1) {
		ITestResult result = new TestResult();
		// String snap = new GenericFunctions2().takeScreenShot(arg1, "Exception
		// "+arg0.getMessage());
		/// String snap = new GenericFunctions().takeScreenShot(arg1,
		// arg0.getMessage());
		/*
		 * String msg = "<br /><a href=\"" + snap + "\">" + "<img src=\"" + snap
		 * + "\" alt=\"errorPic\" height=\"50\" width=\"200\"/>" + "</a><br />";
		 */
		Reporter.setCurrentTestResult(result);
		Reporter.setEscapeHtml(false);
		//// Reporter.log(msg);
		Reporter.setCurrentTestResult(null);
	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		String msg = "Value of [" + arg0.toString() + "] changed to '" + arg0.getText() + "'";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Change value", msg, 1);
	}

	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		String msg = "WebElement [" + arg0.toString() + "] clicked.";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Click element", msg, 1);
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		String msg = "WebElement [" + arg0.toString() + "] found.";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Find element", msg, 1);
	}

	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
	}

	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		String msg = "Navigated to url [" + arg0 + "].";
		System.out.println(msg);
		CustomLogger.logMessage(null, "Navigate", "Navigate to url [" + arg0 + "].", 1);
	}

	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

}