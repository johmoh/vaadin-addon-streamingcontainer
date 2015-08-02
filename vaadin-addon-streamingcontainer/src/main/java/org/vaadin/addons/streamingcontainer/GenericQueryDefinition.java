/**
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.addons.streamingcontainer.Constants.Defaults;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * @author johmoh
 */
public final class GenericQueryDefinition<BEANTYPE> implements QueryDefinition<BEANTYPE>
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4991787542075663487L;

    /** The property definition set. */
    private final BeanDefinition<BEANTYPE> beanDefinition;

    /** The batch size hint. */
    private int batchSizeHint;

    /** The max query size hint. */
    private int maxQuerySizeHint;

    /** The additional parameter map. */
    private Map<Object, Object> additionalParameterMap = new HashMap<Object, Object>();

    /**
     * The sort property ids.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of the array!
     */
    public Object[] sortPropertyIds;

    /**
     * The sort property ascending states.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of the array!
     */
    public boolean[] sortPropertyAscendingStates;

    /** The filters. */
    public ArrayList<Filter> filters;

    /**
     * Instantiates a new generic query definition.
     *
     * @param _beanDefinition
     *            the _bean definition
     */
    public GenericQueryDefinition(final BeanDefinition<BEANTYPE> _beanDefinition)
    {
        this.beanDefinition = _beanDefinition;
        this.batchSizeHint = Defaults.DEFAULT_BATCH_SIZE_HINT;
        this.maxQuerySizeHint = Defaults.DEFAULT_MAX_QUERY_SIZE_HINT;
        this.sortPropertyIds = Constants.EMPTY_SORT_PROPERTY_IDS;
        this.sortPropertyAscendingStates = Constants.EMPTY_SORT_PROPERTY_ASCENDING_STATES;
        this.filters = new ArrayList<Filter>();
    }

    /**
     * Instantiates a new generic query definition.
     *
     * @param _prototype
     *            the _prototype
     */
    public GenericQueryDefinition(final QueryDefinition<BEANTYPE> _prototype)
    {
        this.beanDefinition = new GenericBeanDefinition<BEANTYPE>(_prototype.getBeanDefinition());
        this.batchSizeHint = _prototype.getBatchSizeHint();
        this.maxQuerySizeHint = _prototype.getMaxQuerySizeHint();
        this.sortPropertyIds = _prototype.getSortPropertyIds();
        this.sortPropertyAscendingStates = _prototype.getSortPropertyAscendingStates();
        this.filters = new ArrayList<Filter>();
        this.filters.addAll(_prototype.getFilters());
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.QueryDefinition#getBeanDefinition()
     */
    @Override
    public BeanDefinition<BEANTYPE> getBeanDefinition()
    {
        return beanDefinition;
    }

    /**
     * @see org.vaadin.test.web.prototype.QueryDefinition#getBatchSizeHint()
     */
    @Override
    public int getBatchSizeHint()
    {
        return batchSizeHint;
    }

    /**
     * Sets the batch size hint.
     *
     * @param _batchSizeHint
     *            the _batch size hint
     * @return the query definition
     */
    public GenericQueryDefinition<BEANTYPE> setBatchSizeHint(final int _batchSizeHint)
    {
        this.batchSizeHint = _batchSizeHint;
        return this;
    }

    /**
     * @see org.vaadin.test.web.prototype.QueryDefinition#getMaxQuerySizeHint()
     */
    @Override
    public int getMaxQuerySizeHint()
    {
        return maxQuerySizeHint;
    }

    /**
     * Sets the max query size hint.
     *
     * @param _maxQuerySizeHint
     *            the _max query size hint
     * @return the query definition
     */
    public GenericQueryDefinition<BEANTYPE> setMaxQuerySizeHint(final int _maxQuerySizeHint)
    {
        this.maxQuerySizeHint = _maxQuerySizeHint;
        return this;
    }

    /**
     * @see org.vaadin.test.web.lazystreamingquerycontainer.QueryDefinition#getAdditionalParameters()
     */
    @Override
    public Map<Object, Object> getAdditionalParameters()
    {
        return additionalParameterMap;
    }

    /**
     * Sets the additional parameters.
     *
     * @param _additionalParameterMap
     *            the _additional parameter map
     * @return the generic query definition
     */
    public GenericQueryDefinition<BEANTYPE> setAdditionalParameters(final Map<Object, Object> _additionalParameterMap)
    {
        additionalParameterMap = ((null != _additionalParameterMap) ? new HashMap<Object, Object>(
                _additionalParameterMap) : new HashMap<Object, Object>());
        return this;
    }

    /**
     * @see org.vaadin.test.web.lazystreamingquerycontainer.QueryDefinition#hasAdditionalParameter(java.lang.Object)
     */
    @Override
    public boolean hasAdditionalParameter(final Object _key)
    {
        final boolean result = additionalParameterMap.containsKey(_key);
        return result;
    }

    /**
     * @see org.vaadin.test.web.lazystreamingquerycontainer.QueryDefinition#getAdditionalParameter(java.lang.Object)
     */
    @Override
    public Object getAdditionalParameter(final Object _key)
    {
        final Object result = additionalParameterMap.get(_key);
        return result;
    }

    /**
     * Sets the additional parameter.
     *
     * @param _key
     *            the _key
     * @param _value
     *            the _value
     * @return the generic query definition
     */
    public GenericQueryDefinition<BEANTYPE> setAdditionalParameter(final Object _key, final Object _value)
    {
        additionalParameterMap.put(_key, _value);
        return this;
    }

    /**
     * Removes the additional parameter.
     *
     * @param _key
     *            the _key
     * @return the generic query definition
     */
    public GenericQueryDefinition<BEANTYPE> removeAdditionalParameter(final Object _key)
    {
        additionalParameterMap.remove(_key);
        return this;
    }

    /**
     * Removes the all additional parameters.
     *
     * @return the generic query definition
     */
    public GenericQueryDefinition<BEANTYPE> removeAllAdditionalParameters()
    {
        additionalParameterMap.clear();
        return this;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.QueryDefinition#getSortPropertyIds()
     */
    @Override
    public Object[] getSortPropertyIds()
    {
        return sortPropertyIds;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.QueryDefinition#getSortPropertyAscendingStates()
     */
    @Override
    public boolean[] getSortPropertyAscendingStates()
    {
        return sortPropertyAscendingStates;
    }

    /**
     * Sets the sort properties.
     *
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAcendingStates
     *            the _sort property acending states
     * @return the query definition
     */
    public GenericQueryDefinition<BEANTYPE> setSortProperties(final Object[] _sortPropertyIds,
                                                              final boolean[] _sortPropertyAcendingStates)
    {
        if ((null == _sortPropertyIds) != (null == _sortPropertyAcendingStates)) {
            throw new IllegalArgumentException(
                    "One array is NULL and the other is not (_sortPropertyIds, _sortPropertyAcendingStates)");
        }
        if ((null != _sortPropertyIds) && (_sortPropertyIds.length != _sortPropertyAcendingStates.length)) {
            throw new IllegalArgumentException(
                    "Arrays have diffenent size (_sortPropertyIds, _sortPropertyAcendingStates)");
        }

        sortPropertyIds = _sortPropertyIds;
        sortPropertyAscendingStates = _sortPropertyAcendingStates;
        return this;
    }

    /**
     * @see org.vaadin.test.web.prototype.QueryDefinition#getFilters()
     */
    @Override
    public Collection<Filter> getFilters()
    {
        return filters;
    }

    /**
     * Adds the filters.
     *
     * @param _filters
     *            the _filters
     * @return the query definition
     */
    public GenericQueryDefinition<BEANTYPE> addFilters(final Filter... _filters)
    {
        final ArrayList<Filter> filters2add = new ArrayList<Filter>(_filters.length);
        Collections.addAll(filters2add, _filters);
        filters.addAll(filters2add);
        return this;
    }

    /**
     * Removes the filter.
     *
     * @param _filter
     *            the _filter
     * @return the query definition
     */
    public GenericQueryDefinition<BEANTYPE> removeFilter(final Filter _filter)
    {
        if (null != _filter) {
            filters.remove(_filter);
        }
        return this;
    }

    /**
     * Removes the all filters.
     *
     * @return the query definition
     */
    public GenericQueryDefinition<BEANTYPE> removeAllFilters()
    {
        filters.clear();
        return this;
    }
}
