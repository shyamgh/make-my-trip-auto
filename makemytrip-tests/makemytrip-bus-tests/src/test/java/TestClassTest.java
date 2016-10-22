
import java.io.IOException;

import org.testng.annotations.Test;

public class TestClassTest extends BaseTestClass {

	private String verifyText = "";
	private BusSearchResultPage bsr;

	public TestClassTest() {
		super();
	}

	@Test(enabled = false, description = "To Test home page loading within page load time")
	public void LoadHomePageTest() {
		String url = "https://www.makemytrip.com/bus-tickets";
		//// CustomLogger.logMessage(null, "Start -- Load Home Page", "", 1);
		genFunct.OpenThisUrl(url);
		verifyText = "MakeMyTrip";
		GenericFunctions.WaitTillPageLoad(driver, verifyText);
		//// CustomLogger.logMessage(null, "End -- Load Home Page", "" + url,
		//// 1);
	}

	@Test(enabled = false, description = "To Test bus search functionality", dependsOnMethods = "LoadHomePageTest")
	public void BusSearchTest() {
		//// CustomLogger.logMessage(null, "Start -- Bus search", "", 1);
		BusTicketBookingPage btp = new BusTicketBookingPage(driver);
		btp.ClickThisWidgetTab("Bus");
		btp.SelectToAndFrom("Pune", "Nagpur");
		btp.SelectDateOfJourney(false, "24");
		btp.EnterEmail("testemail@gmail.com");
		btp.SubmitSearch();
		btp.HandleSpecialSearchPopup();
		//// CustomLogger.logMessage(null, "End -- Bus search", "", 1);
	}

	@Test(description = "To Test home page loading within page load time")
	public void SelectSeatTest() {
		// SelectBusName("Vrl", "seat1");
	}

