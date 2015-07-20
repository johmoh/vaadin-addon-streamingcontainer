package org.vaadin.addons.streamingcontainer;

import java.util.Collection;

// TODO: Auto-generated Javadoc
/**
 * The Interface QueryResult.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface QueryResult<BEANTYPE>
{
    /**
     * Gets loaded objects.
     *
     * @return the objects
     */
    public Collection<BEANTYPE> getLoadedObjects();

    /**
     * Checks for more.
     *
     * @return true, if successful
     */
    public boolean hasMore();
}
