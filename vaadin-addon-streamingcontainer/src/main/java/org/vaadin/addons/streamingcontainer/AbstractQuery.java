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
    /** The disposed. */
    private boolean disposed = false;

    /** The query definition. */
    private QueryDefinition<BEANTYPE> queryDefinition;

    /**
     * The additional filter.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of this array!
     */
    private Filter[] additionalFilters;

    /**
     * The sort property ids.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of this array!
     */
    private Object[] sortPropertyIds;

    /**
     * The sort property ascending states.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of this array!
     */
    private boolean[] sortPropertyAscendingStates;

    /**
     * Instantiates a new abstract query.
     *
     * @param _queryDefinition
     *            the _query definition
     * @param _additionalFilters
     *            the _additional filter
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAscendingStates
     *            the _sort property ascending states
     */
    protected AbstractQuery(final QueryDefinition<BEANTYPE> _queryDefinition,
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

        this.queryDefinition = _queryDefinition;
        this.additionalFilters = (null != _additionalFilters ? _additionalFilters : Constants.EMPTY_ADDITIONAL_FILTERS);
        if (null != _sortPropertyIds) {
            this.sortPropertyIds = _sortPropertyIds;
            this.sortPropertyAscendingStates = _sortPropertyAscendingStates;
        }
        else {
            this.sortPropertyIds = _queryDefinition.getSortPropertyIds();
            this.sortPropertyAscendingStates = _queryDefinition.getSortPropertyAscendingStates();
        }
    }

    /**
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize()
        throws Throwable
    {
        try {
            dispose();
        }
        catch (final Throwable _t) {
        }
        finally {
            super.finalize();
        }
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.Disposable#dispose()
     */
    @Override
    public final void dispose()
    {
        boolean disposed = this.disposed;
        if (!disposed) {
            synchronized (this) {
                disposed = this.disposed;
                this.disposed = true;
            }

            if (!disposed) {
                doDispose();
            }
        }
    }

    /**
     * Do dispose.
     */
    protected void doDispose()
    {
        queryDefinition = null;
        additionalFilters = null;
        sortPropertyIds = null;
        sortPropertyAscendingStates = null;
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
     * Gets the additional filters.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of the array!
     *
     * @return the additional filters
     */
    public Filter[] getAdditionalFilters()
    {
        return additionalFilters;
    }

    /**
     * Gets the sort property ids.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of the array!
     *
     * @return the sort property ids
     */
    public Object[] getSortPropertyIds()
    {
        return sortPropertyIds;
    }

    /**
     * Gets the sort property ascending states.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of the array!
     *
     * @return the sort property ascending states
     */
    public boolean[] getSortPropertyAscendingStates()
    {
        return sortPropertyAscendingStates;
    }
}
