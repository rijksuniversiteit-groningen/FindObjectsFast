package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.course.Course;
import blackboard.platform.integration.exchange.CourseXO;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
public class CourseResultConverter {

	public static CourseResult convert(Course course) {
		CourseResult result = new CourseResult();
		result.setCode(course.getCourseId());
		result.setTitle(course.getTitle());
		result.setUrl(getCourseLaunchUrl(course));
		result.setAvailable(course.getIsAvailable());
		result.setCourse(course.getServiceLevelType() == Course.ServiceLevel.FULL);
		return result;
	}

	private static String getCourseLaunchUrl(Course course) {
		CourseXO courseXO = new CourseXO(course);
		return courseXO.getLaunchUrl();
	}
}
