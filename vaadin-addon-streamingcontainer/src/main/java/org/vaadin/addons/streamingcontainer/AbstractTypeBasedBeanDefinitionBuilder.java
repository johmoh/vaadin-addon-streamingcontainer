package org.vaadin.addons.streamingcontainer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import org.vaadin.addons.streamingcontainer.generic.GenericBeanDefinition;

import com.vaadin.data.util.BeanUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractTypeBasedBeanDefinitionBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 * @param <SUBTYPE>
 *            the generic type
 */
public abstract class AbstractTypeBasedBeanDefinitionBuilder<BEANTYPE, SUBTYPE extends AbstractTypeBasedBeanDefinitionBuilder<BEANTYPE, SUBTYPE>>
    extends AbstractBeanDefinitionBuilder<BEANTYPE, SUBTYPE>
{
    /**
     * Instantiates a new abstract type based bean definition builder.
     *
     * @param _type
     *            the _type
     */
    protected AbstractTypeBasedBeanDefinitionBuilder(final Class<BEANTYPE> _type)
    {
        this(_type, true);
    }

    /**
     * Instantiates a new abstract type based bean definition builder.
     *
     * @param _type
     *            the _type
     * @param _addPropertyDefinitionsFromType
     *            the _add property definitions from type
     */
    protected AbstractTypeBasedBeanDefinitionBuilder(final Class<BEANTYPE> _type,
                                                     final boolean _addPropertyDefinitionsFromType)
    {
        super(_type);

        if (_addPropertyDefinitionsFromType) {
            addOrSetPropertyDefinitionsFromType();
        }
    }

    /**
     * Adds the or set property definitions from type.
     *
     * @return the subtype
     */
    public SUBTYPE addOrSetPropertyDefinitionsFromType()
    {
        final GenericBeanDefinition<BEANTYPE> prototype = getPrototype();
        final Class<BEANTYPE> beanType = prototype.getType();

        List<PropertyDescriptor> propertyDescriptors;
        try {
            propertyDescriptors = BeanUtil.getBeanPropertyDescriptor(beanType);
        }
        catch (final IntrospectionException _ex) {
            propertyDescriptors = null;
        }

        if (null != propertyDescriptors) {
            for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                final Method getter = propertyDescriptor.getReadMethod();
                if ((null != getter) && (getter.getDeclaringClass() != Object.class)) {
                    final PropertyDefinition definition = createPropertyDefinition(propertyDescriptor);
                    if (null != definition) {
                        addOrSetPropertyDefinition(definition);
                    }
                }
            }
        }

        return getThis();
    }

    /**
     * Creates the property definition.
     *
     * @param _propertyDescriptor
     *            the _property descriptor
     * @return the bean property definition
     */
    protected abstract PropertyDefinition createPropertyDefinition(final PropertyDescriptor _propertyDescriptor);
}
