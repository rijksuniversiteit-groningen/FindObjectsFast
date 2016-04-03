package nl.rug.selenium;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( {
		SearchUsersTest.class
})
public class TestSuite {
	@AfterClass
	public static void closeBrowser() {
		BlackboardClientFactory.get().quit();
	}
}
