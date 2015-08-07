/*
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.util.ArrayList;
import java.util.Collection;

import org.vaadin.addons.streamingcontainer.events.ItemAddEventImpl;
import org.vaadin.addons.streamingcontainer.events.ItemSetChangeEventImpl;
import org.vaadin.addons.streamingcontainer.events.PropertySetChangeEventImpl;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractStreamingContainer.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public abstract class AbstractStreamingContainer<BEANTYPE> implements StreamingContainer<BEANTYPE>
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2374091247518141937L;

    /** The query factory. */
    private final QueryFactory<BEANTYPE> queryFactory;

    /** The query definition. */
    private QueryDefinition<BEANTYPE> queryDefinition = null;

    /** The item set change listeners. */
    private final Collection<ItemSetChangeListener> itemSetChangeListeners = new ArrayList<ItemSetChangeListener>();

    /** The property set change listeners. */
    private final Collection<PropertySetChangeListener> propertySetChangeListeners = new ArrayList<PropertySetChangeListener>();

    /*************************************************************************
     * CONSTRUCTOR
     *************************************************************************/

    /**
     * Instantiates a new abstract streaming container.
     */
    protected AbstractStreamingContainer(final QueryFactory<BEANTYPE> _queryFactory,
                                         final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        if (null == _queryFactory) {
            throw new NullPointerException("_queryFactory is NULL");
        }
        if (null == _queryDefinition) {
            throw new NullPointerException("_queryDefinition is NULL");
        }

        this.queryFactory = _queryFactory;
        this.queryDefinition = _queryDefinition;
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

    /*************************************************************************
     * QUERY DEFINITION CONFIGURATION
     *************************************************************************/

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainer#getQueryDefinition()
     */
    @Override
    public QueryDefinition<BEANTYPE> getQueryDefinition()
    {
        return queryDefinition;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainer#setQueryDefinition(org.vaadin.addons.streamingcontainer.QueryDefinition)
     */
    @Override
    public void setQueryDefinition(final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        if (null == _queryDefinition) {
            throw new NullPointerException("_queryDefinition is NULL");
        }

        if (!this.queryDefinition.equals(_queryDefinition)) {
            this.queryDefinition = _queryDefinition;
            refresh();
        }
    }

    /*************************************************************************
     * ITEM SET ACCESS MANAGEMENT
     *************************************************************************/

    /**
     * Refresh.
     */
    protected abstract void refresh();

    /*************************************************************************
     * ITEM SET ACCESS MANAGEMENT
     *************************************************************************/

    /**
     * @see com.vaadin.data.Container#containsId(java.lang.Object)
     */
    @Override
    public boolean containsId(final Object _itemId)
    {
        final int index = indexOfId(_itemId);
        final boolean result = (index >= 0);
        return result;
    }

    /**
     * @see com.vaadin.data.Container.Ordered#prevItemId(java.lang.Object)
     */
    @Override
    public Object prevItemId(final Object _itemId)
    {
        final int index = indexOfId(_itemId);
        final Object result = ((index <= 0) ? null : getIdByIndex(index - 1));
        return result;
    }

    /**
     * @see com.vaadin.data.Container.Ordered#nextItemId(java.lang.Object)
     */
    @Override
    public Object nextItemId(final Object _itemId)
    {
        final int index = indexOfId(_itemId);
        final Object result = ((index < 0) ? null : getIdByIndex(index + 1));
        return result;
    }

    /**
     * @see com.vaadin.data.Container.Ordered#firstItemId()
     */
    @Override
    public Object firstItemId()
    {
        final int size = size();
        final Object result = ((size <= 0) ? null : getIdByIndex(0));
        return result;
    }

    /**
     * @see com.vaadin.data.Container.Ordered#isFirstId(java.lang.Object)
     */
    @Override
    public boolean isFirstId(final Object _itemId)
    {
        final boolean result = ((null == _itemId) ? false : _itemId.equals(firstItemId()));
        return result;
    }

    /**
     * @see com.vaadin.data.Container.Ordered#lastItemId()
     */
    @Override
    public Object lastItemId()
    {
        final int size = size();
        final Object result = ((size <= 0) ? null : getIdByIndex(size - 1));
        return result;
    }

    /**
     * @see com.vaadin.data.Container.Ordered#isLastId(java.lang.Object)
     */
    @Override
    public boolean isLastId(final Object _itemId)
    {
        final boolean result = ((null == _itemId) ? false : _itemId.equals(lastItemId()));
        return result;
    }

    /*************************************************************************
     * ITEM SET MODIFICATION
     *************************************************************************/

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container#addItem(java.lang.Object)}
     */
    @Override
    @Deprecated
    public Item addItem(final Object _itemId)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container#addItem()}
     */
    @Override
    @Deprecated
    public Object addItem()
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container.Indexed#addItemAt(int)}
     */
    @Override
    @Deprecated
    public Object addItemAt(final int _index)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container.Indexed#addItemAt(int, java.lang.Object)}
     */
    @Override
    @Deprecated
    public Item addItemAt(final int _index, final Object _newItemId)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container.Ordered#addItemAfter(java.lang.Object)}
     */
    @Override
    @Deprecated
    public Object addItemAfter(final Object _previousItemId)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container.Ordered#addItemAfter(java.lang.Object, java.lang.Object)}
     */
    @Override
    @Deprecated
    public Item addItemAfter(final Object _previousItemId, final Object _newItemId)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container#removeItem(java.lang.Object)}
     */
    @Override
    @Deprecated
    public boolean removeItem(final Object _itemId)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container#removeAllItems()}
     */
    @Override
    @Deprecated
    public boolean removeAllItems()
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /*************************************************************************
     * ITEM SET CHANGED
     *************************************************************************/

    /**
     * @see com.vaadin.data.Container.ItemSetChangeNotifier#addItemSetChangeListener(com.vaadin.data.Container.ItemSetChangeListener)
     */
    @Override
    public void addItemSetChangeListener(final ItemSetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        itemSetChangeListeners.add(_listener);
    }

    /**
     * @see com.vaadin.data.Container.ItemSetChangeNotifier#removeItemSetChangeListener(com.vaadin.data.Container.ItemSetChangeListener)
     */
    @Override
    public void removeItemSetChangeListener(final ItemSetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        itemSetChangeListeners.remove(_listener);
    }

    /**
     * @see com.vaadin.data.Container.ItemSetChangeNotifier#addListener(com.vaadin.data.Container.ItemSetChangeListener)
     */
    @Override
    @Deprecated
    public void addListener(final ItemSetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        addItemSetChangeListener(_listener);
    }

    /**
     * @see com.vaadin.data.Container.ItemSetChangeNotifier#removeListener(com.vaadin.data.Container.ItemSetChangeListener)
     */
    @Override
    @Deprecated
    public void removeListener(final ItemSetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        removeItemSetChangeListener(_listener);
    }

    /**
     * Notify item set changed.
     */
    protected void fireItemSetChanged()
    {
        if (!itemSetChangeListeners.isEmpty()) {
            final ItemSetChangeEvent event = new ItemSetChangeEventImpl(this);
            for (final ItemSetChangeListener listener : itemSetChangeListeners) {
                listener.containerItemSetChange(event);
            }
        }
    }

    /**
     * Fire item add event.
     *
     * @param _startIndex
     *            the _start index
     * @param _numOfAddedItems
     *            the _num of added items
     */
    protected void fireItemAddEvent(final int _startIndex, final int _numOfAddedItems)
    {
        if (!itemSetChangeListeners.isEmpty() && (_numOfAddedItems > 0)) {
            final Object startItemId = getIdByIndex(_startIndex);
            final Container.Indexed.ItemAddEvent event = new ItemAddEventImpl(this, _startIndex, startItemId,
                    _numOfAddedItems);
            for (final ItemSetChangeListener listener : itemSetChangeListeners) {
                listener.containerItemSetChange(event);
            }
        }
    }

    /*************************************************************************
     * PROPERTIES CONFIGURATION
     *************************************************************************/

    /**
     * @see com.vaadin.data.Container#getContainerPropertyIds()
     */
    @Override
    public Collection<?> getContainerPropertyIds()
    {
        final QueryDefinition<BEANTYPE> queryDefinition = getQueryDefinition();
        final BeanDefinition<BEANTYPE> beanDefinition = queryDefinition.getBeanDefinition();
        final Collection<Object> result = beanDefinition.getPropertyIds();
        return result;
    }

    /**
     * @see com.vaadin.data.Container#getContainerProperty(java.lang.Object, java.lang.Object)
     */
    @Override
    public Property<?> getContainerProperty(final Object _itemId, final Object _propertyId)
    {
        final Item item = getItem(_itemId);
        final Property<?> result = item.getItemProperty(_propertyId);
        return result;
    }

    /**
     * @see com.vaadin.data.Container#getType(java.lang.Object)
     */
    @Override
    public Class<?> getType(final Object _propertyId)
    {
        final QueryDefinition<BEANTYPE> queryDefinition = getQueryDefinition();
        final BeanDefinition<BEANTYPE> beanDefinition = queryDefinition.getBeanDefinition();
        final BeanPropertyDefinition propertyDefinition = beanDefinition.getPropertyDefinition(_propertyId);
        final Class<?> result = propertyDefinition.getType();
        return result;
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container#addContainerProperty(java.lang.Object, java.lang.Class, java.lang.Object)}
     */
    @Override
    @Deprecated
    public boolean addContainerProperty(final Object _propertyId, final Class<?> _type, final Object _defaultValue)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <b>NOT SUPPORTED!<b> <br>
     * <br>
     * {@inheritDoc com.vaadin.data.Container#removeContainerProperty(java.lang.Object)}
     */
    @Override
    @Deprecated
    public boolean removeContainerProperty(final Object _propertyId)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /*************************************************************************
     * PROPERTY SET CHANGED
     *************************************************************************/

    /**
     * @see com.vaadin.data.Container.PropertySetChangeNotifier#addPropertySetChangeListener(com.vaadin.data.Container.PropertySetChangeListener)
     */
    @Override
    public void addPropertySetChangeListener(final PropertySetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        propertySetChangeListeners.add(_listener);
    }

    /**
     * @see com.vaadin.data.Container.PropertySetChangeNotifier#removePropertySetChangeListener(com.vaadin.data.Container.PropertySetChangeListener)
     */
    @Override
    public void removePropertySetChangeListener(final PropertySetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        propertySetChangeListeners.remove(_listener);
    }

    /**
     * @see com.vaadin.data.Container.PropertySetChangeNotifier#addListener(com.vaadin.data.Container.PropertySetChangeListener)
     */
    @Override
    @Deprecated
    public void addListener(final PropertySetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        addPropertySetChangeListener(_listener);
    }

    /**
     * @see com.vaadin.data.Container.PropertySetChangeNotifier#removeListener(com.vaadin.data.Container.PropertySetChangeListener)
     */
    @Override
    @Deprecated
    public void removeListener(final PropertySetChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        removePropertySetChangeListener(_listener);
    }

    /**
     * Notify property set changed.
     */
    protected void firePropertySetChanged()
    {
        if (!propertySetChangeListeners.isEmpty()) {
            final PropertySetChangeEvent event = new PropertySetChangeEventImpl(this);
            for (final PropertySetChangeListener listener : propertySetChangeListeners) {
                listener.containerPropertySetChange(event);
            }
        }
    }
}
