package org.vaadin.addons.streamingcontainer.generic;

import java.io.Serializable;

import org.vaadin.addons.streamingcontainer.PropertyDefinition;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericPropertyDefinition.
 */
public final class GenericPropertyDefinition implements PropertyDefinition, Serializable
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3851395431642174429L;

    /** The id. */
    private final Object id;

    /** The type. */
    private Class<?> type;

    /** The default value. */
    private Object defaultValue;

    /** The read only. */
    private boolean readOnly;

    /** The sortable. */
    private boolean sortable;

    /**
     * Instantiates a new generic bean property definition.
     *
     * @param _id
     *            the _id
     * @param _type
     *            the _type
     */
    public GenericPropertyDefinition(final Object _id, final Class<?> _type)
    {
        this(_id, _type, null, true, false);
    }

    /**
     * Instantiates a new generic bean property definition.
     *
     * @param _id
     *            the _id
     * @param _type
     *            the _type
     * @param _defaultValue
     *            the _default value
     * @param _readOnly
     *            the _read only
     * @param _sortable
     *            the _sortable
     */
    public GenericPropertyDefinition(final Object _id,
                                     final Class<?> _type,
                                     final Object _defaultValue,
                                     final boolean _readOnly,
                                     final boolean _sortable)
    {
        this.id = _id;
        this.type = _type;
        this.defaultValue = _defaultValue;
        this.readOnly = _readOnly;
        this.sortable = _sortable;
    }

    /**
     * Instantiates a new generic bean property definition.
     *
     * @param _prototype
     *            the _prototype
     */
    public GenericPropertyDefinition(final PropertyDefinition _prototype)
    {
        this( //
                _prototype.getId(), //
                _prototype.getType(), //
                _prototype.getDefaultValue(), //
                _prototype.isReadOnly(), //
                _prototype.isSortable() //
        );
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getId()
     */
    @Override
    public Object getId()
    {
        return id;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getType()
     */
    @Override
    public Class<?> getType()
    {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type
     *            the type
     * @return the generic bean property definition
     */
    public GenericPropertyDefinition setType(final Class<?> type)
    {
        this.type = type;
        return this;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#getDefaultValue()
     */
    @Override
    public Object getDefaultValue()
    {
        return defaultValue;
    }

    /**
     * Sets the default value.
     *
     * @param defaultValue
     *            the default value
     * @return the generic bean property definition
     */
    public GenericPropertyDefinition setDefaultValue(final Object defaultValue)
    {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.PropertyDefinition#isReadOnly()
     */
    @Override
    public boolean isReadOnly()
    {
        return readOnly;
    }

    /**
     * Sets the read only.
     *
     * @param readOnly
     *            the read only
     * @return the generic bean property definition
     */
    public GenericPropertyDefinition setReadOnly(final boolean readOnly)
    {
        this.readOnly = readOnly;
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
     * @param sortable
     *            the sortable
     * @return the generic bean property definition
     */
    public GenericPropertyDefinition setSortable(final boolean sortable)
    {
        this.sortable = sortable;
        return this;
    }
}
