/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer;

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
     * @return the query result
     */
    public QueryResult<BEANTYPE> readNext(final int _maxNumberOfObjects);

    /**
     * Close stream.
     */
    public void closeStream();
}