	public void testPrintMessage() {
		//// CustomLogger.logMessage(null, "Start -- Print messages", "", 1);
		try {
			new RemoteCommandExec().ExecuteCmdOnThisHost("localhost", "user", "pwd", "ls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//// CustomLogger.logMessage(Level.INFO, "End -- Print messages", "",
		//// 1);
	}

	@Test(description = "To test search bus end to end scenario")
	public void SearchBusEndToEndTest() {
		String url = "https://www.makemytrip.com/bus-tickets";
		String email = "testemail@gmail.com";
		String step = "Load MakeMyTrip Home Page";
		//// CustomLogger.logMessage(null, "Start -- " +
		//// this.getClass().getName(), "", 1);

		//// -------------------------------------------
		driver.get(url);
		verifyText = "MakeMyTrip";
		/// GenericFunctions.WaitTillPageLoad(driver, verifyText);

		step = "Bus search"; // -------------------------------------------
		// ////CustomLogger.startStep(step, "");
		BusTicketBookingPage btp = new BusTicketBookingPage(driver);
		//// CustomLogger.logMessage(null, "Go to Bus search page", "", 1);
		btp.ClickThisWidgetTab("Bus");
		//// CustomLogger.logMessage(null, "Enter bus search related
		//// parameters", "", 1);
		btp.SelectToAndFrom("Pune", "Nagpur");
		//// btp.SelectDateOfJourney(false, "24");
		//// btp.EnterEmail(email);
		//// CustomLogger.logMessage(null, "Initiate bus search", "", 1);
		btp.SubmitSearch();
		//// CustomLogger.logMessage(null, "Select bus search on popup window",
		//// "", 1);
		/// btp.HandleSpecialSearchPopup();
		// ////CustomLogger.endStep(step, "");
		// //-------------------------------------------

		step = "Search in progress"; // -------------------------------------------
		// ////CustomLogger.startStep(step, "");
		bsr = new BusSearchResultPage(driver);
		bsr.verifySearchInitiated();
		// ////CustomLogger.endStep(step, "");
		// //-------------------------------------------

		step = "Select bus"; // -------------------------------------------
		// ////CustomLogger.startStep(step, "");
		bsr.selectThisBus(driver, "Neeta");
		// ////CustomLogger.endStep(step, "");
		// //-------------------------------------------

		step = "Select seat"; // -------------------------------------------
		// ////CustomLogger.startStep(step, "");
		bsr.selectSeat("A2");
		// ////CustomLogger.endStep(step, "");
		// //-------------------------------------------

		step = "Select boarding point"; // -------------------------------------------
		// ////CustomLogger.startStep(step, "");
		bsr.selectBP("Viman");
		// ////CustomLogger.endStep(step, "");
		// //-------------------------------------------

		step = "Verify traveler details page opened"; // -------------------------------------------
		// ////CustomLogger.startStep(step, "");
		bsr.verifyPageHeadingDisplayed();
		// ////CustomLogger.endStep(step, "");
		// //-------------------------------------------

		//// CustomLogger.logMessage(null, "End -- " +
		//// this.getClass().getName(), "", 1);
	}

	// -----------------------------------------------------------

	/*
	 * public void ClickThisPageLink(String tabName) {
	 * driver.findElement(By.linkText(tabName)).click(); }
	 * 
	 * public void ClickThisWidgetTab(String tabName) {
	 * driver.findElement(By.xpath("//div[@class='widget_tabs_info']//span[.='"+
	 * tabName+"']")).click(); // driver.findElement(By.cssSelector(
	 * "#trip_type > label")).click(); //
	 * driver.findElement(By.id("trip_type_oneway")).click(); }
	 * 
	 * public void SelectToAndFrom(String departureCity, String destinationCity)
	 * { driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 * WebElement w1 = driver.findElement(By.xpath(
	 * "//div[@class='carousel-li']/div[2]/div/div[1]/div[2]/span/input"));
	 * Assert.assertTrue(w1.isDisplayed(),
	 * "Verify departure city text box is displayed");
	 * w1.sendKeys(departureCity); try { Thread.sleep(2000); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } w1.sendKeys(Keys.ARROW_DOWN);
	 * w1.sendKeys(Keys.ENTER);
	 * 
	 * 
	 * WebElement w2 =driver.findElement(By.xpath(
	 * "//div[@class='carousel-li']/div[2]/div/div[1]/div[3]/span/input"));
	 * Assert.assertTrue(w2.isDisplayed(),
	 * "Verify departure city text box is displayed");
	 * w2.sendKeys(destinationCity); try { Thread.sleep(2000); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } w2.sendKeys(Keys.ARROW_DOWN);
	 * w2.sendKeys(Keys.ENTER);
	 * 
	 * 
	 * driver.findElement(By.xpath(
	 * "//div[@class='carousel-li']/div[2]/div/div[1]/div[2]/span/input")).
	 * sendKeys("Pune, India"); driver.findElement(By.id("ui-id-172")).click();
	 * 
	 * driver.findElement(By.xpath(
	 * "//div[@class='carousel-li']/div[2]/div/div[1]/div[3]/span/input")).
	 * sendKeys("Nagpur, India");
	 * driver.findElement(By.id("ui-id-212")).click(); }
	 * 
	 * public void SelectDateOfJourney() {
	 * driver.findElement(By.linkText("07Mar'14(Fri)")).click();
	 * driver.findElement(By.linkText("8")).click(); }
	 * 
	 * public void EnterEmail(String email) {
	 * driver.findElement(By.id("email")).sendKeys(email); }
	 * 
	 * public void SubmitSearch() {
	 * driver.findElement(By.name("submit_name")).click(); }
	 * 
	 * public void HandleSpecialSearchPopup() { driver.findElement(By.xpath(
	 * "//div[@id='popBusSearch']//button[normalize-space(.)='Search Bus']"
	 * )).click(); new Actions(driver).doubleClick(driver.findElement(By.xpath(
	 * "//div[@id='content']/div[5]/div[2]/div"))).build().perform();
	 * 
	 * driver.findElement(By.cssSelector("div.ng-scope")).click();
	 * driver.findElement(By.linkText("Select Bus")).click();
	 * driver.findElement(By.cssSelector("a.close_popup")).click(); }
	 * 
	 * public void SelectBusName(String bName, String seatNum) {
	 * //driver.findElement(By.xpath("//div[@class='bus_listing']//p[.='"+bName+
	 * "']")).click(); //driver.findElement(By.xpath(
	 * "//div[@class='bus_listing']/div[5]/div[20]/div[1]/div[3]/p[1]")).click()
	 * ; driver.findElement(By.xpath(
	 * "//div[@class='bus_listing']/div[5]/div[2]/div[1]/a")).click(); try {
	 * Thread.sleep(4000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * driver.findElement(By.id(seatNum)).click();
	 * Assert.assertTrue(driver.findElement(By.cssSelector("a.close_popup")).
	 * isDisplayed() , "Verify if the close popup displayed");
	 * driver.findElement(By.cssSelector("a.close_popup")).click(); try {
	 * Thread.sleep(2000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * public static boolean isAlertPresent(FirefoxDriver driver) { try {
	 * driver.switchTo().alert(); return true; } catch (NoAlertPresentException
	 * e) { return false; } }
	 */
}