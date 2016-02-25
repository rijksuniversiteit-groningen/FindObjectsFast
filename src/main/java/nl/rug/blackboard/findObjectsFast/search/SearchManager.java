package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.persist.PersistenceException;
import blackboard.persist.PersistenceRuntimeException;
import blackboard.persist.SearchOperator;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.persist.user.UserSearch;
import blackboard.platform.integration.exchange.CourseXO;
import blackboard.platform.integration.launch.LaunchHandler;
import blackboard.platform.user.UserManagerUtil;
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
                if(course.getServiceLevelType() == Course.ServiceLevel.FULL) {
                    courseListBuilder.add(convertCourseToResult(course));
                } else {
                    organizationListBuilder.add(convertCourseToResult(course));
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
            builder.add(convertUserToResult(user));
        }

        return builder.build();
    }

    private UserResult convertUserToResult(User user) {
        UserResult result = new UserResult();
        result.setUsername(user.getUserName());
        result.setDisplayName(UserManagerUtil.getDisplayName(user));
        result.setEmail(user.getEmailAddress());
        LaunchHandler launchHandler = new LaunchHandler(LaunchHandler.Type.UserAdminModify, user.getId(), "");
        result.setUrl(launchHandler.getLaunchUrl());
        result.setCourseEnrollmentsUrl(getUserCourseEnrollmentsUrl(user));
        result.setOrganizationEnrollmentsUrl(getUserOrganizationEnrollmentsUrl(user));
        result.setAvailable(user.getIsAvailable());
        return result;
    }

    private CourseResult convertCourseToResult(Course course) {
        CourseResult result = new CourseResult();
        result.setCode(course.getCourseId());
        result.setTitle(course.getTitle());
        result.setUrl(getCourseLaunchUrl(course));
        result.setAvailable(course.getIsAvailable());
        result.setCourse(course.getServiceLevelType() == Course.ServiceLevel.FULL);
        return result;
    }

    private String getCourseLaunchUrl(Course course) {
        CourseXO courseXO = new CourseXO(course);
        return courseXO.getLaunchUrl();
    }

    private String getUserCourseEnrollmentsUrl(User user) {
        return "/webapps/blackboard/execute/userEnrollment?nav_item=list_courses_by_user&group_type=Course&user_id="
                + user.getId().toExternalString();
    }

    private String getUserOrganizationEnrollmentsUrl(User user) {
        return "/webapps/blackboard/execute/userEnrollment?nav_item=list_orgs_by_user&group_type=Organization&user_id="
                + user.getId().toExternalString();
    }
}
