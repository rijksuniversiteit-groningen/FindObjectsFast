package nl.rug.blackboard.findObjectsFast.search;

public class UserResult {
    private String username;
    private String displayName;
    private String email;
    private String url;
    private String courseEnrollmentsUrl;
    private String organizationEnrollmentsUrl;
    private boolean available;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCourseEnrollmentsUrl() {
        return courseEnrollmentsUrl;
    }

    public void setCourseEnrollmentsUrl(String courseEnrollmentsUrl) {
        this.courseEnrollmentsUrl = courseEnrollmentsUrl;
    }

    public String getOrganizationEnrollmentsUrl() {
        return organizationEnrollmentsUrl;
    }

    public void setOrganizationEnrollmentsUrl(String organizationEnrollmentsUrl) {
        this.organizationEnrollmentsUrl = organizationEnrollmentsUrl;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
