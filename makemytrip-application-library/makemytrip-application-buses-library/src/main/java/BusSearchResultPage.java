
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BusSearchResultPage extends AbstractPage implements ElementLocators {

	private static final String OBJ_LOCATOR_FILE = "locators.properties";
	private WebElement searchProgressIcon;
	private WebElement selectSeatLnk;
	private WebElement seatList;
	private WebElement selBPFromSeatMap;
	private WebElement travelerDetailsHeading;

	static By bySearchProgressIcon;
	static By byBusInfoDivList;
	static By bySelectBusBtn;
	static By bySelectSeatLnk;
	static By bySeatList;
	static By bySelBPFromSeatMap;
	static By byTravelerDetailsHeading;

	public BusSearchResultPage(WebDriver DRIVER) {
		super(DRIVER, OBJ_LOCATOR_FILE);
		initPageObject();
	}

	@Override
	protected void initPageObject() {
		bySearchProgressIcon = By.xpath(BUSSEARCHRESULT_PROGRESS_IMG_XPATH);
		byBusInfoDivList = By.xpath(BUSSEARCHRESULT_PROGRESS_BUSINFO_DIV_XPATH);
		bySelectSeatLnk = By.xpath(BUSSEARCHRESULT_SELECTSEAT_LNK_XPATH);
		bySelBPFromSeatMap = By.xpath(BUSSEARCHRESULT_SELECTBPFROMSEATMAP_LIST_XPATH);
		byTravelerDetailsHeading = By.xpath(TRAVELDETAILS_HEADING_XPATH);
		bySeatList = By.xpath(BUSSEARCHRESULT_SEATLIST_XPATH);
	}

	public BusSearchResultPage verifySearchInitiated() {
		CustomLogger.logMethodStart();
		CustomLogger.logMessage(null, "Verify search progress", "", 1);
		DRIVER.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		genFun.verifyElementPresent(bySearchProgressIcon);
		DRIVER.manage().timeouts().implicitlyWait(SystemWaits.PAGE_WAIT, TimeUnit.SECONDS);
		CustomLogger.logMethodEnd();
		Assert.assertTrue(genFun.verifyElementPresent(By.id("mfs_search")), "verify Search page loaded");

		return this;
	}

	public BusSearchResultPage selectThisBus(WebDriver wd, String busName) {

		CustomLogger.logMethodStart();
		boolean found = false;
		List ls = wd.findElements(byBusInfoDivList);
		for (int i = 0; i < ls.size(); i++) {
			WebElement wb = (WebElement) ls.get(i);
			if (wb.findElement(By.cssSelector("div.flL p.traveler_name")).getText().trim().contains(busName)) {
				System.out.println(wb.getText());
				CustomLogger.logMessage(null, "Find bus in search result", "Bus found...", 3);
				CustomLogger.logMessage(null, "Select Bus", "Selecting bus...", 3);
				String selectBtn = BUSSEARCHRESULT_PROGRESS_BUSINFO_DIVLIST_XPATH + "[" + (i + 1) + "]"
						+ BUSSEARCHRESULT_SELECTBUS_BTN_XPATH;
				WebElement wbBtn = genFun.waitForElement(By.xpath(selectBtn));
				CustomLogger.logMessage(null, "Select Bus", "Bus selected", 3);
				wbBtn.click();
				found = true;
				break;
			}

		}
		if (!found)
			CustomLogger.logMessage(Level.SEVERE, "Select bus " + busName, "Could not find bus on search result page.",
					1);
		else
			CustomLogger.logMessage(null, "Select bus " + busName, "Bus selected successfully.", 1);
		CustomLogger.logMethodEnd();
		return this;
	}

	public BusSearchResultPage selectSeat(String seatNum) {
		CustomLogger.logMethodStart();
		String seat = "//a[@id='seat" + seatNum + "']";
		selectSeatLnk = genFun.waitForElement(bySelectSeatLnk);
		WebElement wbSeat = null;
		try {
			wbSeat = genFun.waitForElement(By.xpath(seat));
			wbSeat.click();
		} catch (Exception e) {
			Assert.assertTrue(false, "Verify seat number [" + seatNum + "]");
			// CustomLogger.logMessage(Level.WARNING,"Verify seat
			// number","Invalid seat number ["+seatNum+ "]",1);
			// throw new WebElementNotFound("Web element ["+seat+ "]
			// displayed");
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		seatList = genFun.waitForElement(bySeatList);
		CustomLogger.logMessage(null, "Verify seat selected", "Verifying selected seat displayed in seat list", 3);
		Assert.assertTrue(seatList.getText().contains(seatNum), "Verify seat selected and displayed in seat list");
		CustomLogger.logMethodEnd();
		return this;
	}

	public BusSearchResultPage selectBP(String bp) {
		CustomLogger.logMethodStart();
		/*
		 * selBPFromSeatMap = genFun.waitForElement(bySelBPFromSeatMap);
		 * selBPFromSeatMap.sendKeys(bp);
		 * Assert.assertTrue(selBPFromSeatMap.getAttribute("value").equals(bp),
		 * "Verify given value ["+bp+"] is selected.");
		 */
		genFun.selectValue(bySelBPFromSeatMap, bp);
		CustomLogger.logMethodEnd();
		return this;
	}

	public BusSearchResultPage verifyPageHeadingDisplayed() {
		CustomLogger.logMethodStart();
		CustomLogger.logMessage(null, "Verify page heading displayed", "", 1);
		Assert.assertTrue(genFun.verifyElementPresent(byTravelerDetailsHeading), "Verify page heading displayed");
		CustomLogger.logMethodEnd();
		return this;
	}

}
