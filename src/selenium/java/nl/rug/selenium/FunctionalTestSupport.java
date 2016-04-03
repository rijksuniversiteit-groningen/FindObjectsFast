package nl.rug.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.fail;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
abstract class FunctionalTestSupport {

	protected WebDriver driver;
	protected String baseUrl;
	protected StringBuffer verificationErrors = new StringBuffer();


	@Before
	public void setupDriver() {
		driver = BlackboardClientFactory.get();
		baseUrl = BlackboardClientFactory.BASE_URL;
	}


	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
		verificationErrors.setLength(0);
	}
}
