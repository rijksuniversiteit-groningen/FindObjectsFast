package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.user.User;
import nl.rug.junit.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
public class UserResultConverterTest {

	@Test
	@Category(IntegrationTest.class)
	public void testEmptyUser() {
		User u = new User();
		UserResult result = UserResultConverter.convert(u);
		assertNotNull(result);
	}

	@Test
	@Category(IntegrationTest.class)
	public void testDisplayName() {
		User u = new User();
		u.setFamilyName("Simpson");
		u.setGivenName("Bart");
		UserResult result = UserResultConverter.convert(u);
		assertEquals("Bart Simpson", result.getDisplayName());
	}
}
