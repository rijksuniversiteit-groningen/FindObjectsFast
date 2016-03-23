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
    private final CourseResultConverter toCourseResult = new CourseResultConverter();
    private final UserResultConverter toUserResult = new UserResultConverter();
    private final Predicate<CourseResult> isCourse = new IsCoursePredicate();

    public SearchResult search(String searchTerm) {
        try {
            FluentIterable<CourseResult> allCourses =
                    FluentIterable.from(searchCourses(searchTerm))
                    .transform(toCourseResult);

            SearchResult result = new SearchResult();
            result.setCourseResultList(allCourses.filter(isCourse).toList());
            result.setOrganizationResultList(allCourses.filter(not(isCourse)).toList());
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
        return Lists.transform(userList, toUserResult);
    }

    private class IsCoursePredicate implements Predicate<CourseResult> {
        @Override
        public boolean apply(CourseResult course) {
            return course.isCourse();
        }
    }

}
