package org.vaadin.addons.streamingcontainer;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyDescriptorBasedPropertyDefinition.
 */
public class PropertyDescriptorBasedPropertyDefinition implements PropertyDefinition
{
    /** The property descriptor. */
    private PropertyDescriptor propertyDescriptor;

    /** The read only. */
    private boolean readOnly = true;

    /** The sortable. */
    private boolean sortable = false;

    /**
     * Instantiates a new property descriptor based bean property definition.
     *
     * @param _propertyDescriptor
     *            the _property descriptor
     */
    public PropertyDescriptorBasedPropertyDefinition(final PropertyDescriptor _propertyDescriptor)
    {
        setPropertyDescriptor(_propertyDescriptor);
    }

    /**
     * Gets the property descriptor.
     *
     * @return the property descriptor
     */
    public PropertyDescriptor getPropertyDescriptor()
    {
        return propertyDescriptor;
    }

    /**
     * Sets the property descriptor.
     *
     * @param _propertyDescriptor
     *            the _property descriptor
     * @return the property descriptor based bean property definition
     */
    public PropertyDescriptorBasedPropertyDefinition setPropertyDescriptor(final PropertyDescriptor _propertyDescriptor)
    {
        if (null == _propertyDescriptor) {
            throw new NullPointerException("_propertyDescriptor is NULL");
        }

        propertyDescriptor = _propertyDescriptor;
        readOnly = isReadOnly();

        return this;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getId()
     */
    @Override
    public Object getId()
    {
        final String result = propertyDescriptor.getName();
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getType()
     */
    @Override
    public Class<?> getType()
    {
        final Class<?> result = propertyDescriptor.getPropertyType();
        return result;
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
        final Method setter = propertyDescriptor.getWriteMethod();
        final boolean result = ((null == setter) || readOnly);
        return result;
    }

    /**
     * Sets the read only.
     *
     * @param _readOnly
     *            the _read only
     * @return the property descriptor based bean property definition
     */
    public PropertyDescriptorBasedPropertyDefinition setReadOnly(final boolean _readOnly)
    {
        final Method setter = propertyDescriptor.getWriteMethod();
        if ((null == setter) && !_readOnly) {
            throw new IllegalArgumentException(
                    "Bean property definition cannot be set to \"not read-only\" because the property descriptor does not contain a \"setter\" (write method)");
        }

        readOnly = _readOnly;
        return this;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#isSortable()
     */
    @Override
    public boolean isSortable()
    {
        return sortable;
    }

    /**
     * Sets the sortable.
     *
     * @param _sortable
     *            the _sortable
     * @return the property descriptor based bean property definition
     */
    public PropertyDescriptorBasedPropertyDefinition setSortable(final boolean _sortable)
    {
        sortable = _sortable;
        return this;
    }
}
