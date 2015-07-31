package org.vaadin.addons.streamingcontainer.demo;

import javax.persistence.EntityManager;

import org.vaadin.addons.streamingcontainer.AbstractQueryFactory;
import org.vaadin.addons.streamingcontainer.Query;
import org.vaadin.addons.streamingcontainer.QueryDefinition;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating JpaQuery objects.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class JpaQueryFactory<BEANTYPE> extends AbstractQueryFactory<BEANTYPE>
{
    /** The entity manager. */
    private final EntityManager entityManager;

    /**
     * Instantiates a new jpa query factory.
     *
     * @param _entityManager
     *            the _entity manager
     * @param _queryType
     *            the _query type
     */
    public JpaQueryFactory(final EntityManager _entityManager, final Class<? extends Query<BEANTYPE>> _queryType)
    {
        super(_queryType);

        if (null == _entityManager) {
            throw new NullPointerException("_entityManager is NULL");
        }

        this.entityManager = _entityManager;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.QueryFactory#createQuery(org.vaadin.addons.streamingcontainer.QueryDefinition,
     *      com.vaadin.data.Container.Filter[], java.lang.Object[], boolean[])
     */
    @Override
    public Query<BEANTYPE> createQuery(QueryDefinition<BEANTYPE> _queryDefinition,
                                       Filter[] _additionalFilters,
                                       Object[] _sortPropertyIds,
                                       boolean[] _sortPropertyAscendingStates)
    {
        final Query<BEANTYPE> result = new JpaQuery<BEANTYPE>( //
                entityManager, //
                _queryDefinition, //
                _additionalFilters, //
                _sortPropertyIds, //
                _sortPropertyAscendingStates //
        );
        return result;
    }
}
