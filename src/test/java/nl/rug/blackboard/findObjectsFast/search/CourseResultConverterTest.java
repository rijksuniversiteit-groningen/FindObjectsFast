package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.course.Course;
import nl.rug.junit.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertNotNull;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
public class CourseResultConverterTest {

	@Test
	@Category(UnitTest.class)
	public void testEmptyCourse() {
		Course course = new Course();
		CourseResult converted = CourseResultConverter.convert(course);
		assertNotNull(converted);
		assertNotNull(converted.getTitle());
	}

	@Test
	public void testCourseCode() {
		/*
		 * write a test that checks if CourseResult.getCode()
		 * returns valid values after conversion
		 */
	}

	@Test
	public void testCourseUrl() {
		/*
		 * write a test that checks if CourseResult.getUrl()
		 * returns valid values after conversion
		 */
	}
}
