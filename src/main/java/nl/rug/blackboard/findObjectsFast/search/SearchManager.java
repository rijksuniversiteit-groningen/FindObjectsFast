package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.persist.user.UserSearch;
import com.google.common.collect.ImmutableList;
import nl.rug.blackboard.findObjectsFast.search.query.CourseSearchEx;
import nl.rug.blackboard.findObjectsFast.search.query.UserSearchEx;

import java.util.List;

public class SearchManager {

    public SearchResult search(String searchTerm) {
        try {
            SearchResult result = new SearchResult();

            ImmutableList.Builder<CourseResult> courseListBuilder = ImmutableList.builder();
            ImmutableList.Builder<CourseResult> organizationListBuilder = ImmutableList.builder();

            for(Course course : searchCourses(searchTerm)) {
                CourseResult converted = CourseResultConverter.convert(course);
                if(course.getServiceLevelType() == Course.ServiceLevel.FULL) {
                    courseListBuilder.add(converted);
                } else {
                    organizationListBuilder.add(converted);
                }
            }

            result.setOrganizationResultList(organizationListBuilder.build());
            result.setCourseResultList(courseListBuilder.build());
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
        UserSearch search = new UserSearchEx();
        search.addParameter(new UserSearch.SearchParameter(UserSearch.SearchKey.GivenName, searchTerm, SearchOperator.Contains));
        search.addParameter(new UserSearch.SearchParameter(UserSearch.SearchKey.FamilyName, searchTerm, SearchOperator.Contains));
        search.addParameter(new UserSearch.SearchParameter(UserSearch.SearchKey.Email, searchTerm, SearchOperator.Contains));
        search.addParameter(new UserSearch.SearchParameter(UserSearch.SearchKey.UserName, searchTerm, SearchOperator.Contains));

        List<User> userList = UserDbLoader.Default.getInstance().loadByUserSearch(search);
        ImmutableList.Builder<UserResult> builder = ImmutableList.builder();

        for (User user : userList) {
            builder.add(UserResultConverter.convert(user));
        }

        return builder.build();
    }


}
