package com.iijima.bookmanager.infra.dao;

/** */
@org.springframework.stereotype.Repository()
@javax.annotation.Generated(value = { "Doma", "2.24.0" }, date = "2019-04-20T09:28:14.971+0900")
public class BookDaoImpl extends org.seasar.doma.internal.jdbc.dao.AbstractDao implements com.iijima.bookmanager.infra.dao.BookDao {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.24.0");
    }

    private static final java.lang.reflect.Method __method0 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(com.iijima.bookmanager.infra.dao.BookDao.class, "selectBookSummaries");

    /**
     * @param config the config
     */
    @org.springframework.beans.factory.annotation.Autowired()
    public BookDaoImpl(org.seasar.doma.jdbc.Config config) {
        super(config);
    }

    @Override
    public java.util.List<com.iijima.bookmanager.infra.entity.BookSummary> selectBookSummaries() {
        entering("com.iijima.bookmanager.infra.dao.BookDaoImpl", "selectBookSummaries");
        try {
            org.seasar.doma.jdbc.query.SqlFileSelectQuery __query = getQueryImplementors().createSqlFileSelectQuery(__method0);
            __query.setMethod(__method0);
            __query.setConfig(__config);
            __query.setSqlFilePath("META-INF/com/iijima/bookmanager/infra/dao/BookDao/selectBookSummaries.sql");
            __query.setEntityType(com.iijima.bookmanager.infra.entity._BookSummary.getSingletonInternal());
            __query.setCallerClassName("com.iijima.bookmanager.infra.dao.BookDaoImpl");
            __query.setCallerMethodName("selectBookSummaries");
            __query.setResultEnsured(false);
            __query.setResultMappingEnsured(false);
            __query.setFetchType(org.seasar.doma.FetchType.LAZY);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.prepare();
            org.seasar.doma.jdbc.command.SelectCommand<java.util.List<com.iijima.bookmanager.infra.entity.BookSummary>> __command = getCommandImplementors().createSelectCommand(__method0, __query, new org.seasar.doma.internal.jdbc.command.EntityResultListHandler<com.iijima.bookmanager.infra.entity.BookSummary>(com.iijima.bookmanager.infra.entity._BookSummary.getSingletonInternal()));
            java.util.List<com.iijima.bookmanager.infra.entity.BookSummary> __result = __command.execute();
            __query.complete();
            exiting("com.iijima.bookmanager.infra.dao.BookDaoImpl", "selectBookSummaries", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("com.iijima.bookmanager.infra.dao.BookDaoImpl", "selectBookSummaries", __e);
            throw __e;
        }
    }

}
