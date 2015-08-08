package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * The Interface Item.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface Item<BEANTYPE> extends com.vaadin.data.Item, com.vaadin.data.Item.PropertySetChangeNotifier
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
