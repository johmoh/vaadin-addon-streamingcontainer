/**
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.vaadin.addons.streamingcontainer.Constants.Limits;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.UnsupportedFilterException;

// TODO: Auto-generated Javadoc
/**
 * @author johmoh
 */
public class LazyStreamingContainer<BEANTYPE> extends AbstractStreamingContainer<BEANTYPE>
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8027529229449065312L;

    /** The initialized. */
    private boolean initialized = false;

    /** The query factory. */
    private QueryFactory<BEANTYPE> queryFactory = null;

    /** The query definition. */
    private QueryDefinition<BEANTYPE> queryDefinition = null;

    /** The query. */
    private Query<BEANTYPE> query = null;

    /** The end of data. */
    private boolean endOfStream = false;

    /** The has more. */
    private Boolean hasMore = null;

    /** The load till end of stream on initialization. */
    private boolean loadTillEndOfStreamOnInitialization = false;

    /** The index2item list. */
    private final ArrayList<Item> index2itemList = new ArrayList<Item>();

    /** The index2item id list. */
    private final ArrayList<Object> index2itemIdList = new ArrayList<Object>();

    /** The item id2index map. */
    private final HashMap<Object, Integer> itemId2indexMap = new HashMap<Object, Integer>();

    /*************************************************************************
     * CONSTRUCTOR
     *************************************************************************/

    /**
     * Instantiates a new query container.
     *
     * @param _queryFactory
     *            the _query factory
     * @param _queryDefinition
     *            the _query definition
     */
    public LazyStreamingContainer(final QueryFactory<BEANTYPE> _queryFactory,
                                  final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        super();
        this.queryFactory = _queryFactory;
        this.queryDefinition = _queryDefinition;
    }

    /**
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize()
        throws Throwable
    {
        try {
            disposeQuery();
        }
        catch (final Throwable _t) {
        }
        finally {
            super.finalize();
        }
    }

    /**
     * Initialize query.
     */
    private void initializeQuery()
    {
        // System.out.println("CALL LazyStreamingQueryContainer.initializeQuery()");
        if (null == query) {
            if (null != queryFactory) {
                // TODO
                query = queryFactory.createQuery( //
                        queryDefinition, //
                        Constants.EMPTY_ADDITIONAL_FILTERS, //
                        Constants.EMPTY_SORT_PROPERTY_IDS, //
                        Constants.EMPTY_SORT_PROPERTY_ASCENDING_STATES //
                    );
            }

            if (null == query) {
                endOfStream = true;
                hasMore = Boolean.FALSE;
            }
            else {
                endOfStream = false;
                hasMore = null;
            }
        }
    }

    /**
     * Dispose query.
     */
    private void disposeQuery()
    {
        // System.out.println("CALL LazyStreamingQueryContainer.disposeQuery()");
        endOfStream = true;
        hasMore = null;
        if (null != query) {
            query.closeStream();
            query = null;
        }
    }

    /**
     * Clear cache.
     */
    private void clearCache()
    {
        index2itemList.clear();
        index2itemList.trimToSize();
        index2itemIdList.clear();
        index2itemIdList.trimToSize();
        itemId2indexMap.clear();
    }

    /**
     * Refresh.
     */
    private void refresh()
    {
        System.out.println("CALL LazyStreamingQueryContainer.refresh()");

        initialized = false;

        disposeQuery();
        clearCache();

        fireItemSetChanged();
    }

    /**
     * Initialize.
     */
    private void initialize()
    {
        // System.out.println("CALL LazyStreamingQueryContainer.initialize()");
        initializeQuery();
        clearCache();
        initialized = true;

        // Load initial batch of items
        if (!endOfStream) {
            if (loadTillEndOfStreamOnInitialization) {
                loadTillEndOfStream();
            }
            else {
                loadNextItemsFromStream(false);
            }
        }
    }

    /**
     * Load next items from stream.
     *
     * @param _fireItemSetChangedEvent
     *            the _fire item set changed event
     */
    private void loadNextItemsFromStream(final boolean _fireItemSetChangedEvent)
    {
        // System.out.println("CALL LazyStreamingQueryContainer.loadNextItemsFromStream("
        // + _fireItemSetChangedEvent + ")");
        assert (initialized);
        if (endOfStream) {
            return;
        }

        final int size = getNumberOfLoadedItems();
        final int startIndex = Math.max(0, size);
        final int batchSizeHint = Math.max(0, queryDefinition.getBatchSizeHint());
        loadNextItemsFromStream(startIndex, batchSizeHint, _fireItemSetChangedEvent);
    }

    /**
     * TODO: DIRECT DATA ACCESS!!!<br>
     * <br>
     * Load next items from stream.
     *
     * @param _startIndex
     *            the _start index
     * @param _numberOfItems
     *            the _number of items
     * @param _fireItemSetChangedEvent
     *            the _fire item set changed event
     */
    private void loadNextItemsFromStream(final int _startIndex,
                                         final int _numberOfItems,
                                         final boolean _fireItemSetChangedEvent)
    {
        // System.out.println("CALL LazyStreamingQueryContainer.loadNextItemsFromStream("
        // + _startIndex + ", " + _numberOfItems + ", " +
        // _fireItemSetChangedEvent + ")");
        assert (initialized);
        if (endOfStream) {
            return;
        }

        final int size = getNumberOfLoadedItems();
        final int startIndex = Math.max(0, size);
        final int startIndexOffset = Math.max(0, _startIndex) - startIndex;
        final int numberOfItems = Math.max(0, _numberOfItems) + startIndexOffset;
        if (numberOfItems <= 0) {
            return;
        }

        final int batchSizeHint = queryDefinition.getBatchSizeHint();
        final int batchSizeMin = Math.max(Limits.MIN_BATCH_SIZE_LIMIT, ((startIndex > 0) ? 0
                : Limits.MIN_INITIAL_BATCH_SIZE_LIMIT));
        final int batchSize = Math.max(numberOfItems, Math.max(batchSizeHint, batchSizeMin));
        final int maxQuerySizeHint = Math.max(0, queryDefinition.getMaxQuerySizeHint());
        final int maxQuerySize = Math.max(0, Math.min(maxQuerySizeHint, Limits.MAX_NUMBER_OF_ITEMS_LIMIT));
        final int maxNumberOfItems = Math.max(0, Math.min(startIndex + batchSize, maxQuerySize) - startIndex);
        System.out.println("CALL LazyStreamingQueryContainer.loadNextItemsFromStream(...): startIndex=" + startIndex
                + " batchSize=" + batchSize + " maxNumberOfItems=" + maxNumberOfItems);
        if (maxNumberOfItems == 0) {
            endStreaming();
            return;
        }

        final Collection<BEANTYPE> loadedObjects = query.readNext(maxNumberOfItems);
        if ((null == loadedObjects) || loadedObjects.isEmpty()) {
            endStreaming();
            return;
        }
        else {
            hasMore = query.hasMore();
            if (!hasMore) {
                endStreaming();
            }
        }

        int idx = startIndex;
        boolean maxQuerySizeReached = false;
        final BeanDefinition<BEANTYPE> beanDefinition = queryDefinition.getBeanDefinition();
        final Class<BEANTYPE> beanType = beanDefinition.getType();
        final Object idPropertyId = beanDefinition.getIdPropertyId();
        for (final BEANTYPE object : loadedObjects) {
            if (maxQuerySizeReached) {
                hasMore = Boolean.TRUE;
                break;
            }

            if (null == object) {
                continue;
            }

            final BeanItem<BEANTYPE> beanItem = ((null == beanType) ? new BeanItem<BEANTYPE>(object)
                    : new BeanItem<BEANTYPE>(object, beanType));
            final Object itemId;
            if (null != idPropertyId) {
                final Property<?> idProperty = beanItem.getItemProperty(idPropertyId);
                itemId = idProperty.getValue();
            }
            else {
                itemId = object;
            }
            index2itemList.add(beanItem);
            index2itemIdList.add(itemId);
            itemId2indexMap.put(itemId, idx);

            ++idx;
            if (idx >= maxQuerySize) {
                maxQuerySizeReached = true;
            }
        }

        if (maxQuerySizeReached) {
            endStreaming();
        }

        if (_fireItemSetChangedEvent) {
            final int numOfAddedItems = idx - startIndex;
            fireItemAddEvent(startIndex, numOfAddedItems);
        }
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainer#loadTillEndOfStream()
     */
    @Override
    public void loadTillEndOfStream()
    {
        // System.out.println("CALL LazyStreamingQueryContainer.loadTillEndOfStream()");
        if (endOfStream) {
            return;
        }

        if (!initialized) {
            loadTillEndOfStreamOnInitialization = true;
            return;
        }
        loadTillEndOfStreamOnInitialization = false;

        int oldSize = getNumberOfLoadedItems();
        int newSize = oldSize;
        final int startIndex = oldSize;
        final int maxQuerySizeHint = queryDefinition.getMaxQuerySizeHint();
        do {
            loadNextItemsFromStream(0, maxQuerySizeHint, false);
            newSize = getNumberOfLoadedItems();
            if (newSize == oldSize) {
                endStreaming();
                break;
            }
            oldSize = newSize;
        } while (!endOfStream);

        final int numOfAddedItems = newSize - startIndex;
        if (numOfAddedItems > 0) {
            fireItemAddEvent(startIndex, numOfAddedItems);
        }
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainer#endStreaming()
     */
    @Override
    public void endStreaming()
    {
        endOfStream = true;
        if (null != query) {
            query.closeStream();
        }
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainer#hasStreamingEnded()
     */
    @Override
    public boolean hasStreamingEnded()
    {
        final boolean result = (initialized && endOfStream);
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainer#hasMore()
     */
    @Override
    public Boolean hasMore()
    {
        return hasMore;
    }

    /*************************************************************************
     * ITEM SET ACCESS MANAGEMENT
     *************************************************************************/

    /**
     * TODO: DIRECT DATA ACCESS!!! <br>
     * <br>
     * Gets the number of loaded items.
     *
     * @return the number of loaded items
     */
    public int getNumberOfLoadedItems()
    {
        // DO NOT CALL size() HERE!
        // Reason: size() returns the number of currently visible items. That
        // number might be less than the number of loaded items (e.g. because of
        // local filtering of items inside the container).
        final int result = (index2itemIdList.isEmpty() ? 0 : index2itemIdList.size());
        return result;
    }

    /**
     * TODO: DIRECT DATA ACCESS!!!
     *
     * @see com.vaadin.data.Container#size()
     */
    @Override
    public int size()
    {
        // System.out.println("CALL LazyStreamingQueryContainer.size()");
        if (!initialized) {
            initialize();
        }

        final int result = (index2itemIdList.isEmpty() ? 0 : index2itemIdList.size());
        return result;
    }

    /**
     * TODO: DIRECT DATA ACCESS!!!
     *
     * @see com.vaadin.data.Container.Indexed#indexOfId(java.lang.Object)
     */
    @Override
    public int indexOfId(final Object _itemId)
    {
        // System.out.println("CALL LazyStreamingQueryContainer.indexOfId(" +
        // _itemId + ")");
        final Integer indexObj = ((null == _itemId) ? null : itemId2indexMap.get(_itemId));
        final int result = ((null == indexObj) ? -1 : indexObj);
        return result;
    }

    /**
     * TODO: DIRECT DATA ACCESS!!!
     *
     * @see com.vaadin.data.Container.Indexed#getIdByIndex(int)
     */
    @Override
    public Object getIdByIndex(final int _index)
    {
        // System.out.println("CALL LazyStreamingQueryContainer.getIdByIndex(" +
        // _index + ")");
        final Object result;
        if (_index < 0) {
            result = null;
        }
        else {
            int size = size();
            if (!endOfStream && (size > 0) && (_index == (size - 1))) {
                loadNextItemsFromStream(true);
                size = size();
            }
            result = ((_index >= size) ? null : index2itemIdList.get(_index));
        }

        return result;
    }

    /**
     * TODO: DIRECT DATA ACCESS!!!
     *
     * @see com.vaadin.data.Container#getItem(java.lang.Object)
     */
    @Override
    public Item getItem(final Object _itemId)
    {
        // System.out.println("CALL LazyStreamingQueryContainer.getItem(" +
        // _itemId + ")");
        final int index = indexOfId(_itemId);
        final Item result = ((index < 0) ? null : index2itemList.get(index));
        return result;
    }

    /**
     * TODO: DIRECT DATA ACCESS!!!
     *
     * @see com.vaadin.data.Container#getItemIds()
     */
    @Override
    public Collection<?> getItemIds()
    {
        // System.out.println("CALL LazyStreamingQueryContainer.getItemIds()");
        final Collection<?> result = index2itemIdList;
        return result;
    }

    /**
     * TODO: DIRECT DATA ACCESS!!!
     *
     * @see com.vaadin.data.Container.Indexed#getItemIds(int, int)
     */
    @Override
    public List<?> getItemIds(final int _startIndex, final int _numberOfItems)
    {
        // System.out.println("CALL LazyStreamingQueryContainer.getItemIds(" +
        // _startIndex + ", " + _numberOfItems + ")");
        final List<?> result = index2itemIdList.subList(_startIndex, _startIndex + _numberOfItems);
        return result;
    }

    /*************************************************************************
     * QUERY FACTORY CONFIGURATION
     *************************************************************************/

    /**
     * Gets the query factory.
     *
     * @return the query factory
     */
    public QueryFactory<BEANTYPE> getQueryFactory()
    {
        return queryFactory;
    }

    /**
     * Sets the query factory.
     *
     * @param _queryFactory
     *            the new query factory
     */
    public void setQueryFactory(final QueryFactory<BEANTYPE> _queryFactory)
    {
        if (this.queryFactory != _queryFactory) {
            this.queryFactory = _queryFactory;
            refresh();
        }
    }

    /*************************************************************************
     * QUERY DEFINITION CONFIGURATION
     *************************************************************************/

    /**
     * Gets the query definition.
     *
     * @return the query definition
     */
    @Override
    public QueryDefinition<BEANTYPE> getQueryDefinition()
    {
        return queryDefinition;
    }

    /**
     * Sets the query definition.
     *
     * @param _queryDefinition
     *            the new query definition
     */
    @Override
    public void setQueryDefinition(final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        this.queryDefinition = new GenericQueryDefinition<BEANTYPE>(_queryDefinition);
        refresh();
    }

    /*************************************************************************
     * FILTER CONFIGURATION
     *************************************************************************/

    /**
     * @see com.vaadin.data.Container.Filterable#getContainerFilters()
     */
    @Override
    public Collection<Filter> getContainerFilters()
    {
        // TODO
        final Collection<Filter> result = null;
        return result;
    }

    /**
     * @see com.vaadin.data.Container.Filterable#addContainerFilter(com.vaadin.data.Container.Filter)
     */
    @Override
    public void addContainerFilter(final Filter _filter)
        throws UnsupportedFilterException
    {
        // TODO
        refresh();
    }

    /**
     * @see com.vaadin.data.Container.Filterable#removeContainerFilter(com.vaadin.data.Container.Filter)
     */
    @Override
    public void removeContainerFilter(final Filter _filter)
    {
        // TODO
        refresh();
    }

    /**
     * @see com.vaadin.data.Container.Filterable#removeAllContainerFilters()
     */
    @Override
    public void removeAllContainerFilters()
    {
        // TODO
        refresh();
    }

    /*************************************************************************
     * SORT ORDER CONFIGURATION
     *************************************************************************/

    /**
     * @see com.vaadin.data.Container.Sortable#sort(java.lang.Object[],
     *      boolean[])
     */
    @Override
    public void sort(final Object[] _sortPropertyIds, final boolean[] _sortPropertyAcendingStates)
    {
        // TODO
        refresh();
    }

    /**
     * @see com.vaadin.data.Container.Sortable#getSortableContainerPropertyIds()
     */
    @Override
    public Collection<?> getSortableContainerPropertyIds()
    {
        final BeanDefinition<BEANTYPE> beanDefinition = queryDefinition.getBeanDefinition();
        final Collection<Object> result = beanDefinition.getIdsOfSortableDefinitions();
        return result;
    }
}
