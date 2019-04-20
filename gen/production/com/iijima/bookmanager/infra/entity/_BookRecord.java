package com.iijima.bookmanager.infra.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "2.24.0" }, date = "2019-04-20T09:28:14.860+0900")
public final class _BookRecord extends org.seasar.doma.jdbc.entity.AbstractEntityType<com.iijima.bookmanager.infra.entity.BookRecord> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.24.0");
    }

    private static final _BookRecord __singleton = new _BookRecord();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    private final org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator __idGenerator = new org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator();

    /** the id */
    public final org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, java.lang.Integer, java.lang.Integer> $id = new org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<>(com.iijima.bookmanager.infra.entity.BookRecord.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.IntegerWrapper::new, false), "id", "id", __namingType, false, __idGenerator);

    /** the title */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, java.lang.String, java.lang.String> $title = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookRecord.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.StringWrapper::new, false), "title", "title", __namingType, true, true, false);

    /** the author_id */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, java.lang.Integer, java.lang.Integer> $author_id = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookRecord.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.IntegerWrapper::new, false), "author_id", "author_id", __namingType, true, true, false);

    /** the publisher_id */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, java.lang.Integer, java.lang.Integer> $publisher_id = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookRecord.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.IntegerWrapper::new, false), "publisher_id", "publisher_id", __namingType, true, true, false);

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<com.iijima.bookmanager.infra.entity.BookRecord>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> __entityPropertyTypeMap;

    private _BookRecord() {
        __listenerSupplier = () -> ListenerHolder.listener;
        __immutable = false;
        __name = "BookRecord";
        __catalogName = "";
        __schemaName = "";
        __tableName = "";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> __list = new java.util.ArrayList<>(4);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> __map = new java.util.HashMap<>(4);
        __idList.add($id);
        __list.add($id);
        __map.put("id", $id);
        __list.add($title);
        __map.put("title", $title);
        __list.add($author_id);
        __map.put("author_id", $author_id);
        __list.add($publisher_id);
        __map.put("publisher_id", $publisher_id);
        __idPropertyTypes = java.util.Collections.unmodifiableList(__idList);
        __entityPropertyTypes = java.util.Collections.unmodifiableList(__list);
        __entityPropertyTypeMap = java.util.Collections.unmodifiableMap(__map);
    }

    @Override
    public org.seasar.doma.jdbc.entity.NamingType getNamingType() {
        return __namingType;
    }

    @Override
    public boolean isImmutable() {
        return __immutable;
    }

    @Override
    public String getName() {
        return __name;
    }

    @Override
    public String getCatalogName() {
        return __catalogName;
    }

    @Override
    public String getSchemaName() {
        return __schemaName;
    }

    @Override
    public String getTableName() {
        return getTableName(org.seasar.doma.jdbc.Naming.DEFAULT::apply);
    }

    @Override
    public String getTableName(java.util.function.BiFunction<org.seasar.doma.jdbc.entity.NamingType, String, String> namingFunction) {
        if (__tableName.isEmpty()) {
            return namingFunction.apply(__namingType, __name);
        }
        return __tableName;
    }

    @Override
    public boolean isQuoteRequired() {
        return __isQuoteRequired;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preInsert(com.iijima.bookmanager.infra.entity.BookRecord entity, org.seasar.doma.jdbc.entity.PreInsertContext<com.iijima.bookmanager.infra.entity.BookRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(com.iijima.bookmanager.infra.entity.BookRecord entity, org.seasar.doma.jdbc.entity.PreUpdateContext<com.iijima.bookmanager.infra.entity.BookRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(com.iijima.bookmanager.infra.entity.BookRecord entity, org.seasar.doma.jdbc.entity.PreDeleteContext<com.iijima.bookmanager.infra.entity.BookRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(com.iijima.bookmanager.infra.entity.BookRecord entity, org.seasar.doma.jdbc.entity.PostInsertContext<com.iijima.bookmanager.infra.entity.BookRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(com.iijima.bookmanager.infra.entity.BookRecord entity, org.seasar.doma.jdbc.entity.PostUpdateContext<com.iijima.bookmanager.infra.entity.BookRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(com.iijima.bookmanager.infra.entity.BookRecord entity, org.seasar.doma.jdbc.entity.PostDeleteContext<com.iijima.bookmanager.infra.entity.BookRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?, ?> getGeneratedIdPropertyType() {
        return $id;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.TenantIdPropertyType<com.iijima.bookmanager.infra.entity.BookRecord, ?, ?> getTenantIdPropertyType() {
        return null;
    }

    @Override
    public com.iijima.bookmanager.infra.entity.BookRecord newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<com.iijima.bookmanager.infra.entity.BookRecord, ?>> __args) {
        com.iijima.bookmanager.infra.entity.BookRecord entity = new com.iijima.bookmanager.infra.entity.BookRecord();
        if (__args.get("id") != null) __args.get("id").save(entity);
        if (__args.get("title") != null) __args.get("title").save(entity);
        if (__args.get("author_id") != null) __args.get("author_id").save(entity);
        if (__args.get("publisher_id") != null) __args.get("publisher_id").save(entity);
        return entity;
    }

    @Override
    public Class<com.iijima.bookmanager.infra.entity.BookRecord> getEntityClass() {
        return com.iijima.bookmanager.infra.entity.BookRecord.class;
    }

    @Override
    public com.iijima.bookmanager.infra.entity.BookRecord getOriginalStates(com.iijima.bookmanager.infra.entity.BookRecord __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(com.iijima.bookmanager.infra.entity.BookRecord __entity) {
    }

    /**
     * @return the singleton
     */
    public static _BookRecord getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _BookRecord newInstance() {
        return new _BookRecord();
    }

    private static class ListenerHolder {
        private static org.seasar.doma.jdbc.entity.NullEntityListener<com.iijima.bookmanager.infra.entity.BookRecord> listener = new org.seasar.doma.jdbc.entity.NullEntityListener<>();
    }

}
