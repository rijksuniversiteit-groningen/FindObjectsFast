package nl.rug.blackboard.findObjectsFast.search;

import blackboard.data.user.User;
import blackboard.platform.integration.launch.LaunchHandler;
import blackboard.platform.user.UserManagerUtil;


/**
 * @author <a href="p.r.fokkinga [at] rug.nl">Peter Fokkinga</a>
 */
public class UserResultConverter {

	public static UserResult convert(User user) {
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

	private static String getUserCourseEnrollmentsUrl(User user) {
		return "/webapps/blackboard/execute/userEnrollment?nav_item=list_courses_by_user&group_type=Course&user_id="
				+ user.getId().toExternalString();
	}

	private static String getUserOrganizationEnrollmentsUrl(User user) {
		return "/webapps/blackboard/execute/userEnrollment?nav_item=list_orgs_by_user&group_type=Organization&user_id="
				+ user.getId().toExternalString();
	}
}
