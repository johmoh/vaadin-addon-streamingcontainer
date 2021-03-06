package org.vaadin.addons.streamingcontainer.jpa.generic;

import java.lang.reflect.Constructor;

import javax.persistence.EntityManager;

import org.vaadin.addons.streamingcontainer.AbstractQueryFactory;
import org.vaadin.addons.streamingcontainer.Query;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.jpa.AbstractJpaQuery;
import org.vaadin.addons.streamingcontainer.jpa.JpaTypedQueryBuilder;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * A generic factory for creating JpaQuery objects.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericJpaQueryFactory<BEANTYPE> extends AbstractQueryFactory<BEANTYPE>
{
    /** The default jpa query type. */
    @SuppressWarnings("rawtypes")
    private static Class<? extends AbstractJpaQuery> DEFAULT_JPA_QUERY_TYPE = GenericJpaQuery.class;

    /** The entity manager. */
    private final EntityManager entityManager;

    /** The jpa typed query builder. */
    private final JpaTypedQueryBuilder<BEANTYPE> jpaTypedQueryBuilder;

    /**
     * Instantiates a new jpa query factory.
     *
     * @param _entityManager
     *            the _entity manager
     */
    public GenericJpaQueryFactory(final EntityManager _entityManager)
    {
        this( //
                GenericJpaQueryFactory.<BEANTYPE> getDefaultJpaQueryType(), //
                _entityManager, //
                GenericJpaTypedQueryBuilder.<BEANTYPE> getInstance() //
        );
    }

    /**
     * Instantiates a new generic jpa query factory.
     *
     * @param _queryType
     *            the _query type
     * @param _entityManager
     *            the _entity manager
     * @param _jpaTypedQueryBuilder
     *            the _jpa typed query builder
     */
    public GenericJpaQueryFactory(final Class<? extends AbstractJpaQuery<BEANTYPE>> _queryType,
                                  final EntityManager _entityManager,
                                  final JpaTypedQueryBuilder<BEANTYPE> _jpaTypedQueryBuilder)
    {
        super(_queryType);

        if (null == _entityManager) {
            throw new NullPointerException("_entityManager is NULL");
        }
        if (null == _jpaTypedQueryBuilder) {
            throw new NullPointerException("_jpaTypedQueryBuilder is NULL");
        }

        this.entityManager = _entityManager;
        this.jpaTypedQueryBuilder = _jpaTypedQueryBuilder;
    }

    /**
     * Gets the default jpa query type.
     *
     * @param <BEANTYPE>
     *            the generic type
     * @return the default jpa query type
     */
    private static <BEANTYPE> Class<? extends AbstractJpaQuery<BEANTYPE>> getDefaultJpaQueryType()
    {
        @SuppressWarnings("unchecked")
        final Class<? extends AbstractJpaQuery<BEANTYPE>> result = (Class<? extends AbstractJpaQuery<BEANTYPE>>) DEFAULT_JPA_QUERY_TYPE;
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.QueryFactory#createQuery(org.vaadin.addons.streamingcontainer.QueryDefinition,
     *      com.vaadin.data.Container.Filter[], java.lang.Object[], boolean[])
     */
    @Override
    public Query<BEANTYPE> createQuery(final QueryDefinition<BEANTYPE> _queryDefinition,
                                       final Filter[] _additionalFilters,
                                       final Object[] _sortPropertyIds,
                                       final boolean[] _sortPropertyAscendingStates)
    {
        if (null == _queryDefinition) {
            throw new NullPointerException("_queryDefinition is NULL");
        }
        if (null == _additionalFilters) {
            throw new NullPointerException("_additionalFilter is NULL");
        }
        if ((null == _sortPropertyIds) != (null == _sortPropertyAscendingStates)) {
            throw new IllegalArgumentException(
                    "One array is NULL and the other is not (_sortPropertyIds, _sortPropertyAscendingStates)");
        }
        if ((null != _sortPropertyIds) && (_sortPropertyIds.length != _sortPropertyAscendingStates.length)) {
            throw new IllegalArgumentException(
                    "Arrays have diffenent size (_sortPropertyIds, _sortPropertyAscendingStates)");
        }

        Query<BEANTYPE> result;
        try {
            final Class<? extends Query<BEANTYPE>> queryType = getQueryType();
            final Constructor<? extends Query<BEANTYPE>> constructor = queryType.getConstructor( //
                    EntityManager.class, //
                    JpaTypedQueryBuilder.class, //
                    QueryDefinition.class, //
                    Filter[].class, //
                    Object[].class, //
                    boolean[].class //
                );
            result = constructor.newInstance( //
                    entityManager, //
                    jpaTypedQueryBuilder, //
                    _queryDefinition, //
                    _additionalFilters, //
                    _sortPropertyIds, //
                    _sortPropertyAscendingStates //
                );
        }
        catch (final Exception _ex) {
            result = null;
        }

        return result;
    }
}
