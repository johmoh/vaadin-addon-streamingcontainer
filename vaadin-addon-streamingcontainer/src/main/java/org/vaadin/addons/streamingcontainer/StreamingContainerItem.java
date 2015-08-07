package org.vaadin.addons.streamingcontainer;

import com.vaadin.data.Item;

// TODO: Auto-generated Javadoc
/**
 * The Interface StreamingContainerItem.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface StreamingContainerItem<BEANTYPE> extends Item, Item.PropertySetChangeNotifier, Cloneable
{
    /**
     * Gets the bean.
     *
     * @return the bean
     */
    public BEANTYPE getBean();

    /**
     * Gets the status.
     *
     * @return the status
     */
    public EStatus getStatus();

    /**
     * Sets the status.
     *
     * @param _status
     *            the new status
     */
    public void setStatus(final EStatus _status);
}
