package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.ValidationException;
import blackboard.data.course.Course;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbPersister;
import nl.rug.junit.IntegrationTest;
import org.junit.*;
import org.junit.experimental.categories.Category;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
public class SearchManagerTest {
	private static Course courseOne;

	@BeforeClass
	public static void createTestData() throws PersistenceException, ValidationException {
		courseOne = new Course();
		courseOne.setCourseId("junit.course1");
		courseOne.setTitle("TLC 2016");
		CourseDbPersister.Default.getInstance().persist(courseOne);
	}


	@AfterClass
	public static void removeTestData() throws PersistenceException, ValidationException {
		CourseDbPersister.Default.getInstance().deleteById(courseOne.getId());
	}


	@Test
	@Category(IntegrationTest.class)
	public void testCourseTitleSearch() {
		// todo: write a test that checks whether SearchManager
		// searches case-insensitive
	}
}
