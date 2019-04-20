package com.iijima.bookmanager.infra.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "2.24.0" }, date = "2019-04-20T09:28:14.870+0900")
public final class _BookSummary extends org.seasar.doma.jdbc.entity.AbstractEntityType<com.iijima.bookmanager.infra.entity.BookSummary> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.24.0");
    }

    private static final _BookSummary __singleton = new _BookSummary();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    /** the id */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, java.lang.Integer, java.lang.Integer> $id = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookSummary.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.IntegerWrapper::new, false), "id", "id", __namingType, true, true, false);

    /** the title */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, java.lang.String, java.lang.String> $title = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookSummary.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.StringWrapper::new, false), "title", "title", __namingType, true, true, false);

    /** the authorId */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, java.lang.Integer, java.lang.Integer> $authorId = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookSummary.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.IntegerWrapper::new, false), "authorId", "author_id", __namingType, true, true, false);

    /** the authorName */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, java.lang.String, java.lang.String> $authorName = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookSummary.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.StringWrapper::new, false), "authorName", "author_name", __namingType, true, true, false);

    /** the publisherId */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, java.lang.Integer, java.lang.Integer> $publisherId = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookSummary.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.IntegerWrapper::new, false), "publisherId", "publisher_id", __namingType, true, true, false);

    /** the publisherName */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, java.lang.String, java.lang.String> $publisherName = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.BookSummary.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.StringWrapper::new, false), "publisherName", "publisher_name", __namingType, true, true, false);

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<com.iijima.bookmanager.infra.entity.BookSummary>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> __entityPropertyTypeMap;

    private _BookSummary() {
        __listenerSupplier = () -> ListenerHolder.listener;
        __immutable = false;
        __name = "BookSummary";
        __catalogName = "";
        __schemaName = "";
        __tableName = "";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> __list = new java.util.ArrayList<>(6);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> __map = new java.util.HashMap<>(6);
        __list.add($id);
        __map.put("id", $id);
        __list.add($title);
        __map.put("title", $title);
        __list.add($authorId);
        __map.put("authorId", $authorId);
        __list.add($authorName);
        __map.put("authorName", $authorName);
        __list.add($publisherId);
        __map.put("publisherId", $publisherId);
        __list.add($publisherName);
        __map.put("publisherName", $publisherName);
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
    public void preInsert(com.iijima.bookmanager.infra.entity.BookSummary entity, org.seasar.doma.jdbc.entity.PreInsertContext<com.iijima.bookmanager.infra.entity.BookSummary> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(com.iijima.bookmanager.infra.entity.BookSummary entity, org.seasar.doma.jdbc.entity.PreUpdateContext<com.iijima.bookmanager.infra.entity.BookSummary> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(com.iijima.bookmanager.infra.entity.BookSummary entity, org.seasar.doma.jdbc.entity.PreDeleteContext<com.iijima.bookmanager.infra.entity.BookSummary> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(com.iijima.bookmanager.infra.entity.BookSummary entity, org.seasar.doma.jdbc.entity.PostInsertContext<com.iijima.bookmanager.infra.entity.BookSummary> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(com.iijima.bookmanager.infra.entity.BookSummary entity, org.seasar.doma.jdbc.entity.PostUpdateContext<com.iijima.bookmanager.infra.entity.BookSummary> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(com.iijima.bookmanager.infra.entity.BookSummary entity, org.seasar.doma.jdbc.entity.PostDeleteContext<com.iijima.bookmanager.infra.entity.BookSummary> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?, ?> getGeneratedIdPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.TenantIdPropertyType<com.iijima.bookmanager.infra.entity.BookSummary, ?, ?> getTenantIdPropertyType() {
        return null;
    }

    @Override
    public com.iijima.bookmanager.infra.entity.BookSummary newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<com.iijima.bookmanager.infra.entity.BookSummary, ?>> __args) {
        com.iijima.bookmanager.infra.entity.BookSummary entity = new com.iijima.bookmanager.infra.entity.BookSummary();
        if (__args.get("id") != null) __args.get("id").save(entity);
        if (__args.get("title") != null) __args.get("title").save(entity);
        if (__args.get("authorId") != null) __args.get("authorId").save(entity);
        if (__args.get("authorName") != null) __args.get("authorName").save(entity);
        if (__args.get("publisherId") != null) __args.get("publisherId").save(entity);
        if (__args.get("publisherName") != null) __args.get("publisherName").save(entity);
        return entity;
    }

    @Override
    public Class<com.iijima.bookmanager.infra.entity.BookSummary> getEntityClass() {
        return com.iijima.bookmanager.infra.entity.BookSummary.class;
    }

    @Override
    public com.iijima.bookmanager.infra.entity.BookSummary getOriginalStates(com.iijima.bookmanager.infra.entity.BookSummary __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(com.iijima.bookmanager.infra.entity.BookSummary __entity) {
    }

    /**
     * @return the singleton
     */
    public static _BookSummary getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _BookSummary newInstance() {
        return new _BookSummary();
    }

    private static class ListenerHolder {
        private static org.seasar.doma.jdbc.entity.NullEntityListener<com.iijima.bookmanager.infra.entity.BookSummary> listener = new org.seasar.doma.jdbc.entity.NullEntityListener<>();
    }

}
