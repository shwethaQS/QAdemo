package AuTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseClass {

	static WebDriver driver;
	static Properties prop = new Properties();
	static Logger log;

	@BeforeClass
	public static void setupApplication() throws InterruptedException, IOException {
		FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "\\dataFile.properties");
		prop.load(input);
		log = Logger.getLogger("testlogs");
		String driverpro = prop.getProperty("driverpro");
		String chromeloc = prop.getProperty("chromeloc");
		String url = prop.getProperty("Appurl");
		String usrname = prop.getProperty("username");
		String paswrd = prop.getProperty("password");
		log.debug("Browser session started!");
		System.setProperty(driverpro, chromeloc);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		username.sendKeys(usrname);
		password.sendKeys(paswrd);
		driver.findElement(By.className("btn-text")).click();

	}

	@AfterClass
	public static void closeApplication() throws InterruptedException {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[starts-with(@class,'ce-user-menu')]"))).click();
		//driver.findElement(By.xpath("//button[contains(text(),'Logout')]")).click();
		driver.quit();
		log.debug("Browser session ended");
	}

}
