package org.vaadin.addons.streamingcontainer.generic;

import java.beans.PropertyDescriptor;
import java.util.Set;

import org.vaadin.addons.streamingcontainer.AbstractTypeBasedBeanDefinitionBuilder;
import org.vaadin.addons.streamingcontainer.PropertyDefinition;
import org.vaadin.addons.streamingcontainer.PropertyDescriptorBasedPropertyDefinition;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericTypeBasedBeanDefinitionBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public final class GenericTypeBasedBeanDefinitionBuilder<BEANTYPE> extends
    AbstractTypeBasedBeanDefinitionBuilder<BEANTYPE, GenericTypeBasedBeanDefinitionBuilder<BEANTYPE>>
{
    /**
     * Instantiates a new generic type based bean definition builder.
     *
     * @param _type
     *            the _type
     */
    public GenericTypeBasedBeanDefinitionBuilder(final Class<BEANTYPE> _type)
    {
        this(_type, true);
    }

    /**
     * Instantiates a new generic type based bean definition builder.
     *
     * @param _type
     *            the _type
     * @param _addPropertyDefinitionsFromType
     *            the _add property definitions from type
     */
    public GenericTypeBasedBeanDefinitionBuilder(final Class<BEANTYPE> _type,
                                                 final boolean _addPropertyDefinitionsFromType)
    {
        super(_type, _addPropertyDefinitionsFromType);
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.AbstractTypeBasedBeanDefinitionBuilder#addOrSetPropertyDefinitionsFromType()
     */
    @Override
    public GenericTypeBasedBeanDefinitionBuilder<BEANTYPE> addOrSetPropertyDefinitionsFromType()
    {
        super.addOrSetPropertyDefinitionsFromType();

        final GenericBeanDefinition<BEANTYPE> prototype = getPrototype();
        final Set<Object> propertyIdSet = prototype.getPropertyIds();
        Object idPropertyId = null;
        for (final Object propertyId : propertyIdSet) {
            final String propertyIdAsString = propertyId.toString();
            if (propertyIdAsString.equalsIgnoreCase("id")) {
                idPropertyId = propertyId;
                break;
            }
        }
        setIdPropertyId(idPropertyId);

        return getThis();
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.AbstractTypeBasedBeanDefinitionBuilder#createPropertyDefinition(java.beans.PropertyDescriptor)
     */
    @Override
    protected PropertyDefinition createPropertyDefinition(final PropertyDescriptor _propertyDescriptor)
    {
        if (null == _propertyDescriptor) {
            throw new NullPointerException("_propertyDescriptor is NULL");
        }

        final PropertyDefinition result = new PropertyDescriptorBasedPropertyDefinition(_propertyDescriptor);
        return result;
    }
}
