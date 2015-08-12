package org.vaadin.addons.streamingcontainer;

import org.vaadin.addons.streamingcontainer.generic.GenericStreamingContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractStreamingContainerBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 * @param <FACTORY_TYPE>
 *            the generic type
 * @param <QUERY_DEFINITION_TYPE>
 *            the generic type
 * @param <ITEM_BUILDER_TYPE>
 *            the generic type
 * @param <SUBTYPE>
 *            the generic type
 */
public abstract class AbstractStreamingContainerBuilder< //
BEANTYPE, //
FACTORY_TYPE extends QueryFactory<BEANTYPE>, //
QUERY_DEFINITION_TYPE extends QueryDefinition<BEANTYPE>, //
ITEM_BUILDER_TYPE extends ItemBuilder<BEANTYPE>, //
SUBTYPE extends AbstractStreamingContainerBuilder<BEANTYPE, FACTORY_TYPE, QUERY_DEFINITION_TYPE, ITEM_BUILDER_TYPE, SUBTYPE> //
> implements StreamingContainerBuilder<BEANTYPE>
{
    /** The query factory. */
    private FACTORY_TYPE queryFactory;

    /** The query definition. */
    private QUERY_DEFINITION_TYPE queryDefinition;

    /** The item builder. */
    private ITEM_BUILDER_TYPE itemBuilder;

    /** The initial batch size hint. */
    private int initialBatchSizeHint;

    /** The batch size hint. */
    private int batchSizeHint;

    /** The max query size hint. */
    private int maxQuerySizeHint;

    /**
     * Instantiates a new abstract streaming container builder.
     *
     * @param _queryFactory
     *            the _query factory
     * @param _queryDefinition
     *            the _query definition
     * @param _itemBuilder
     *            the _item builder
     * @param _initialBatchSizeHint
     *            the _initial batch size hint
     * @param _batchSizeHint
     *            the _batch size hint
     * @param _maxQuerySizeHint
     *            the _max query size hint
     */
    protected AbstractStreamingContainerBuilder(final FACTORY_TYPE _queryFactory,
                                                final QUERY_DEFINITION_TYPE _queryDefinition,
                                                final ITEM_BUILDER_TYPE _itemBuilder,
                                                final int _initialBatchSizeHint,
                                                final int _batchSizeHint,
                                                final int _maxQuerySizeHint)
    {
        setQueryFactory(_queryFactory);
        setQueryDefinition(_queryDefinition);
        setItemBuilder(_itemBuilder);
        setInitialBatchSizeHint(_initialBatchSizeHint);
        setBatchSizeHint(_batchSizeHint);
        setMaxQuerySizeHint(_maxQuerySizeHint);
    }

    /**
     * Gets the this.
     *
     * @return the this
     */
    protected final SUBTYPE getThis()
    {
        @SuppressWarnings("unchecked")
        final SUBTYPE __this = (SUBTYPE) this;
        return __this;
    }

    /**
     * Gets the or create default query factory.
     *
     * @return the or create default query factory
     */
    protected abstract FACTORY_TYPE getOrCreateDefaultQueryFactory();

    /**
     * Gets the query factory.
     *
     * @return the query factory
     */
    public FACTORY_TYPE getQueryFactory()
    {
        return queryFactory;
    }

    /**
     * Sets the query factory.
     *
     * @param _queryFactory
     *            the _query factory
     * @return the subtype
     */
    public SUBTYPE setQueryFactory(final FACTORY_TYPE _queryFactory)
    {
        this.queryFactory = ((null != _queryFactory) ? _queryFactory : getQueryFactory());
        return getThis();
    }

    /**
     * Gets the query definition.
     *
     * @return the query definition
     */
    public QUERY_DEFINITION_TYPE getQueryDefinition()
    {
        return queryDefinition;
    }

    /**
     * Sets the query definition.
     *
     * @param _queryDefinition
     *            the _query definition
     * @return the subtype
     */
    public SUBTYPE setQueryDefinition(final QUERY_DEFINITION_TYPE _queryDefinition)
    {
        if (null == _queryDefinition) {
            throw new NullPointerException("_queryDefinition is NULL");
        }

        this.queryDefinition = _queryDefinition;
        return getThis();
    }

    /**
     * Gets the or create default item builder.
     *
     * @return the or create default item builder
     */
    protected abstract ITEM_BUILDER_TYPE getOrCreateDefaultItemBuilder();

    /**
     * Gets the item builder.
     *
     * @return the item builder
     */
    public ITEM_BUILDER_TYPE getItemBuilder()
    {
        return itemBuilder;
    }

    /**
     * Sets the item builder.
     *
     * @param _itemBuilder
     *            the _item builder
     * @return the subtype
     */
    public SUBTYPE setItemBuilder(final ITEM_BUILDER_TYPE _itemBuilder)
    {
        this.itemBuilder = ((null != _itemBuilder) ? _itemBuilder : getOrCreateDefaultItemBuilder());
        return getThis();
    }

    /**
     * Gets the initial batch size hint.
     *
     * @return the initial batch size hint
     */
    public int getInitialBatchSizeHint()
    {
        return initialBatchSizeHint;
    }

    /**
     * Sets the initial batch size hint.
     *
     * @param _initialBatchSizeHint
     *            the _initial batch size hint
     * @return the subtype
     */
    public SUBTYPE setInitialBatchSizeHint(final int _initialBatchSizeHint)
    {
        if (_initialBatchSizeHint < 0) {
            throw new IllegalArgumentException("_initialBatchSize has invalid value (value=" + _initialBatchSizeHint
                    + ")");
        }

        this.initialBatchSizeHint = _initialBatchSizeHint;
        return getThis();
    }

    /**
     * Gets the batch size hint.
     *
     * @return the batch size hint
     */
    public int getBatchSizeHint()
    {
        return batchSizeHint;
    }

    /**
     * Sets the batch size hint.
     *
     * @param _batchSizeHint
     *            the _batch size hint
     * @return the subtype
     */
    public SUBTYPE setBatchSizeHint(final int _batchSizeHint)
    {
        if (_batchSizeHint < 0) {
            throw new IllegalArgumentException("_batchSizeHint has invalid value (value=" + _batchSizeHint + ")");
        }

        this.batchSizeHint = _batchSizeHint;
        return getThis();
    }

    /**
     * Gets the max query size hint.
     *
     * @return the max query size hint
     */
    public int getMaxQuerySizeHint()
    {
        return maxQuerySizeHint;
    }

    /**
     * Sets the max query size hint.
     *
     * @param _maxQuerySizeHint
     *            the _max query size hint
     * @return the subtype
     */
    public SUBTYPE setMaxQuerySizeHint(final int _maxQuerySizeHint)
    {
        if (_maxQuerySizeHint < 0) {
            throw new IllegalArgumentException("_maxQuerySizeHint has invalid value (value=" + _maxQuerySizeHint + ")");
        }

        this.maxQuerySizeHint = _maxQuerySizeHint;
        return getThis();
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainerBuilder#build()
     */
    @Override
    public StreamingContainer<BEANTYPE> build()
    {
        final StreamingContainer<BEANTYPE> result = new GenericStreamingContainer<BEANTYPE>( //
                queryFactory, //
                queryDefinition, //
                itemBuilder, //
                Math.max(initialBatchSizeHint, Constants.Limits.MIN_INITIAL_BATCH_SIZE_LIMIT), //
                Math.max(batchSizeHint, Constants.Limits.MIN_BATCH_SIZE_LIMIT), //
                Math.min(maxQuerySizeHint, Constants.Limits.MAX_QUERY_SIZE_LIMIT) //
        );

        return result;
    }
}
