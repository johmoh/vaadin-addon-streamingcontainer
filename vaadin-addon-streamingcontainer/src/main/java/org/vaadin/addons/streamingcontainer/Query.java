/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer;

import java.util.Collection;

// TODO: Auto-generated Javadoc
/**
 * The Interface IQuery.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface Query<BEANTYPE>
{
    /**
     * Read next.
     *
     * @param _maxNumberOfObjects
     *            the _max number of objects
     * @return the collection
     */
    public Collection<BEANTYPE> readNext(final int _maxNumberOfObjects);

    /**
     * Checks for more.
     *
     * @return true, if successful
     */
    public boolean hasMore();

    /**
     * Close stream.
     */
    public void closeStream();
}
