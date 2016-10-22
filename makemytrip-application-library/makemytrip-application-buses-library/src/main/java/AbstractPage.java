

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;


public abstract class AbstractPage {

	protected final WebDriver DRIVER;
	public WebDriver getDriver() {
		return DRIVER;
	}
	protected SoftAssert softassert;
	private BusTicketBookingPage btbp;
	private CommonPage hp;
	protected final GenericFunctions genFun;
	
	/**
	 * This is constructor with single argument
	 * @param DRIVER
	 * @author shyam
	 */
	public AbstractPage(WebDriver DRIVER, String propFile)
	{
        this.DRIVER = DRIVER;
        genFun = new GenericFunctions(this.DRIVER);
        softassert = new SoftAssert();
    }
	
	public BusTicketBookingPage btbp() {
		if (btbp == null) {
			btbp = new BusTicketBookingPage(this.DRIVER);
		}
		return btbp;
	}
	
	public CommonPage dhp() {
		if (hp == null) {
			hp = new CommonPage(this.DRIVER);
		}
		return hp;
	}
    /**
     * This method is used to set by object for this page
     * @author shyam
     */
    protected abstract void initPageObject();
    
}
