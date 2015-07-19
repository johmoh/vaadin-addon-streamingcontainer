/**
 *
 */
package org.vaadin.addons.streamingcontainer;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * @author johmoh
 */
public interface QueryFactory<BEANTYPE>
{
    /**
     * Gets the query type.
     *
     * @return the query type
     */
    public Class<? extends Query<BEANTYPE>> getQueryType();

    /**
     * Creates a new Query object.
     *
     * @param _queryDefinition
     *            the _query definition
     * @param _additionalFilters
     *            the _additional filters
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAcendingStates
     *            the _sort property acending states
     * @return the query< beantyp e>
     */
    public Query<BEANTYPE> createQuery(final QueryDefinition<BEANTYPE> _queryDefinition,
                                       final Filter[] _additionalFilters,
                                       final Object[] _sortPropertyIds,
                                       final boolean[] _sortPropertyAcendingStates);
}
