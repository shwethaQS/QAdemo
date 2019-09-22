package SmTest;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Smtest extends BaseClass {

	/*
	 * Navigate to the workspace and validate process explorer Testcase to check
	 * newly implemented share your selections functionality
	 */

	@Test
	public void testAworkspaceslist() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String KPI = prop.getProperty("KPI");
		String firstKPI = prop.getProperty("Firstkpi");
		String intotile = prop.getProperty("intotile");
		String ProcessEx = prop.getProperty("ProcessEx");
		String share = prop.getProperty("share");
		String sharetitle = prop.getProperty("sharetitle");
		String sharelnk = prop.getProperty("sharelnk");
		String copy = prop.getProperty("copy");
		String copied = prop.getProperty("copied");
		String home = prop.getProperty("home");
		try {
			List<WebElement> allKPIs = driver.findElements(By.className(KPI));
			log.debug("Number of workspaces:" + allKPIs.size());
			for (int i = 0; i < allKPIs.size(); i++) {
				log.debug("Workspace" + i + ":" + allKPIs.get(i).getText());
			}
			assertEquals(firstKPI, allKPIs.get(0).getText());
			WebElement firstworkpace = allKPIs.get(0);

			firstworkpace.click();
		} catch (WebDriverException e) {
			log.debug("Exceptional case");
		}

		WebElement clicktile = driver.findElement(By.xpath(intotile));

		clicktile.click();

		if (driver.findElement((By.xpath(ProcessEx))).isDisplayed()) {
			log.debug("Process Explorer showing");
		}

		try {
			driver.findElement(By.xpath(share)).click();

			if (driver.findElement(By.xpath(sharetitle)).isDisplayed()) {
				driver.findElement(By.xpath(sharelnk)).getText().startsWith("http");
				driver.findElement(By.xpath(copy)).click();
				driver.findElement(By.xpath(copied)).isDisplayed();
				log.debug("share your selections is good!");
			}
		} catch (NoSuchElementException e) {
		}
		driver.findElement(By.xpath(home)).click();
	}

}
