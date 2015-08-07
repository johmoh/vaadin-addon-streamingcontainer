package org.vaadin.addons.streamingcontainer;

import com.vaadin.data.util.BeanItem;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericStreamingContainerItem.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericStreamingContainerItem<BEANTYPE> //
    extends BeanItem<BEANTYPE> implements StreamingContainerItem<BEANTYPE>
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6545756774837709296L;

    /** The status property. */
    private final StatusProperty statusProperty;

    /**
     * Instantiates a new generic streaming container item.
     *
     * @param _bean
     *            the _bean
     * @param _beanDefinition
     *            the _bean definition
     */
    public GenericStreamingContainerItem(final BEANTYPE _bean, final BeanDefinition<BEANTYPE> _beanDefinition)
    {
        super(_bean, _beanDefinition.getPropertyIds());

        statusProperty = new GenericStatusProperty();
        super.addItemProperty(Constants.STATUS_PROPERTY_ID, statusProperty);
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainerItem#getStatus()
     */
    @Override
    public EStatus getStatus()
    {
        final EStatus result = statusProperty.getValue();
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainerItem#setStatus(org.vaadin.addons.streamingcontainer.EStatus)
     */
    @Override
    public void setStatus(final EStatus _status)
    {
        statusProperty.setStatus(_status);
    }
}
