package nl.rug.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
public class BlackboardClientFactory {
	public static final int TIMEOUT_SECONDS = 5;
	public static final String BASE_URL = "http://localhost:3380";
	public static final String TEST_USERNAME = "administrator";
	public static final String TEST_PASSWORD = "password";


	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>() {
		@Override protected WebDriver initialValue() {
			System.err.println("getting");
			WebDriver result = new ChromeDriver();
			result.manage().timeouts().implicitlyWait(TIMEOUT_SECONDS, TimeUnit.SECONDS);
			result.get(BASE_URL + "/");
			result.findElement(By.id("user_id")).clear();
			result.findElement(By.id("user_id")).sendKeys(TEST_USERNAME);
			result.findElement(By.id("password")).clear();
			result.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
			result.findElement(By.id("entry-login")).click();
			return result;
		}
	};

	public static WebDriver get() { return DRIVER.get(); }
}
