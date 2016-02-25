package nl.rug.blackboard.findObjectsFast.search.query;

import blackboard.persist.SearchOperator;
import blackboard.persist.course.CourseSearch;
import blackboard.persist.course.impl.CourseDbMap;
import blackboard.persist.impl.AbstractSearchQuery;
import blackboard.persist.impl.DbBbObjectMapUnmarshaller;
import blackboard.persist.impl.DbUnmarshaller;
import blackboard.persist.impl.PagedUnmarshallSelectQuery;
import blackboard.persist.impl.mapping.DbObjectMap;

public class CourseSearchEx extends CourseSearch {
    private final String searchTerm;


    public CourseSearchEx(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public PagedUnmarshallSelectQuery getQuery() {
        CourseQueryEx query = new CourseQueryEx(searchTerm, CourseDbMap.MAP);
        query.setPageSize(40);
        query.setUsePaging(true);
        return query;
    }

    public class CourseQueryEx extends AbstractSearchQuery {
        private final String searchTerm;
        protected final DbObjectMap dbObjectMap;

        public CourseQueryEx(String searchTerm, DbObjectMap dbObjectMap) {
            this.searchTerm = searchTerm;
            this.dbObjectMap = dbObjectMap;
        }

        @Override
        protected String generateOrderByClause() {
            return "order by cm.course_name";
        }

        @Override
        protected String generateWhereClause() {
            StringBuilder sql = new StringBuilder();
            sql.append(" where ");
            appendCaseInsensitiveLike(sql, "cm.course_id", searchTerm, SearchOperator.Like);
            sql.append(" or ");
            appendCaseInsensitiveLike(sql, "cm.course_name", searchTerm, SearchOperator.Like);
            return sql.toString();
        }

        @Override
        protected String generateSelectColumns() {
            StringBuilder sql = new StringBuilder();
            sql.append("select ").append(this.dbObjectMap.getSelectColumnListSql("cm"));
            return sql.toString();        }

        @Override
        protected String generateFromClause() {
            return " from course_main cm";
        }

        @Override
        protected DbUnmarshaller createUnmarshaller() {
            return new DbBbObjectMapUnmarshaller(this.dbObjectMap, "cm");
        }

    }
}
