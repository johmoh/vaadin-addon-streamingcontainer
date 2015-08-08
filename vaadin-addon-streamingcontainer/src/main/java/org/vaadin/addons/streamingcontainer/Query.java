/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * The Interface Query.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface Query<BEANTYPE> extends Disposable
{
    /**
     * Read next.
     *
     * @param _maxNumberOfObjects
     *            the _max number of objects
     * @return the query result
     */
    public QueryResult<BEANTYPE> readNext(final int _maxNumberOfObjects);
}
