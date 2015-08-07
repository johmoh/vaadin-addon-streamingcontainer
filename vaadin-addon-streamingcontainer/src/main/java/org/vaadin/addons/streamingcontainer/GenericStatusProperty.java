package org.vaadin.addons.streamingcontainer;

import java.util.LinkedList;

import com.vaadin.data.Property;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusProperty.
 */
public class GenericStatusProperty implements StatusProperty
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1906833924245683736L;

    /** The status. */
    protected EStatus status;

    /**
     * List of listeners who are interested in the value changes of the Property
     */
    private LinkedList<ValueChangeListener> valueChangeListeners = null;

    /**
     * Instantiates a new status property.
     */
    public GenericStatusProperty()
    {
        this(null);
    }

    /**
     * Instantiates a new status property.
     *
     * @param _status
     *            the _status
     */
    public GenericStatusProperty(final EStatus _status)
    {
        this.status = _status;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StatusProperty#getStatus()
     */
    @Override
    public EStatus getStatus()
    {
        return status;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StatusProperty#setStatus(org.vaadin.addons.streamingcontainer.EStatus)
     */
    @Override
    public void setStatus(final EStatus _status)
    {
        if (_status != status) {
            status = _status;

            fireValueChangeEvent();
        }
    }

    /**
     * @see com.vaadin.data.Property#getValue()
     */
    @Override
    public EStatus getValue()
    {
        return status;
    }

    /**
     * @see com.vaadin.data.Property#setValue(java.lang.Object)
     */
    @Override
    public void setValue(final EStatus _status)
        throws com.vaadin.data.Property.ReadOnlyException
    {
        if (_status != status) {
            throw new com.vaadin.data.Property.ReadOnlyException("Status flag cannot be changed for \""
                    + this.getClass().getSimpleName() + "\"");
        }
    }

    /**
     * @see com.vaadin.data.Property#getType()
     */
    @Override
    public Class<? extends EStatus> getType()
    {
        final Class<? extends EStatus> result = EStatus.class;
        return result;
    }

    /**
     * @see com.vaadin.data.Property#isReadOnly()
     */
    @Override
    public boolean isReadOnly()
    {
        return true;
    }

    /**
     * @see com.vaadin.data.Property#setReadOnly(boolean)
     */
    @Override
    public void setReadOnly(final boolean _status)
    {
        if (!_status) {
            throw new com.vaadin.data.Property.ReadOnlyException("Read-only flag cannot be changed for \""
                    + this.getClass().getSimpleName() + "\"");
        }
    }

    private void fireValueChangeEvent()
    {
        if ((null != valueChangeListeners) && !valueChangeListeners.isEmpty()) {
            final Property.ValueChangeEvent event = new ValueChangeEventImpl(this);
            for (final Property.ValueChangeListener listener : valueChangeListeners) {
                listener.valueChange(event);
            }
        }
    }

    /**
     * @see com.vaadin.data.Property.ValueChangeNotifier#addValueChangeListener(com.vaadin.data.Property.ValueChangeListener)
     */
    @Override
    public void addValueChangeListener(final Property.ValueChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        if (null == valueChangeListeners) {
            valueChangeListeners = new LinkedList<ValueChangeListener>();
        }
        valueChangeListeners.add(_listener);
    }

    /**
     * @see com.vaadin.data.Property.ValueChangeNotifier#addListener(com.vaadin.data.Property.ValueChangeListener)
     */
    @Override
    @Deprecated
    public void addListener(final com.vaadin.data.Property.ValueChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        addValueChangeListener(_listener);
    }

    /**
     * @see com.vaadin.data.Property.ValueChangeNotifier#removeValueChangeListener(com.vaadin.data.Property.ValueChangeListener)
     */
    @Override
    public void removeValueChangeListener(final Property.ValueChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        valueChangeListeners.remove(_listener);
        if (valueChangeListeners.isEmpty()) {
            valueChangeListeners = null;
        }
    }

    /**
     * @see com.vaadin.data.Property.ValueChangeNotifier#removeListener(com.vaadin.data.Property.ValueChangeListener)
     */
    @Override
    @Deprecated
    public void removeListener(final Property.ValueChangeListener _listener)
    {
        if (null == _listener) {
            throw new NullPointerException("_listener is NULL");
        }

        removeValueChangeListener(_listener);
    }
}
