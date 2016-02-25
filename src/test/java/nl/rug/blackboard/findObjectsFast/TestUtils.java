package nl.rug.blackboard.findObjectsFast;

import blackboard.data.course.Course;
import blackboard.persist.PersistenceException;
import blackboard.persist.SearchOperator;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseSearch;
import blackboard.persist.impl.PagedCTEUnmarshallSelectQuery;
import nl.rug.blackboard.findObjectsFast.search.SearchManager;
import nl.rug.blackboard.findObjectsFast.search.SearchResult;
import nl.rug.blackboard.findObjectsFast.search.query.CourseSearchEx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TestUtils {
    @Test
    public void run() {
        SearchManager searchManager = new SearchManager();
        SearchResult searchResult = searchManager.search("jas");
        System.out.println(searchResult.getCourseResultList().size());
        assertEquals("Jasper's testcursus", searchResult.getCourseResultList().get(0).getTitle());
    }

    @Test
    public void run2() {
        SearchManager searchManager = new SearchManager();
        SearchResult searchResult = searchManager.search("jasper");
        System.out.println(searchResult.getCourseResultList().size());
        assertEquals("Jasper's testcursus", searchResult.getCourseResultList().get(0).getTitle());
    }

    @Test
    public void run3() {
        CourseSearch search = new CourseSearch();
        search.addCourseRowStatusParameter();

        String searchTerm = "jas";
        CourseSearch.SearchParameter searchName
                = new CourseSearch.SearchParameter(CourseSearch.SearchKey.CourseName, searchTerm,
                SearchOperator.Contains);
        search.addSearchParameter(searchName);

        CourseSearch.SearchParameter searchId
                = new CourseSearch.SearchParameter(CourseSearch.SearchKey.CourseId, searchTerm,
                SearchOperator.StartsWith);
        search.addSearchParameter(searchId);

        try {
            CourseDbLoader.Default.getInstance().loadByCourseSearch(search);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        PagedCTEUnmarshallSelectQuery query = (PagedCTEUnmarshallSelectQuery)search.getQuery();
        System.out.println(query.generateCteClause());
    }


    @Test
    public void run4() throws Exception {
        List<Course> courseList = CourseDbLoader.Default.getInstance().loadByCourseSearch(new CourseSearchEx("exam"));
        for(Course course : courseList) {
            System.out.println(course.getTitle());
        }
        System.out.println(courseList.size());
    }
}
