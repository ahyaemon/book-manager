package com.iijima.bookmanager.infra.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "2.24.0" }, date = "2019-04-20T09:28:14.820+0900")
public final class _PublisherRecord extends org.seasar.doma.jdbc.entity.AbstractEntityType<com.iijima.bookmanager.infra.entity.PublisherRecord> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.24.0");
    }

    private static final _PublisherRecord __singleton = new _PublisherRecord();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    private final org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator __idGenerator = new org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator();

    /** the id */
    public final org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, java.lang.Integer, java.lang.Integer> $id = new org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<>(com.iijima.bookmanager.infra.entity.PublisherRecord.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.IntegerWrapper::new, false), "id", "id", __namingType, false, __idGenerator);

    /** the name */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, java.lang.String, java.lang.String> $name = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.iijima.bookmanager.infra.entity.PublisherRecord.class, () -> new org.seasar.doma.internal.jdbc.scalar.BasicScalar<>(org.seasar.doma.wrapper.StringWrapper::new, false), "name", "name", __namingType, true, true, false);

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<com.iijima.bookmanager.infra.entity.PublisherRecord>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> __entityPropertyTypeMap;

    private _PublisherRecord() {
        __listenerSupplier = () -> ListenerHolder.listener;
        __immutable = false;
        __name = "PublisherRecord";
        __catalogName = "";
        __schemaName = "";
        __tableName = "";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> __list = new java.util.ArrayList<>(2);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> __map = new java.util.HashMap<>(2);
        __idList.add($id);
        __list.add($id);
        __map.put("id", $id);
        __list.add($name);
        __map.put("name", $name);
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
    public void preInsert(com.iijima.bookmanager.infra.entity.PublisherRecord entity, org.seasar.doma.jdbc.entity.PreInsertContext<com.iijima.bookmanager.infra.entity.PublisherRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(com.iijima.bookmanager.infra.entity.PublisherRecord entity, org.seasar.doma.jdbc.entity.PreUpdateContext<com.iijima.bookmanager.infra.entity.PublisherRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(com.iijima.bookmanager.infra.entity.PublisherRecord entity, org.seasar.doma.jdbc.entity.PreDeleteContext<com.iijima.bookmanager.infra.entity.PublisherRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(com.iijima.bookmanager.infra.entity.PublisherRecord entity, org.seasar.doma.jdbc.entity.PostInsertContext<com.iijima.bookmanager.infra.entity.PublisherRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(com.iijima.bookmanager.infra.entity.PublisherRecord entity, org.seasar.doma.jdbc.entity.PostUpdateContext<com.iijima.bookmanager.infra.entity.PublisherRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(com.iijima.bookmanager.infra.entity.PublisherRecord entity, org.seasar.doma.jdbc.entity.PostDeleteContext<com.iijima.bookmanager.infra.entity.PublisherRecord> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?, ?> getGeneratedIdPropertyType() {
        return $id;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.TenantIdPropertyType<com.iijima.bookmanager.infra.entity.PublisherRecord, ?, ?> getTenantIdPropertyType() {
        return null;
    }

    @Override
    public com.iijima.bookmanager.infra.entity.PublisherRecord newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<com.iijima.bookmanager.infra.entity.PublisherRecord, ?>> __args) {
        com.iijima.bookmanager.infra.entity.PublisherRecord entity = new com.iijima.bookmanager.infra.entity.PublisherRecord();
        if (__args.get("id") != null) __args.get("id").save(entity);
        if (__args.get("name") != null) __args.get("name").save(entity);
        return entity;
    }

    @Override
    public Class<com.iijima.bookmanager.infra.entity.PublisherRecord> getEntityClass() {
        return com.iijima.bookmanager.infra.entity.PublisherRecord.class;
    }

    @Override
    public com.iijima.bookmanager.infra.entity.PublisherRecord getOriginalStates(com.iijima.bookmanager.infra.entity.PublisherRecord __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(com.iijima.bookmanager.infra.entity.PublisherRecord __entity) {
    }

    /**
     * @return the singleton
     */
    public static _PublisherRecord getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _PublisherRecord newInstance() {
        return new _PublisherRecord();
    }

    private static class ListenerHolder {
        private static org.seasar.doma.jdbc.entity.NullEntityListener<com.iijima.bookmanager.infra.entity.PublisherRecord> listener = new org.seasar.doma.jdbc.entity.NullEntityListener<>();
    }

}
