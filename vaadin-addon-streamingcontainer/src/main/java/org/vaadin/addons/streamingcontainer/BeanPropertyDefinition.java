package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * The Interface BeanPropertyDefinition.
 */
public interface BeanPropertyDefinition
{
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Object getId();

    /**
     * Gets the type.
     *
     * @return the type
     */
    public Class<?> getType();

    /**
     * Gets the default value.
     *
     * @return the default value
     */
    public Object getDefaultValue();

    /**
     * Checks if is read only.
     *
     * @return true, if is read only
     */
    public boolean isReadOnly();

    /**
     * Checks if is sortable.
     *
     * @return true, if is sortable
     */
    public boolean isSortable();
}
