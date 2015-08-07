package org.vaadin.addons.streamingcontainer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.vaadin.data.util.BeanUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericBeanDefinition.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public final class GenericBeanDefinition<BEANTYPE> implements BeanDefinition<BEANTYPE>, Serializable
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1124843778790244473L;

    /** The type. */
    private final Class<BEANTYPE> type;

    /** The id property id. */
    private Object idPropertyId = null;

    /** The id2 definition map. */
    private final HashMap<Object, BeanPropertyDefinition> id2DefinitionMap = new HashMap<Object, BeanPropertyDefinition>();

    /**
     * Instantiates a new generic bean definition.
     *
     * @param _type
     *            the _type
     */
    public GenericBeanDefinition(final Class<BEANTYPE> _type)
    {
        this(_type, null, false);
    }

    /**
     * Instantiates a new generic bean definition.
     *
     * @param _type
     *            the _type
     * @param _addDefinitionsFromType
     *            the _add definitions from type
     */
    public GenericBeanDefinition(final Class<BEANTYPE> _type, final boolean _addDefinitionsFromType)
    {
        this(_type, null, _addDefinitionsFromType);
    }

    /**
     * Instantiates a new generic bean definition.
     *
     * @param _type
     *            the _type
     * @param _idPropertyId
     *            the _id property id
     * @param _addDefinitionsFromType
     *            the _add definitions from type
     */
    public GenericBeanDefinition(final Class<BEANTYPE> _type,
                                 final Object _idPropertyId,
                                 final boolean _addDefinitionsFromType)
    {
        if (null == _type) {
            throw new NullPointerException("_type is NULL");
        }

        this.type = _type;
        this.idPropertyId = _idPropertyId;
        if (_addDefinitionsFromType) {
            addOrSetPropertyDefinitionsFromType();
        }
    }

    /**
     * Instantiates a new generic bean definition.
     *
     * @param _prototype
     *            the _prototype
     */
    public GenericBeanDefinition(final BeanDefinition<BEANTYPE> _prototype)
    {
        if (null == _prototype) {
            throw new NullPointerException("_prototype is NULL");
        }

        this.type = _prototype.getType();
        this.idPropertyId = _prototype.getIdPropertyId();
        final Set<Object> idSet = _prototype.getPropertyIds();
        for (final Object id : idSet) {
            final BeanPropertyDefinition definition = _prototype.getPropertyDefinition(id);
            addOrSetPropertyDefinition(definition);
        }
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getType()
     */
    @Override
    public Class<BEANTYPE> getType()
    {
        return type;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getIdPropertyId()
     */
    @Override
    public Object getIdPropertyId()
    {
        return idPropertyId;
    }

    /**
     * Sets the id property id.
     *
     * @param _idPropertyId
     *            the _id property id
     * @return the generic bean definition
     */
    public GenericBeanDefinition<BEANTYPE> setIdPropertyId(final Object _idPropertyId)
    {
        this.idPropertyId = _idPropertyId;
        return this;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#containsPropertyId(java.lang.Object)
     */
    @Override
    public boolean containsPropertyId(final Object _id)
    {
        final boolean result = id2DefinitionMap.containsKey(_id);
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getPropertyIds()
     */
    @Override
    public Set<Object> getPropertyIds()
    {
        final Set<Object> result = id2DefinitionMap.keySet();
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getIdsOfSortablePropertyDefinitions()
     */
    @Override
    public Set<Object> getIdsOfSortablePropertyDefinitions()
    {
        final Set<Object> result = new HashSet<Object>();
        final Set<Entry<Object, BeanPropertyDefinition>> entrySet = id2DefinitionMap.entrySet();
        for (final Entry<Object, BeanPropertyDefinition> entry : entrySet) {
            final BeanPropertyDefinition definition = entry.getValue();
            if (definition.isSortable()) {
                final Object id = entry.getKey();
                result.add(id);
            }
        }

        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getPropertyDefinition(java.lang.Object)
     */
    @Override
    public BeanPropertyDefinition getPropertyDefinition(final Object _id)
    {
        final BeanPropertyDefinition result = id2DefinitionMap.get(_id);
        return result;
    }

    /**
     * Gets the as generic property definition.
     *
     * @param _id
     *            the _id
     * @return the as generic property definition
     */
    public GenericBeanPropertyDefinition getAsGenericPropertyDefinition(final Object _id)
    {
        final GenericBeanPropertyDefinition result;
        final BeanPropertyDefinition definition = getPropertyDefinition(_id);
        if (null == definition) {
            result = null;
        }
        else if (definition instanceof GenericBeanPropertyDefinition) {
            result = (GenericBeanPropertyDefinition) definition;
        }
        else {
            result = new GenericBeanPropertyDefinition(definition);
            addOrSetPropertyDefinition(result);
        }

        return result;
    }

    /**
     * Adds the or set a property definition.
     *
     * @param _definition
     *            the property definition
     * @return the generic bean definition
     */
    public GenericBeanDefinition<BEANTYPE> addOrSetPropertyDefinition(final BeanPropertyDefinition _definition)
    {
        final Object id = _definition.getId();
        id2DefinitionMap.put(id, _definition);
        return this;
    }

    /**
     * Adds the or set property definitions of all properties of the type (see {@link #getType()}).
     *
     * @return the generic bean definition
     */
    public GenericBeanDefinition<BEANTYPE> addOrSetPropertyDefinitionsFromType()
    {
        List<PropertyDescriptor> propertyDescriptors;
        try {
            propertyDescriptors = BeanUtil.getBeanPropertyDescriptor(type);
        }
        catch (final IntrospectionException _ex) {
            propertyDescriptors = null;
        }

        if (null != propertyDescriptors) {
            for (final PropertyDescriptor pd : propertyDescriptors) {
                final Method getMethod = pd.getReadMethod();
                if ((getMethod != null) && (getMethod.getDeclaringClass() != Object.class)) {
                    final boolean hasReadMethod = (null != pd.getReadMethod());
                    if (hasReadMethod) {
                        final String propertyId = pd.getName();
                        final Class<?> type = pd.getPropertyType();
                        final Object defaultValue = null;
                        final boolean readOnly = (null == pd.getWriteMethod());
                        final BeanPropertyDefinition definition = new GenericBeanPropertyDefinition(propertyId, type,
                                defaultValue, readOnly, false);
                        addOrSetPropertyDefinition(definition);
                    }
                }
            }
        }

        return this;
    }

    /**
     * Removes a property definition.
     *
     * @param _id
     *            the _id
     * @return the generic bean definition
     */
    public GenericBeanDefinition<BEANTYPE> removePropertyDefinition(final Object _id)
    {
        id2DefinitionMap.remove(_id);
        return this;
    }

    /**
     * Removes all property definitions.
     *
     * @return the generic bean definition
     */
    public GenericBeanDefinition<BEANTYPE> removeAllPropertyDefinitions()
    {
        id2DefinitionMap.clear();
        return this;
    }
}
