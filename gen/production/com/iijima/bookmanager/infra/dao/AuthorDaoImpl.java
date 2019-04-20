package com.iijima.bookmanager.infra.dao;

/** */
@org.springframework.stereotype.Repository()
@javax.annotation.Generated(value = { "Doma", "2.24.0" }, date = "2019-04-20T09:28:14.991+0900")
public class AuthorDaoImpl extends org.seasar.doma.internal.jdbc.dao.AbstractDao implements com.iijima.bookmanager.infra.dao.AuthorDao {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.24.0");
    }

    private static final java.lang.reflect.Method __method0 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(com.iijima.bookmanager.infra.dao.AuthorDao.class, "select");

    /**
     * @param config the config
     */
    @org.springframework.beans.factory.annotation.Autowired()
    public AuthorDaoImpl(org.seasar.doma.jdbc.Config config) {
        super(config);
    }

    @Override
    public java.util.List<com.iijima.bookmanager.infra.entity.AuthorRecord> select() {
        entering("com.iijima.bookmanager.infra.dao.AuthorDaoImpl", "select");
        try {
            org.seasar.doma.jdbc.query.SqlFileSelectQuery __query = getQueryImplementors().createSqlFileSelectQuery(__method0);
            __query.setMethod(__method0);
            __query.setConfig(__config);
            __query.setSqlFilePath("META-INF/com/iijima/bookmanager/infra/dao/AuthorDao/select.sql");
            __query.setEntityType(com.iijima.bookmanager.infra.entity._AuthorRecord.getSingletonInternal());
            __query.setCallerClassName("com.iijima.bookmanager.infra.dao.AuthorDaoImpl");
            __query.setCallerMethodName("select");
            __query.setResultEnsured(false);
            __query.setResultMappingEnsured(false);
            __query.setFetchType(org.seasar.doma.FetchType.LAZY);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.prepare();
            org.seasar.doma.jdbc.command.SelectCommand<java.util.List<com.iijima.bookmanager.infra.entity.AuthorRecord>> __command = getCommandImplementors().createSelectCommand(__method0, __query, new org.seasar.doma.internal.jdbc.command.EntityResultListHandler<com.iijima.bookmanager.infra.entity.AuthorRecord>(com.iijima.bookmanager.infra.entity._AuthorRecord.getSingletonInternal()));
            java.util.List<com.iijima.bookmanager.infra.entity.AuthorRecord> __result = __command.execute();
            __query.complete();
            exiting("com.iijima.bookmanager.infra.dao.AuthorDaoImpl", "select", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("com.iijima.bookmanager.infra.dao.AuthorDaoImpl", "select", __e);
            throw __e;
        }
    }

}
