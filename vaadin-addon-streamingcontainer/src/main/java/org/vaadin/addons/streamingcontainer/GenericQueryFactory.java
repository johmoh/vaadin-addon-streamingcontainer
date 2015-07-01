/**
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.lang.reflect.Constructor;

// TODO: Auto-generated Javadoc
/**
 * @author johmoh
 */
public final class GenericQueryFactory<BEANTYPE> extends AbstractQueryFactory<BEANTYPE>
{
    /**
     * Instantiates a new generic query factory.
     *
     * @param _queryType
     *            the _query type
     */
    public GenericQueryFactory(final Class<? extends Query<BEANTYPE>> _queryType)
    {
        super(_queryType);
    }

    /**
     * @see org.vaadin.test.web.prototype.QueryFactory#createQuery(org.vaadin.test.web.prototype.QueryDefinition)
     */
    @Override
    public Query<BEANTYPE> createQuery(final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        Query<BEANTYPE> result;
        try {
            final Class<? extends Query<BEANTYPE>> queryType = getQueryType();
            final Constructor<? extends Query<BEANTYPE>> constructor = queryType.getConstructor(QueryDefinition.class);
            result = constructor.newInstance(_queryDefinition);
        }
        catch (final Exception _ex) {
            result = null;
        }

        return result;
    }
}
