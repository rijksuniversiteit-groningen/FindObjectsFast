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
		UserResultConverter userResultConverter = new UserResultConverter();
		UserResult result = userResultConverter.apply(u);
		assertNotNull(result);
	}

	@Test
	@Category(IntegrationTest.class)
	public void testDisplayName() {
		User u = new User();
		u.setFamilyName("Simpson");
		u.setGivenName("Bart");
		UserResultConverter userResultConverter = new UserResultConverter();
		UserResult result = userResultConverter.apply(u);
		assertNotNull(result);

		/*
		 * if this fails with "expected:<[Bart Simpson]> but was:<[LOCALE_SETTINGS.SHORT]>"
		 * then either
		 *  1. you did not copy the locale files from the Vagrant image
		 *  2. you did not change bbconfig.base.shared.dir in bb-config.properties
		 *  3. your computer selects a locale that was not copied
		 */
		assertEquals("Bart Simpson", result.getDisplayName());
	}
}
