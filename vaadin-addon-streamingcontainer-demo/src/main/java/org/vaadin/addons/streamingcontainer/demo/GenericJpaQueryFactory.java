package org.vaadin.addons.streamingcontainer.demo;

import javax.persistence.EntityManager;

import org.vaadin.addons.streamingcontainer.AbstractQueryFactory;
import org.vaadin.addons.streamingcontainer.Query;
import org.vaadin.addons.streamingcontainer.QueryDefinition;

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
    /** The entity manager. */
    private final EntityManager entityManager;

    /**
     * Instantiates a new jpa query factory.
     *
     * @param _entityManager
     *            the _entity manager
     */
    public GenericJpaQueryFactory(final EntityManager _entityManager)
    {
        super(GenericJpaQueryFactory.<BEANTYPE> buildGenericJpaQueryType());

        if (null == _entityManager) {
            throw new NullPointerException("_entityManager is NULL");
        }

        this.entityManager = _entityManager;
    }

    /**
     * Gets the query type.
     *
     * @param <BEANTYPE>
     *            the generic type
     * @return the query type
     */
    private static <BEANTYPE> Class<? extends Query<BEANTYPE>> buildGenericJpaQueryType()
    {
        // This hack is necessary because otherwise the code will not compile. And: Yes, it must be done in two steps...
        @SuppressWarnings({ "rawtypes" })
        final Class<? extends Query> step1 = (Class<? extends Query>) GenericJpaQuery.class;
        @SuppressWarnings("unchecked")
        final Class<? extends Query<BEANTYPE>> step2 = (Class<? extends Query<BEANTYPE>>) step1;
        return step2;
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
        final Query<BEANTYPE> result = new GenericJpaQuery<BEANTYPE>( //
                entityManager, //
                _queryDefinition, //
                _additionalFilters, //
                _sortPropertyIds, //
                _sortPropertyAscendingStates //
        );
        return result;
    }
}
