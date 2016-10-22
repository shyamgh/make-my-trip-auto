
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class MyTestListener extends TestListenerAdapter {
	@Override
	public void onTestStart(ITestResult itr) {
		super.onTestStart(itr);
		System.out.println("TEST LISTENER onTestStart itr = " + itr);
	}
	@Override
	public void onTestFailure(ITestResult result) {
		super.onTestFailure(result);
		System.out.println("TEST LISTENER onTestFailure tr = " + result);
		System.out.println(EnvironmentVariables.getEnvironmentVars().get("step"));
		System.out.println(EnvironmentVariables.getEnvironmentVars().get("msg"));
		String snap = new GenericFunctions().takeScreenShot("Exception "+result.getName());
		Reporter.setCurrentTestResult(result); 
	    Reporter.log("<br /> <img src=..\\\""+snap+"\" /> <br />");
	    Reporter.setCurrentTestResult(null);
	}
}
