/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractQuery.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public abstract class AbstractQuery<BEANTYPE> implements Query<BEANTYPE>
{
    /** The query definition. */
    private final QueryDefinition<BEANTYPE> queryDefinition;

    /** The additional filter. */
    private final Filter[] additionalFilters;

    /** The sort property ids. */
    private final Object[] sortPropertyIds;

    /** The sort property acending states. */
    private final boolean[] sortPropertyAcendingStates;

    /**
     * Instantiates a new abstract query.
     *
     * @param _queryDefinition
     *            the _query definition
     * @param _additionalFilters
     *            the _additional filter
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAcendingStates
     *            the _sort property acending states
     * @throws IllegalArgumentException
     *             the illegal argument exception
     */
    protected AbstractQuery(final QueryDefinition<BEANTYPE> _queryDefinition,
                            final Filter[] _additionalFilters,
                            final Object[] _sortPropertyIds,
                            final boolean[] _sortPropertyAcendingStates)
        throws IllegalArgumentException
    {
        if (null == _queryDefinition) {
            throw new IllegalArgumentException("Parameter '_queryDefinition' is null");
        }
        final int numSortPropertyIds = ((null == _sortPropertyIds) ? 0 : _sortPropertyIds.length);
        final int numSortPropertyAcendingStates = ((null == _sortPropertyAcendingStates) ? 0
                : _sortPropertyAcendingStates.length);
        if (numSortPropertyIds != numSortPropertyAcendingStates) {
            throw new IllegalArgumentException(
                    "Array parameters '_sortPropertyIds' and '_sortPropertyAcendingStates' contain a different number of values");
        }

        this.queryDefinition = _queryDefinition;
        this.additionalFilters = (null != _additionalFilters ? _additionalFilters : Constants.EMPTY_ADDITIONAL_FILTERS);
        this.sortPropertyIds = ((numSortPropertyIds > 0) ? _sortPropertyIds : Constants.EMPTY_SORT_PROPERTY_IDS);
        this.sortPropertyAcendingStates = ((numSortPropertyAcendingStates > 0) ? _sortPropertyAcendingStates
                : Constants.EMPTY_SORT_PROPERTY_ASCENDING_STATES);
    }

    /**
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize()
        throws Throwable
    {
        try {
            closeStream();
        }
        catch (final Throwable _t) {
        }
        finally {
            super.finalize();
        }
    }

    /**
     * Gets the query definition.
     *
     * @return the query definition
     */
    public QueryDefinition<BEANTYPE> getQueryDefinition()
    {
        return queryDefinition;
    }

    /**
     * Gets the additional filters.
     *
     * @return the additional filters
     */
    public Filter[] getAdditionalFilters()
    {
        return additionalFilters;
    }

    /**
     * Gets the sort property ids.
     *
     * @return the sort property ids
     */
    public Object[] getSortPropertyIds()
    {
        return sortPropertyIds;
    }

    /**
     * Gets the sort property acending states.
     *
     * @return the sort property acending states
     */
    public boolean[] getSortPropertyAcendingStates()
    {
        return sortPropertyAcendingStates;
    }
}
