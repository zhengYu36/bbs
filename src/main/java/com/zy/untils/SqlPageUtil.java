package com.zy.untils;

public class SqlPageUtil {

    /**
     * 创建分页sql
     * @param dialect 方言
     * @param sql 原sql
     * @param pageSize 分页条数
     * @param page 页数
     * @return 分页sql
     */
    public static String pageSql(Dialect dialect, String sql, int pageSize, int page) {
        if (dialect == null || sql == null) {
            throw new IllegalArgumentException("dialect and sql can't null");
        }
        switch (dialect) {
            case MySql:
                return mysqlPageSql(sql, pageSize, page);
            case Oracle:
                return oraclePageSql(sql, pageSize, page);
            default:
                throw new IllegalArgumentException("not support this dialect: " + dialect);
        }
    }

    /**
     * 查询条数
     * @param sql 原sql
     * @return 条数查询sql
     */
    public static String countSql(String sql) {
        return "select count(1) as count from ( " + sql + " ) page_temp";
    }

    /**
     * 计算分页页数
     * @param pageSize 分页条数
     * @param count 总条数
     * @return 页数
     */
    public static long pageCount(int pageSize, long count) {
        return (count  +  pageSize  - 1) / pageSize;
    }

    private static String oraclePageSql(String sql, int pageSize, int page) {
        return "select page_temp.* from ( select page_temp.*, ROWNUM RN from ( "+ sql + " ) page_temp where ROWNUM <= "
                + (pageSize * (page + 1)) + " ) where RN >= " + (page * pageSize);
    }

    private static String mysqlPageSql(String sql, int pageSize, int page) {
        return "select page_temp.* from ( " + sql + " ) page_temp " + "limit " + ((page-1) * pageSize) + ", " + pageSize;
    }

    public enum Dialect{
        MySql, Oracle
    }

}
