package org.vaadin.addons.streamingcontainer;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusBeanPropertyDefinition.
 */
public class StatusBeanPropertyDefinition implements BeanPropertyDefinition, Serializable
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3466535417173743237L;

    /** The Constant instance. */
    private static final StatusBeanPropertyDefinition instance = new StatusBeanPropertyDefinition();

    /**
     * Instantiates a new status bean property definition.
     */
    private StatusBeanPropertyDefinition()
    {
        // INTENTIONALLY LEFT BLANK
    }

    /**
     * Gets the single instance of StatusBeanPropertyDefinition.
     *
     * @return single instance of StatusBeanPropertyDefinition
     */
    public static StatusBeanPropertyDefinition getInstance()
    {
        return instance;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanPropertyDefinition#getId()
     */
    @Override
    public Object getId()
    {
        return Constants.STATUS_PROPERTY_ID;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanPropertyDefinition#getType()
     */
    @Override
    public Class<?> getType()
    {
        return Constants.STATUS_PROPERTY_TYPE;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanPropertyDefinition#getDefaultValue()
     */
    @Override
    public Object getDefaultValue()
    {
        return null;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanPropertyDefinition#isReadOnly()
     */
    @Override
    public boolean isReadOnly()
    {
        return true;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanPropertyDefinition#isSortable()
     */
    @Override
    public boolean isSortable()
    {
        return false;
    }
}
