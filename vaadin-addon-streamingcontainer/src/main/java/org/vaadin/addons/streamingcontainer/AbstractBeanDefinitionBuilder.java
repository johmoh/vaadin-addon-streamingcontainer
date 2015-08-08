package org.vaadin.addons.streamingcontainer;

import org.vaadin.addons.streamingcontainer.generic.GenericBeanDefinition;
import org.vaadin.addons.streamingcontainer.generic.GenericPropertyDefinition;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractBeanDefinitionBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 * @param <SUBTYPE>
 *            the generic type
 */
public abstract class AbstractBeanDefinitionBuilder<BEANTYPE, SUBTYPE extends AbstractBeanDefinitionBuilder<BEANTYPE, SUBTYPE>>
    implements
        BeanDefinitionBuilder<BEANTYPE>
{
    /** The prototype. */
    private final GenericBeanDefinition<BEANTYPE> prototype;

    /**
     * Instantiates a new abstract bean definition builder.
     *
     * @param _type
     *            the _type
     */
    protected AbstractBeanDefinitionBuilder(final Class<BEANTYPE> _type)
    {
        if (null == _type) {
            throw new NullPointerException("_type is NULL");
        }

        prototype = new GenericBeanDefinition<BEANTYPE>(_type);
    }

    /**
     * Gets the this.
     *
     * @return the this
     */
    protected SUBTYPE getThis()
    {
        @SuppressWarnings("unchecked")
        final SUBTYPE __this = (SUBTYPE) this;
        return __this;
    }

    /**
     * Gets the prototype.
     *
     * @return the prototype
     */
    protected GenericBeanDefinition<BEANTYPE> getPrototype()
    {
        return prototype;
    }

    /**
     * Sets the id property id.
     *
     * @param _idPropertyId
     *            the _id property id
     * @return the subtype
     */
    public SUBTYPE setIdPropertyId(final Object _idPropertyId)
    {
        prototype.setIdPropertyId(_idPropertyId);
        return getThis();
    }

    /**
     * Adds the or set property definition.
     *
     * @param _propertyDefinition
     *            the _property definition
     * @return the subtype
     */
    public SUBTYPE addOrSetPropertyDefinition(final PropertyDefinition _propertyDefinition)
    {
        if (null == _propertyDefinition) {
            throw new NullPointerException("_propertyDefinition is NULL");
        }

        prototype.addOrSetPropertyDefinition(_propertyDefinition);
        return getThis();
    }

    /**
     * Gets the as generic property definition.
     *
     * @param _propertyId
     *            the _property id
     * @return the as generic property definition
     */
    public GenericPropertyDefinition getAsGenericPropertyDefinition(final Object _propertyId)
    {
        if (null == _propertyId) {
            throw new NullPointerException("_propertyId is NULL");
        }

        final PropertyDefinition propertyDefinition = prototype.getPropertyDefinition(_propertyId);
        if (null == propertyDefinition) {
            throw new IllegalArgumentException("Property definition with id \"" + String.valueOf(_propertyId)
                    + "\" does not exist");
        }

        final GenericPropertyDefinition result;
        if (propertyDefinition instanceof GenericPropertyDefinition) {
            result = (GenericPropertyDefinition) propertyDefinition;
        }
        else {
            result = new GenericPropertyDefinition(propertyDefinition);
            prototype.addOrSetPropertyDefinition(result);
        }

        return result;
    }

    /**
     * Sets the property definition type.
     *
     * @param _propertyId
     *            the _property id
     * @param _type
     *            the _type
     * @return the subtype
     */
    public SUBTYPE setPropertyDefinitionType(final Object _propertyId, final Class<?> _type)
    {
        if (null == _type) {
            throw new NullPointerException("_type is NULL");
        }

        final GenericPropertyDefinition propertyDefinition = getAsGenericPropertyDefinition(_propertyId);
        if (!propertyDefinition.getType().equals(_type)) {
            propertyDefinition.setType(_type);
        }

        return getThis();
    }

    /**
     * Sets the property definition default value.
     *
     * @param _propertyId
     *            the _property id
     * @param _defaultValue
     *            the _default value
     * @return the subtype
     */
    public SUBTYPE setPropertyDefinitionDefaultValue(final Object _propertyId, final Object _defaultValue)
    {
        final GenericPropertyDefinition propertyDefinition = getAsGenericPropertyDefinition(_propertyId);
        if (propertyDefinition.getDefaultValue() != _defaultValue) {
            propertyDefinition.setDefaultValue(_defaultValue);
        }

        return getThis();
    }

    /**
     * Sets the property definition read only.
     *
     * @param _propertyId
     *            the _property id
     * @param _readOnly
     *            the _read only
     * @return the subtype
     */
    public SUBTYPE setPropertyDefinitionReadOnly(final Object _propertyId, final boolean _readOnly)
    {
        final GenericPropertyDefinition propertyDefinition = getAsGenericPropertyDefinition(_propertyId);
        if (propertyDefinition.isReadOnly() != _readOnly) {
            propertyDefinition.setReadOnly(_readOnly);
        }

        return getThis();
    }

    /**
     * Sets the property definition sortable.
     *
     * @param _propertyId
     *            the _property id
     * @param _sortable
     *            the _sortable
     * @return the subtype
     */
    public SUBTYPE setPropertyDefinitionSortable(final Object _propertyId, final boolean _sortable)
    {
        final GenericPropertyDefinition propertyDefinition = getAsGenericPropertyDefinition(_propertyId);
        if (propertyDefinition.isSortable() != _sortable) {
            propertyDefinition.setSortable(_sortable);
        }

        return getThis();
    }

    /**
     * Removes the property definition.
     *
     * @param _propertyId
     *            the _property id
     * @return the subtype
     */
    public SUBTYPE removePropertyDefinition(final Object _propertyId)
    {
        prototype.removePropertyDefinition(_propertyId);
        return getThis();
    }

    /**
     * Removes the all property definitions.
     *
     * @return the subtype
     */
    public SUBTYPE removeAllPropertyDefinitions()
    {
        prototype.removeAllPropertyDefinitions();
        return getThis();
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinitionBuilder#build()
     */
    @Override
    public BeanDefinition<BEANTYPE> build()
    {
        final GenericBeanDefinition<BEANTYPE> result = new GenericBeanDefinition<BEANTYPE>(prototype);
        return result;
    }
}
