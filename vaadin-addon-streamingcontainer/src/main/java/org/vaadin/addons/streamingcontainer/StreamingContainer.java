package org.vaadin.addons.streamingcontainer;

import java.io.Serializable;

import com.vaadin.data.Container;

// TODO: Auto-generated Javadoc
/**
 * The Interface StreamingContainer.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface StreamingContainer<BEANTYPE>
    extends
        Container.Indexed,
        Container.Sortable,
        Container.Filterable,
        Container.ItemSetChangeNotifier,
        Container.PropertySetChangeNotifier,
        Serializable
{
    /**
     * Gets the query definition.
     *
     * @return the query definition
     */
    public QueryDefinition<BEANTYPE> getQueryDefinition();

    /**
     * Sets the query definition.
     *
     * @param _queryDefinition
     *            the new query definition
     */
    public void setQueryDefinition(final QueryDefinition<BEANTYPE> _queryDefinition);

    /**
     * Load till end of stream.
     */
    public void loadTillEndOfStream();

    /**
     * End streaming.
     */
    public void endStreaming();

    /**
     * Checks for streaming ended.
     *
     * @return true, if successful
     */
    public boolean hasStreamingEnded();

    /**
     * Checks for more.
     *
     * @return the boolean
     */
    public Boolean hasMore();
}
