/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer.events;

import com.vaadin.data.Container;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemSetChangeEventImpl.
 */
public class ItemSetChangeEventImpl implements Container.ItemSetChangeEvent
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8681875188353361004L;

    /** The container. */
    private final Container container;

    /**
     * Instantiates a new item set change event impl.
     *
     * @param _container
     *            the _container
     */
    public ItemSetChangeEventImpl(final Container _container)
    {
        this.container = _container;
    }

    /**
     * @see com.vaadin.data.Container.ItemSetChangeEvent#getContainer()
     */
    @Override
    public Container getContainer()
    {
        return container;
    }
}
