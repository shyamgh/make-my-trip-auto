package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class DCCUITestClass extends InitTestClass{

    FirefoxDriver wd;
    
    public DCCUITestClass() {
		super();
	}
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
    }
    
    public void bacLogin(WebDriver wd) {
    	wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys("sseadmin");
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys("sseadm1n");
        wd.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        if (!(wd.findElements(By.linkText("Devices")).size() != 0)) {
            System.out.println("verifyElementPresent failed");
        }
    }
    
    public void searchDevice(WebDriver wd, String devID) {
    	
    }
    @Test
    public void DCCUITestClass() {
    	genFunct.OpenThisUrl(wd, genFunct.GetSystemParameter("bac.url"));
    	//wd.get("http://10.90.252.220/adminui/ChLogin.jsp");
    	bacLogin(wd);
        
    	wd.findElement(By.linkText("Devices")).click();
        wd.findElement(By.cssSelector("html")).click();
        wd.findElement(By.cssSelector("html")).click();
        wd.findElement(By.name("SearchQuery")).click();
        wd.findElement(By.name("SearchQuery")).clear();
        wd.findElement(By.name("SearchQuery")).sendKeys("088039-0022081402");
        wd.findElement(By.name("action")).click();
        wd.findElement(By.name("action")).click();
        wd.findElement(By.xpath("//form/table[3]/tbody/tr[4]/th[2]")).click();
        wd.findElement(By.xpath("//form/table[2]/tbody/tr[1]/td/input[1]")).click();
        wd.findElement(By.name("deviceIdentifier")).click();
        wd.findElement(By.name("deviceIdentifier")).clear();
        wd.findElement(By.name("deviceIdentifier")).sendKeys("088039-0022081402");
        wd.findElement(By.name("ownerId")).click();
        wd.findElement(By.name("ownerId")).clear();
        wd.findElement(By.name("ownerId")).sendKeys("1234567");
        if (!wd.findElement(By.xpath("//form/table[1]/tbody/tr[17]/td[2]/select//option[15]")).isSelected()) {
            wd.findElement(By.xpath("//form/table[1]/tbody/tr[17]/td[2]/select//option[15]")).click();
        }
        wd.findElement(By.xpath("//form/table[2]/tbody/tr[10]/td/input[1]")).click();
        wd.findElement(By.name("list2")).click();
        wd.findElement(By.name("SearchQuery")).click();
        wd.findElement(By.name("SearchQuery")).clear();
        wd.findElement(By.name("SearchQuery")).sendKeys("088039-0022081402");
        wd.findElement(By.name("action")).click();
        if (!(wd.findElements(By.linkText("088039-0022081402")).size() != 0)) {
            System.out.println("verifyElementPresent failed");
        }
        wd.findElement(By.linkText("088039-0022081402")).click();
        wd.findElement(By.cssSelector("a.resultsBView > img")).click();
        wd.findElement(By.cssSelector("a.resultsBView > img")).click();
        wd.findElement(By.cssSelector("a.resultsBView > img")).click();
        wd.findElement(By.xpath("//table[4]/tbody/tr[9]/td[2]")).click();
        if (!(wd.findElements(By.linkText("baseline")).size() != 0)) {
            System.out.println("verifyElementPresent failed");
        }
        if (!(wd.findElements(By.xpath("//table[4]/tbody/tr[52]/td[2]")).size() != 0)) {
            System.out.println("verifyElementPresent failed");
        }
        if (!(wd.findElements(By.xpath("//table[4]/tbody/tr[76]/td[2]")).size() != 0)) {
            System.out.println("verifyElementPresent failed");
        }
        wd.findElement(By.cssSelector("body")).click();
        wd.findElement(By.cssSelector("body")).click();
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
