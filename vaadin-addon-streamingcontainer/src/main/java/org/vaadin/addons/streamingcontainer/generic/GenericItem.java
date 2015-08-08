package org.vaadin.addons.streamingcontainer.generic;

import org.vaadin.addons.streamingcontainer.BeanDefinition;
import org.vaadin.addons.streamingcontainer.Constants;
import org.vaadin.addons.streamingcontainer.EStatus;
import org.vaadin.addons.streamingcontainer.Item;
import org.vaadin.addons.streamingcontainer.StatusProperty;

import com.vaadin.data.util.BeanItem;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericItem.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericItem<BEANTYPE> //
    extends BeanItem<BEANTYPE> implements Item<BEANTYPE>
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
    public GenericItem(final BEANTYPE _bean, final BeanDefinition<BEANTYPE> _beanDefinition)
    {
        super(_bean, _beanDefinition.getPropertyIds());

        statusProperty = new GenericStatusProperty();
        super.addItemProperty(Constants.STATUS_PROPERTY_ID, statusProperty);
    }

    /**
     * @see com.vaadin.data.util.PropertysetItem#clone()
     */
    @Override
    public Object clone()
        throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException("\"clone\" method is not supported for type \""
                + this.getClass().getName() + "\"");
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.Item#getStatus()
     */
    @Override
    public EStatus getStatus()
    {
        final EStatus result = statusProperty.getValue();
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.Item#setStatus(org.vaadin.addons.streamingcontainer.EStatus)
     */
    @Override
    public void setStatus(final EStatus _status)
    {
        statusProperty.setStatus(_status);
    }
}
