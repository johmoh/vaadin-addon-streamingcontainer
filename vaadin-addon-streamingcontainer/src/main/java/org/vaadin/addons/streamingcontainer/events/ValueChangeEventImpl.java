package org.vaadin.addons.streamingcontainer;

import com.vaadin.data.Property;

// TODO: Auto-generated Javadoc
/**
 * The Class ValueChangeEventImpl.
 */
public class ValueChangeEventImpl implements Property.ValueChangeEvent
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7920110164437460228L;

    /** The property. */
    private final Property<?> property;

    /**
     * Instantiates a new value change event impl.
     *
     * @param _property
     *            the _property
     */
    public ValueChangeEventImpl(final Property<?> _property)
    {
        if (null == _property) {
            throw new NullPointerException("_property is NULL");
        }

        this.property = _property;
    }

    /**
     * @see com.vaadin.data.Property.ValueChangeEvent#getProperty()
     */
    @Override
    public Property<?> getProperty()
    {
        return property;
    }
}
