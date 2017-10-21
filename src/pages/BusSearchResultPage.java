package pages;

import java.util.List;
import java.util.logging.Level;

import logger.CustomLogger;

import net.sourceforge.htmlunit.corejs.javascript.ast.ThrowStatement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.WebElementNotFound;

public class BusSearchResultPage extends AbstractPage {

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
        /*// Check that we're on the right page.
        if (!"MakeMyTrip".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the home page");
        }*/
	}

	@Override
	protected void initPageObject() {
		bySearchProgressIcon = By.xpath(LOCATOR_PROPS.getProperty("bussearchresult.searchinprogress.img"));
		byBusInfoDivList = By.xpath(LOCATOR_PROPS.getProperty("bussearchresult.businfo.divlist"));
		bySelectSeatLnk = By.xpath(LOCATOR_PROPS.getProperty("bussearchresult.selectseat.lnk"));
		bySelBPFromSeatMap = By.xpath(LOCATOR_PROPS.getProperty("bussearchresult.selBPFromSeatMap.list"));
		byTravelerDetailsHeading = By.xpath(LOCATOR_PROPS.getProperty("travelerdetails.heading"));
		bySeatList = By.xpath(LOCATOR_PROPS.getProperty("bussearchresult.seatlist"));
	}
	
	public BusSearchResultPage verifySearchInitiated() {
		CustomLogger.logMethodStart();
		CustomLogger.logMessage(null, "Verify search progress", "", 1);
		Assert.assertTrue(genFun.verifyElementPresent(bySearchProgressIcon), "Verify search progress icon displayed");
		CustomLogger.logMethodEnd();
		return this;
	}

	public BusSearchResultPage selectThisBus(WebDriver wd,  String busName) {
		/*String busDivXPath = "//div[@class='listing_section shadow_genrator_1 clearFix append_bottomHalf ng-scope']";
		String selectBtnPath = "//div[@class='clearFix']/a['flL button']";
		//List ls = wd.findElements(By.cssSelector("div.listing_section div.clearFix"));
		
		List ls = wd.findElements(By.xpath(busDivXPath));
		for(int i=0;i<ls.size();i++) {
			WebElement wb = (WebElement) ls.get(i);
			if(wb.findElement(By.cssSelector("div.flL p.traveler_name")).getText().trim().contains(busName)) {
				System.out.println(wb.getText());
				wd.findElement(By.xpath(busDivXPath+"["+i+"]"+selectBtnPath)).click();
				break;
			}
		}*/
		
		CustomLogger.logMethodStart();
		boolean found = false;
		List ls = wd.findElements(byBusInfoDivList);
		for(int i=0;i<ls.size();i++) {
			WebElement wb = (WebElement) ls.get(i);
			if(wb.findElement(By.cssSelector("div.flL p.traveler_name")).getText().trim().contains(busName)) {
				System.out.println(wb.getText());
				CustomLogger.logMessage(null, "Find bus in search result", "Bus found...", 3);
				CustomLogger.logMessage(null, "Select Bus", "Selecting bus...",3);
				String selectBtn = LOCATOR_PROPS.getProperty("bussearchresult.businfo.divlist")+"["+(i+1)+"]"+LOCATOR_PROPS.getProperty("bussearchresult.selectbus.btn");
				WebElement wbBtn = genFun.waitForElement(By.xpath(selectBtn));
				CustomLogger.logMessage(null, "Select Bus", "Bus selected",3);
				wbBtn.click();
				found = true;
				break;
			}
		}
		if (!found)
			CustomLogger.logMessage(Level.SEVERE, "Select bus "+busName, "Could not find bus on search result page.", 1);
		else
			CustomLogger.logMessage(null, "Select bus "+busName, "Bus selected successfully.", 1);
		CustomLogger.logMethodEnd();
		return this;
	}
	
	public BusSearchResultPage selectSeat(String seatNum) {
		CustomLogger.logMethodStart();
		String seat = "//a[@id='seat"+seatNum+"']";
		selectSeatLnk = genFun.waitForElement(bySelectSeatLnk);		
		WebElement wbSeat = null;
		try {
			wbSeat = genFun.waitForElement(By.xpath(seat));
			wbSeat.click();
		} catch (Exception e) {
			Assert.assertTrue(false, "Verify seat number ["+seatNum+"]");
			//CustomLogger.logMessage(Level.WARNING,"Verify seat number","Invalid seat number ["+seatNum+ "]",1);
			//throw new WebElementNotFound("Web element ["+seat+ "] displayed");
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
		/*selBPFromSeatMap = genFun.waitForElement(bySelBPFromSeatMap);
		selBPFromSeatMap.sendKeys(bp);
		Assert.assertTrue(selBPFromSeatMap.getAttribute("value").equals(bp), "Verify given value ["+bp+"] is selected.");*/
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
