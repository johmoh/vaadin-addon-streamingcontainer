/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractQuery.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public abstract class AbstractQuery<BEANTYPE> implements Query<BEANTYPE>
{
    /** The query definition. */
    private final QueryDefinition<BEANTYPE> queryDefinition;

    /**
     * Instantiates a new abstract query.
     *
     * @param _queryDefinition
     *            the _query definition
     */
    protected AbstractQuery(final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        System.out.println("CALL ["+this.hashCode()+"] AbstractQuery.CONSTRUCTOR()");
        this.queryDefinition = _queryDefinition;
    }

    /**
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize()
        throws Throwable
    {
        System.out.println("CALL ["+this.hashCode()+"] AbstractQuery.FINALIZE()");
        try {
            closeStream();
        }
        catch (final Throwable _t) {
        }
        finally {
            super.finalize();
        }
    }

    /**
     * Gets the query definition.
     *
     * @return the query definition
     */
    public QueryDefinition<BEANTYPE> getQueryDefinition()
    {
        return queryDefinition;
    }
}
