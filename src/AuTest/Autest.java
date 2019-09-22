package AuTest;

import java.util.ArrayList;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Autest extends BaseClass {

	/* Testcase to check visibility of the Company logo */
	@Test
	public void Logo() {
		String logo = prop.getProperty("logo");
		WebElement companylogo = driver.findElement(By.xpath(logo));
		companylogo.isDisplayed();
		log.debug("Company logo showing");

	}

	/* Testcase to navigate to help document in a new tab */
	/* Switch back to home page */
	@Test
	public void YourHelpDocument() throws InterruptedException {

		String help = prop.getProperty("help");
		String searchhelp = prop.getProperty("searchhelp");
		String keysrch = prop.getProperty("keysrch");
		String gohelp = prop.getProperty("gohelp");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String Hometab = driver.getWindowHandle();

		Actions actions = new Actions(driver);
		WebElement helplnk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(help)));
		actions.moveToElement(helplnk).perform();
		helplnk.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchhelp))).sendKeys(keysrch);
		driver.findElement(By.xpath(gohelp)).click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(Hometab);
		try {
			driver.switchTo().window(newTab.get(0));
			log.debug("Help doc available" + driver.getTitle());
		} catch (NoSuchWindowException e) {
			log.debug("An exception to get help window");
		}
		driver.switchTo().window(Hometab);
		log.debug("Back home"+driver.switchTo().window(Hometab).getTitle());
		actions.sendKeys(Keys.ESCAPE).build().perform();

	}

}
