/**
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    private final GenericBeanDefinition<BEANTYPE> beanDefinition;

    /** The batch size hint. */
    private int batchSizeHint;

    /** The max query size hint. */
    private int maxQuerySizeHint;

    /** The additional parameter map. */
    private Map<Object, Object> additionalParameterMap = new HashMap<Object, Object>();

    /** The sort property ids. */
    public Object[] sortPropertyIds;

    /** The sort property acending states. */
    public boolean[] sortPropertyAcendingStates;

    /** The filters. */
    public ArrayList<Filter> filters = new ArrayList<Filter>();

    /**
     * Instantiates a new generic query definition.
     *
     * @param _beanDefinition
     *            the _bean definition
     */
    public GenericQueryDefinition(final BeanDefinition<BEANTYPE> _beanDefinition)
    {
        this(_beanDefinition, Defaults.DEFAULT_BATCH_SIZE_HINT, Defaults.DEFAULT_MAX_QUERY_SIZE_HINT);
    }

    /**
     * Instantiates a new generic query definition.
     *
     * @param _beanDefinition
     *            the _bean definition
     * @param _batchSizeHint
     *            the _batch size hint
     */
    public GenericQueryDefinition(final BeanDefinition<BEANTYPE> _beanDefinition, final int _batchSizeHint)
    {
        this(_beanDefinition, _batchSizeHint, Defaults.DEFAULT_MAX_QUERY_SIZE_HINT);
    }

    /**
     * Instantiates a new generic query definition.
     *
     * @param _beanDefinition
     *            the _bean definition
     * @param _batchSizeHint
     *            the _batch size hint
     * @param _maxQuerySizeHint
     *            the _max query size hint
     */
    public GenericQueryDefinition(final BeanDefinition<BEANTYPE> _beanDefinition,
                                  final int _batchSizeHint,
                                  final int _maxQuerySizeHint)
    {
        this.beanDefinition = new GenericBeanDefinition<BEANTYPE>(_beanDefinition);
        this.batchSizeHint = _batchSizeHint;
        this.maxQuerySizeHint = _maxQuerySizeHint;
        this.sortPropertyIds = new Object[0];
        this.sortPropertyAcendingStates = new boolean[0];
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
        this.sortPropertyIds = _prototype.getSortPropertyIds().clone();
        this.sortPropertyAcendingStates = _prototype.getSortPropertyAcendingStates().clone();
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
     * @see org.vaadin.test.web.prototype.QueryDefinition#getSortPropertyIds()
     */
    @Override
    public Object[] getSortPropertyIds()
    {
        return sortPropertyIds;
    }

    /**
     * @see org.vaadin.test.web.prototype.QueryDefinition#getSortPropertyAcendingStates()
     */
    @Override
    public boolean[] getSortPropertyAcendingStates()
    {
        return sortPropertyAcendingStates;
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
        sortPropertyIds = _sortPropertyIds;
        sortPropertyAcendingStates = _sortPropertyAcendingStates;
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
