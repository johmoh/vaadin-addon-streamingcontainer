package org.vaadin.addons.streamingcontainer;

import com.vaadin.data.Property;

// TODO: Auto-generated Javadoc
/**
 * The Interface StatusProperty.
 */
public interface StatusProperty extends Property<EStatus>, Property.ValueChangeNotifier
{
    /**
     * Gets the status.
     *
     * @return the status
     */
    public EStatus getStatus();

    /**
     * Sets the status.
     *
     * @param _status
     *            the new status
     */
    public void setStatus(final EStatus _status);
}
