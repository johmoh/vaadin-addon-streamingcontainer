package org.vaadin.addons.streamingcontainer;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusPropertyDefinition.
 */
public class StatusPropertyDefinition implements PropertyDefinition, Serializable
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3466535417173743237L;

    /** The Constant instance. */
    private static final StatusPropertyDefinition instance = new StatusPropertyDefinition();

    /**
     * Instantiates a new status bean property definition.
     */
    private StatusPropertyDefinition()
    {
        // INTENTIONALLY LEFT BLANK
    }

    /**
     * Gets the single instance of StatusBeanPropertyDefinition.
     *
     * @return single instance of StatusBeanPropertyDefinition
     */
    public static StatusPropertyDefinition getInstance()
    {
        return instance;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getId()
     */
    @Override
    public Object getId()
    {
        return Constants.STATUS_PROPERTY_ID;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getType()
     */
    @Override
    public Class<?> getType()
    {
        return Constants.STATUS_PROPERTY_TYPE;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getDefaultValue()
     */
    @Override
    public Object getDefaultValue()
    {
        return null;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#isReadOnly()
     */
    @Override
    public boolean isReadOnly()
    {
        return true;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#isSortable()
     */
    @Override
    public boolean isSortable()
    {
        return false;
    }
}
