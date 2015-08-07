/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer.events;

import com.vaadin.data.Container;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertySetChangeEventImpl.
 */
public class PropertySetChangeEventImpl implements Container.PropertySetChangeEvent
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3030407506894550611L;

    /** The container. */
    private final Container container;

    /**
     * Instantiates a new property set change event impl.
     *
     * @param _container
     *            the _container
     */
    public PropertySetChangeEventImpl(final Container _container)
    {
        this.container = _container;
    }

    /**
     * @see com.vaadin.data.Container.PropertySetChangeEvent#getContainer()
     */
    @Override
    public Container getContainer()
    {
        return container;
    }
}
