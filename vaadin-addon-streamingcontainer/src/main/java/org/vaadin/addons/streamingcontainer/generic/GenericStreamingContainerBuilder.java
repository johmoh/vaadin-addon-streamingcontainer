package org.vaadin.addons.streamingcontainer.generic;

import org.vaadin.addons.streamingcontainer.AbstractQuery;
import org.vaadin.addons.streamingcontainer.AbstractStreamingContainerBuilder;
import org.vaadin.addons.streamingcontainer.Constants;
import org.vaadin.addons.streamingcontainer.ItemBuilder;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericStreamingContainerBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericStreamingContainerBuilder<BEANTYPE>
    extends
    AbstractStreamingContainerBuilder<BEANTYPE, QueryFactory<BEANTYPE>, QueryDefinition<BEANTYPE>, ItemBuilder<BEANTYPE>, GenericStreamingContainerBuilder<BEANTYPE>>
{
    /** The default query factory. */
    private final QueryFactory<BEANTYPE> defaultQueryFactory;

    /** The default item builder. */
    private final ItemBuilder<BEANTYPE> defaultItemBuilder;

    /**
     * Instantiates a new generic streaming container builder.
     *
     * @param _queryType
     *            the _query type
     * @param _queryDefinition
     *            the _query definition
     */
    public GenericStreamingContainerBuilder(final Class<? extends AbstractQuery<BEANTYPE>> _queryType,
                                            final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        this(GenericQueryFactory.<BEANTYPE> getInstance(_queryType), _queryDefinition);
    }

    /**
     * Instantiates a new generic streaming container builder.
     *
     * @param _queryFactory
     *            the _query factory
     * @param _queryDefinition
     *            the _query definition
     */
    public GenericStreamingContainerBuilder(final QueryFactory<BEANTYPE> _queryFactory,
                                            final QueryDefinition<BEANTYPE> _queryDefinition)
    {
        super( //
                _queryFactory, //
                _queryDefinition, //
                GenericItemBuilder.<BEANTYPE> getInstance(), //
                Constants.Defaults.DEFAULT_INITIAL_BATCH_SIZE_HINT, //
                Constants.Defaults.DEFAULT_BATCH_SIZE_HINT, //
                Constants.Defaults.DEFAULT_MAX_QUERY_SIZE_HINT //
        );

        this.defaultQueryFactory = getQueryFactory();
        this.defaultItemBuilder = getItemBuilder();
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.AbstractStreamingContainerBuilder#getOrCreateDefaultQueryFactory()
     */
    @Override
    protected QueryFactory<BEANTYPE> getOrCreateDefaultQueryFactory()
    {
        return defaultQueryFactory;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.AbstractStreamingContainerBuilder#getOrCreateDefaultItemBuilder()
     */
    @Override
    protected ItemBuilder<BEANTYPE> getOrCreateDefaultItemBuilder()
    {
        return defaultItemBuilder;
    }
}
