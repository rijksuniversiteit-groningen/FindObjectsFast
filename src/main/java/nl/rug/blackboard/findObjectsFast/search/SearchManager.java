package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.persist.PersistenceException;
import blackboard.persist.PersistenceRuntimeException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.persist.user.UserSearch;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import nl.rug.blackboard.findObjectsFast.search.query.CourseSearchEx;
import nl.rug.blackboard.findObjectsFast.search.query.UserSearchEx;

import java.util.List;

import static com.google.common.base.Predicates.not;

public class SearchManager {

    public SearchResult search(String searchTerm) {
        try {
            SearchResult result = new SearchResult();
            List<Course> allCourses = searchCourses(searchTerm);
            Predicate<Course> isCourse = new IsCoursePredicate();
            CourseResultConverter toCourseResult = new CourseResultConverter();
            result.setCourseResultList(
                    FluentIterable.from(allCourses).filter(isCourse).transform(toCourseResult).toList());
            result.setOrganizationResultList(
                    FluentIterable.from(allCourses).filter(not(isCourse)).transform(toCourseResult).toList());

            result.setUserResultList(searchUsers(searchTerm));
            return result;
        } catch (PersistenceException e) {
            throw new PersistenceRuntimeException(e);
        }
    }

    private List<Course> searchCourses(String searchTerm) throws PersistenceException {
        return CourseDbLoader.Default.getInstance().loadByCourseSearch(new CourseSearchEx(searchTerm));
    }

    private List<UserResult> searchUsers(String searchTerm) throws PersistenceException {
        UserSearch search = new UserSearchEx(searchTerm);
        List<User> userList = UserDbLoader.Default.getInstance().loadByUserSearch(search);
        return Lists.transform(userList, new UserResultConverter());
    }

    private class IsCoursePredicate implements Predicate<Course> {
        @Override
        public boolean apply(Course course) {
            return course.getServiceLevelType() == Course.ServiceLevel.FULL;
        }
    }

}
