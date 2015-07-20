/**
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.lang.reflect.Constructor;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * @author johmoh
 */
public final class GenericQueryFactory<BEANTYPE> extends AbstractQueryFactory<BEANTYPE>
{
    /**
     * Instantiates a new generic query factory.
     *
     * @param _queryType
     *            the _query type
     */
    public GenericQueryFactory(final Class<? extends Query<BEANTYPE>> _queryType)
    {
        super(_queryType);
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
                    QueryDefinition.class, //
                    Filter[].class, //
                    Object[].class, //
                    boolean[].class //
                );
            result = constructor.newInstance( //
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
