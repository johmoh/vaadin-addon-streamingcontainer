/*
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.util.Set;

// TODO: Auto-generated Javadoc

/**
 * The Interface BeanDefinition.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface BeanDefinition<BEANTYPE>
{
    /**
     * Gets the type.
     *
     * @return the type
     */
    public Class<BEANTYPE> getType();

    /**
     * Gets the id property id.
     *
     * @return the id property id
     */
    public Object getIdPropertyId();

    /**
     * Contains property id.
     *
     * @param _id
     *            the _id
     * @return true, if successful
     */
    public boolean containsPropertyId(final Object _id);

    /**
     * Gets the property ids.
     *
     * @return the property ids
     */
    public Set<Object> getPropertyIds();

    /**
     * Gets the ids of sortable property definitions.
     *
     * @return the ids of sortable property definitions
     */
    public Set<Object> getIdsOfSortablePropertyDefinitions();

    /**
     * Gets the property definition.
     *
     * @param _id
     *            the id
     * @return the definition
     */
    public BeanPropertyDefinition getPropertyDefinition(final Object _id);
}
