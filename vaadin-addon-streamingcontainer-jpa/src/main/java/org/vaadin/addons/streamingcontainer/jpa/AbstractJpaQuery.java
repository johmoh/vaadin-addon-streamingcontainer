package org.vaadin.addons.streamingcontainer.jpa;

import javax.persistence.EntityManager;

import org.vaadin.addons.streamingcontainer.AbstractQuery;
import org.vaadin.addons.streamingcontainer.QueryDefinition;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractJpaQuery.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public abstract class AbstractJpaQuery<BEANTYPE> extends AbstractQuery<BEANTYPE>
{
    /** The entity manager. */
    private final EntityManager entityManager;

    /** The jpa typed query builder. */
    private final JpaTypedQueryBuilder<BEANTYPE> jpaTypedQueryBuilder;

    /**
     * Instantiates a new abstract jpa query.
     *
     * @param _entityManager
     *            the _entity manager
     * @param _jpaTypedQueryBuilder
     *            the _jpa typed query builder
     * @param _queryDefinition
     *            the _query definition
     * @param _additionalFilters
     *            the _additional filters
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAscendingStates
     *            the _sort property ascending states
     */
    protected AbstractJpaQuery(final EntityManager _entityManager,
                               final JpaTypedQueryBuilder<BEANTYPE> _jpaTypedQueryBuilder,
                               final QueryDefinition<BEANTYPE> _queryDefinition,
                               final Filter[] _additionalFilters,
                               final Object[] _sortPropertyIds,
                               final boolean[] _sortPropertyAscendingStates)
    {
        super(_queryDefinition, _additionalFilters, _sortPropertyIds, _sortPropertyAscendingStates);

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
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    /**
     * Gets the jpa typed query builder.
     *
     * @return the jpa typed query builder
     */
    public JpaTypedQueryBuilder<BEANTYPE> getJpaTypedQueryBuilder()
    {
        return jpaTypedQueryBuilder;
    }
}
