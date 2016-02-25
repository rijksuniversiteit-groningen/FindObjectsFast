package nl.rug.blackboard.findObjectsFast.search;

import java.util.List;

public class SearchResult {
    private List<CourseResult> courseResultList;
    private List<CourseResult> organizationResultList;
    private List<UserResult> userResultList;

    public List<CourseResult> getCourseResultList() {
        return courseResultList;
    }

    public void setCourseResultList(List<CourseResult> courseResultList) {
        this.courseResultList = courseResultList;
    }

    public List<CourseResult> getOrganizationResultList() {
        return organizationResultList;
    }

    public void setOrganizationResultList(List<CourseResult> organizationResultList) {
        this.organizationResultList = organizationResultList;
    }

    public List<UserResult> getUserResultList() {
        return userResultList;
    }

    public void setUserResultList(List<UserResult> userResultList) {
        this.userResultList = userResultList;
    }
}
