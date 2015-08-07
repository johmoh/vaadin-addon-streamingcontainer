/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer.events;

import com.vaadin.data.Container;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemAddEventImpl.
 */
public class ItemAddEventImpl implements Container.Indexed.ItemAddEvent
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5228870505701004514L;

    /** The container. */
    private final Container container;

    /** The start idx. */
    private final int startIdx;

    /** The start item id. */
    private final Object startItemId;

    /** The num of added items. */
    private final int numOfAddedItems;

    /**
     * Instantiates a new item add event impl.
     *
     * @param _container
     *            the _container
     * @param _startIdx
     *            the _start idx
     * @param _startItemId
     *            the _start item id
     * @param _numOfAddedItems
     *            the _num of added items
     */
    public ItemAddEventImpl(final Container _container,
                            final int _startIdx,
                            final Object _startItemId,
                            final int _numOfAddedItems)
    {
        this.container = _container;
        this.startIdx = _startIdx;
        this.startItemId = _startItemId;
        this.numOfAddedItems = _numOfAddedItems;
    }

    /**
     * @see com.vaadin.data.Container.ItemSetChangeEvent#getContainer()
     */
    @Override
    public Container getContainer()
    {
        return container;
    }

    /**
     * @see com.vaadin.data.Container.Indexed.ItemAddEvent#getFirstItemId()
     */
    @Override
    public Object getFirstItemId()
    {
        return startItemId;
    }

    /**
     * @see com.vaadin.data.Container.Indexed.ItemAddEvent#getFirstIndex()
     */
    @Override
    public int getFirstIndex()
    {
        return startIdx;
    }

    /**
     * @see com.vaadin.data.Container.Indexed.ItemAddEvent#getAddedItemsCount()
     */
    @Override
    public int getAddedItemsCount()
    {
        return numOfAddedItems;
    }
}
