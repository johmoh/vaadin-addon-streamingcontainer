/**
 *
 */
package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * @author johmoh
 */
public interface QueryFactory<BEANTYPE>
{
    /**
     * Gets the query type.
     *
     * @return the query type
     */
    public Class<? extends Query<BEANTYPE>> getQueryType();

    /**
     * Creates a new IQuery object.
     *
     * @param _queryDefinition
     *            the _query definition
     * @return the i query< beantyp e>
     */
    public Query<BEANTYPE> createQuery(final QueryDefinition<BEANTYPE> _queryDefinition);
}
