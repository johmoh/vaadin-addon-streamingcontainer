package org.vaadin.addons.streamingcontainer.generic;

import java.util.Collection;

import org.vaadin.addons.streamingcontainer.QueryResult;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericQueryResult.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericQueryResult<BEANTYPE> implements QueryResult<BEANTYPE>
{
    /** The loaded objects. */
    private final Collection<BEANTYPE> loadedObjects;

    /** The has more. */
    private final boolean hasMore;

    /**
     * Instantiates a new generic query result.
     *
     * @param _loadedObjects
     *            the _loaded objects
     * @param _hasMore
     *            the _has more
     */
    public GenericQueryResult(final Collection<BEANTYPE> _loadedObjects, final boolean _hasMore)
    {
        if (_hasMore) {
            if (null == _loadedObjects) {
                throw new IllegalArgumentException(
                        "If more objects can be loaded (_hasMore is true) then _loadedObjects cannot be NULL.");
            }
            else if (_loadedObjects.isEmpty()) {
                throw new IllegalArgumentException(
                        "If more objects can be loaded (_hasMore is true) then _loadedObjects cannot be empty.");
            }
        }

        this.loadedObjects = _loadedObjects;
        this.hasMore = _hasMore;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.QueryResult#getLoadedObjects()
     */
    @Override
    public Collection<BEANTYPE> getLoadedObjects()
    {
        return loadedObjects;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.QueryResult#hasMore()
     */
    @Override
    public boolean hasMore()
    {
        return hasMore;
    }
}
