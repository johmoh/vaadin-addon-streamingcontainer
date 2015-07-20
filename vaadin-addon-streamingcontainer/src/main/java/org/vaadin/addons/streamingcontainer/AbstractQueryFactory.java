/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating AbstractQuery objects.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public abstract class AbstractQueryFactory<BEANTYPE> implements QueryFactory<BEANTYPE>
{
    /** The query type. */
    private final Class<? extends Query<BEANTYPE>> queryType;

    /**
     * Instantiates a new abstract query factory.
     *
     * @param _queryType
     *            the _query type
     */
    protected AbstractQueryFactory(final Class<? extends Query<BEANTYPE>> _queryType)
    {
        if (null == _queryType) {
            throw new NullPointerException("_queryType is NULL");
        }

        this.queryType = _queryType;
    }

    /**
     * @see org.vaadin.test.web.prototype.QueryFactory#getQueryType()
     */
    @Override
    public Class<? extends Query<BEANTYPE>> getQueryType()
    {
        return queryType;
    }
}
