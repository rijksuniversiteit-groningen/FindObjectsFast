package nl.rug.blackboard.findObjectsFast.search.query;

import blackboard.data.role.RoleUtil;
import blackboard.persist.Id;
import blackboard.persist.user.UserSearch;
import blackboard.platform.context.Context;
import blackboard.platform.context.ContextManagerFactory;

public class UserSearchEx extends UserSearch {

    public UserSearchEx() {
        Context ctx = ContextManagerFactory.getInstance().getContext();
        this._query = new UserSearchEx.UserSearchQueryEx(ctx.getUserId());
        this._query.setUsePaging(true);
        this._query.setPageSize(40);
        this._query.setSort("userLastName");
        if(Id.isValid(ctx.getUserId())) {
            this.setAdminSearching(RoleUtil.isUserSystemAdmin(ctx.getUser()));
        }
    }

    public class UserSearchQueryEx extends UserSearchQuery {
        public UserSearchQueryEx(Id userId) {
            super(userId);
        }

        @Override
        public String generateWhereClause() {
            StringBuilder sql = new StringBuilder();
            if(this.getSort().equals("dataSourceKey")) {
                sql.append(this.getJoinDataSourceClause());
            }

            Id courseId = ContextManagerFactory.getInstance().getContext().getCourseId();
            if(courseId.equals(Id.UNSET_ID)) {
                sql.append(" where ");
            } else {
                if(UserSearchEx.this.getEnrollSearch()) {
                    sql.append(" where u.pk1 NOT IN  ( select users_pk1 from course_users cu where cu.crsmain_pk1 = ?  ) and ");
                } else if(UserSearchEx.this.getCourseMembershipSearch()) {
                    sql.append(" where u.pk1 IN  ( select users_pk1 from course_users cu where cu.crsmain_pk1 = ?  ) and ");
                } else {
                    sql.append(", course_users cu where cu.users_pk1 = u.pk1 and cu.crsmain_pk1 = ? and cu.row_status = 0 and ");
                }

                this.addParameter(courseId);
            }

            for(int paramIndex = 0; paramIndex < UserSearchEx.this._params.size(); paramIndex++) {
                SearchParameter param = UserSearchEx.this._params.get(paramIndex);

                switch(param.getSearchKey()) {
                    case GivenName:
                        this.buildWhereLikeClause(sql, "firstname", param);
                        break;
                    case FamilyName:
                        this.buildWhereLikeClause(sql, "lastname", param);
                        break;
                    case UserName:
                        this.buildWhereLikeClause(sql, "user_id", param);
                        break;
                    case BatchUid:
                        this.buildWhereLikeClause(sql, "batch_uid", param);
                        break;
                    case StudentId:
                        this.buildWhereLikeClause(sql, "student_id", param);
                        break;
                    case Email:
                        this.buildWhereLikeClause(sql, "email", param);
                        break;
                    case LastLoginDate:
                        this.buildDateClause(sql, param);
                        break;
                    case CourseEnrollmentCount:
                        this.buildCountEnrollmentClause(sql, true, param);
                        break;
                    case OrgEnrollmentCount:
                        this.buildCountEnrollmentClause(sql, false, param);
                        break;
                    case SystemRoles:
                        this.buildSystemRoleClause(sql, param);
                        break;
                    case Entitlement:
                        throw new RuntimeException("Entitlement is not supported");

//                        if(UserSearchEx.this._checkSysRoleEntitlement) {
//                            this.buildEntitlementClause(sql, param);
//                        }
                    case Activity:
                        this.buildActivityClause(sql, param);
                        break;
                    case DataSourceKey:
                        this.buildDataSourceClause(sql, param);
                        break;
                    case Node:
                        throw new RuntimeException("Node search key is not supported");
                        //this.buildNodeFiltrationClause(sql, param);
                    case EntitlementFilter:
                        this.buildEntitlementFilterClause(sql, param);
                }

                if(paramIndex < UserSearchEx.this._params.size() - 1) {
                    sql.append(" OR ");
                }
            }

            this.appendFilterUserClause(sql);
            this.appendOnlyAvailableClause(sql);
            this.appendUserDirectoryClause(sql);
            return sql.toString();        }
    }


}
