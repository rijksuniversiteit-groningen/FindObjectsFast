package nl.rug.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
public class SearchUsersTest extends FunctionalTestSupport {
	@Test
	public void testSearchForAdmin() throws Exception {
		driver.get(baseUrl + "/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_1_1");
		driver.findElement(By.id("fof_searchTerm")).clear();
		driver.findElement(By.id("fof_searchTerm")).sendKeys("admin");
		try {
			WebElement searchResults = driver.findElement(By.cssSelector("div#fof_searchResults"));
			assertTrue(searchResults.findElements(By.tagName("li")).size() > 0);
			WebElement li = searchResults.findElement(By.cssSelector("li[title=\"administrator\"] > div.fof_courseCode > span > span"));
			assertNotNull(li);
			assertEquals("administrator (dev@example.com)",li.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void testSearchForXXXX() throws Exception {
		driver.get(baseUrl + "/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_1_1");
		driver.findElement(By.id("fof_searchTerm")).clear();
		driver.findElement(By.id("fof_searchTerm")).sendKeys("XXXX");
		try {
			WebElement searchResults = driver.findElement(By.cssSelector("div#fof_searchResults"));
			assertFalse(searchResults.findElements(By.tagName("li")).size() > 0);
			assertTrue(searchResults.findElements(By.tagName("li")).size() > 0);
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
