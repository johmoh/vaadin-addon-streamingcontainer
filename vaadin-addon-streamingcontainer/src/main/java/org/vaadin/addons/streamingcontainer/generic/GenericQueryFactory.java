/**
 *
 */
package org.vaadin.addons.streamingcontainer.generic;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

import org.vaadin.addons.streamingcontainer.AbstractQuery;
import org.vaadin.addons.streamingcontainer.AbstractQueryFactory;
import org.vaadin.addons.streamingcontainer.Query;
import org.vaadin.addons.streamingcontainer.QueryDefinition;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * A generic factory for creating Query objects.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public final class GenericQueryFactory<BEANTYPE> extends AbstractQueryFactory<BEANTYPE>
{
    /** The Constant instanceHashMap. */
    @SuppressWarnings("rawtypes")
    private static final ConcurrentHashMap<Class<?>, GenericQueryFactory> instanceHashMap = new ConcurrentHashMap<Class<?>, GenericQueryFactory>();

    /**
     * Instantiates a new generic query factory.
     *
     * @param _queryType
     *            the _query type
     */
    private GenericQueryFactory(final Class<? extends AbstractQuery<BEANTYPE>> _queryType)
    {
        super(_queryType);
    }

    /**
     * Gets the single instance of GenericQueryFactory.
     *
     * @param <BEANTYPE>
     *            the generic type
     * @param _queryType
     *            the _query type
     * @return single instance of GenericQueryFactory
     */
    public static <BEANTYPE> GenericQueryFactory<BEANTYPE> getInstance(final Class<? extends AbstractQuery<BEANTYPE>> _queryType)
    {
        if (null == _queryType) {
            throw new NullPointerException("_queryType is NULL");
        }

        final GenericQueryFactory<BEANTYPE> result;
        if (instanceHashMap.containsKey(_queryType)) {
            @SuppressWarnings("unchecked")
            final GenericQueryFactory<BEANTYPE> factory = instanceHashMap.get(_queryType);
            result = factory;
        }
        else {
            synchronized (instanceHashMap) {
                if (instanceHashMap.containsKey(_queryType)) {
                    result = getInstance(_queryType);
                }
                else {
                    result = new GenericQueryFactory<BEANTYPE>(_queryType);
                    instanceHashMap.put(_queryType, result);
                }
            }
        }

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
