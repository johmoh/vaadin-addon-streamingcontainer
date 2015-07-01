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
     * Contains id.
     *
     * @param _id
     *            the _id
     * @return true, if successful
     */
    public boolean containsId(final Object _id);

    /**
     * Gets the property ids.
     *
     * @return the property ids
     */
    public Set<Object> getIds();

    /**
     * Gets the ids of sortable definitions.
     *
     * @return the ids of sortable definitions
     */
    public Set<Object> getIdsOfSortableDefinitions();

    /**
     * Gets the definition.
     *
     * @param _id
     *            the id
     * @return the definition
     */
    public BeanPropertyDefinition getDefinition(final Object _id);
}
