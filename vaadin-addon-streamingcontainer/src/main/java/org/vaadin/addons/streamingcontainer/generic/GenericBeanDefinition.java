package org.vaadin.addons.streamingcontainer.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.vaadin.addons.streamingcontainer.BeanDefinition;
import org.vaadin.addons.streamingcontainer.PropertyDefinition;

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
    private final HashMap<Object, PropertyDefinition> id2DefinitionMap = new HashMap<Object, PropertyDefinition>();

    /**
     * Instantiates a new generic bean definition.
     *
     * @param _type
     *            the _type
     */
    public GenericBeanDefinition(final Class<BEANTYPE> _type)
    {
        this(_type, null);
    }

    /**
     * Instantiates a new generic bean definition.
     *
     * @param _type
     *            the _type
     * @param _idPropertyId
     *            the _id property id
     */
    public GenericBeanDefinition(final Class<BEANTYPE> _type, final Object _idPropertyId)
    {
        if (null == _type) {
            throw new NullPointerException("_type is NULL");
        }

        this.type = _type;
        this.idPropertyId = _idPropertyId;
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
            final PropertyDefinition definitionProtoype = _prototype.getPropertyDefinition(id);
            final PropertyDefinition definition = new GenericPropertyDefinition(definitionProtoype);
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
        if (null == _id) {
            throw new NullPointerException("_id is NULL");
        }

        final boolean result = id2DefinitionMap.containsKey(_id);
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getPropertyIds()
     */
    @Override
    public Set<Object> getPropertyIds()
    {
        final Set<Object> result = getPropertyIds(null, null);
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getPropertyIds(java.lang.Boolean, java.lang.Boolean)
     */
    @Override
    public Set<Object> getPropertyIds(final Boolean _readOnly, final Boolean _sortable)
    {
        final int maxSize = id2DefinitionMap.size();
        final Set<Object> result = new HashSet<Object>(maxSize);
        final Collection<PropertyDefinition> values = id2DefinitionMap.values();
        for (final PropertyDefinition definition : values) {
            boolean add = true;
            if (add && (null != _readOnly) && (definition.isReadOnly() != _readOnly)) {
                add = false;
            }
            if (add && (null != _sortable) && (definition.isSortable() != _sortable)) {
                add = false;
            }
            if (add) {
                final Object id = definition.getId();
                result.add(id);
            }
        }

        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.BeanDefinition#getPropertyDefinition(java.lang.Object)
     */
    @Override
    public PropertyDefinition getPropertyDefinition(final Object _id)
    {
        if (null == _id) {
            throw new NullPointerException("_id is NULL");
        }

        final PropertyDefinition result = id2DefinitionMap.get(_id);
        return result;
    }

    /**
     * Adds the or set a property definition.
     *
     * @param _definition
     *            the property definition
     * @return the generic bean definition
     */
    public GenericBeanDefinition<BEANTYPE> addOrSetPropertyDefinition(final PropertyDefinition _definition)
    {
        if (null == _definition) {
            throw new NullPointerException("_definition is NULL");
        }

        final Object id = _definition.getId();
        id2DefinitionMap.put(id, _definition);
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
        if (null == _id) {
            throw new NullPointerException("_id is NULL");
        }

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
