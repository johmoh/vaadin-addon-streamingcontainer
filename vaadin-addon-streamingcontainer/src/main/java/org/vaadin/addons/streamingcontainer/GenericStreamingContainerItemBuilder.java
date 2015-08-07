package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericStreamingContainerItemBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericStreamingContainerItemBuilder<BEANTYPE> implements StreamingContainerItemBuilder<BEANTYPE>
{
    /** The Constant instance. */
    @SuppressWarnings("rawtypes")
    private static final GenericStreamingContainerItemBuilder instance = new GenericStreamingContainerItemBuilder();

    /**
     * Instantiates a new generic streaming container item builder.
     */
    private GenericStreamingContainerItemBuilder()
    {
        // INTENTIONALLY LEFT BLANK
    }

    /**
     * Gets the single instance of BeanItemBuilder.
     *
     * @param <BEANTYPE>
     *            the generic type
     * @return single instance of BeanItemBuilder
     */
    public static <BEANTYPE> GenericStreamingContainerItemBuilder<BEANTYPE> getInstance()
    {
        @SuppressWarnings("unchecked")
        final GenericStreamingContainerItemBuilder<BEANTYPE> result = instance;
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.StreamingContainerItemBuilder#build(java.lang.Object,
     *      org.vaadin.addons.streamingcontainer.BeanDefinition)
     */
    @Override
    public StreamingContainerItem<BEANTYPE> build(final BEANTYPE _obj, final BeanDefinition<BEANTYPE> _beanDefinition)
    {
        if (null == _obj) {
            throw new NullPointerException("_obj is NULL");
        }
        if (null == _beanDefinition) {
            throw new NullPointerException("_beanDefinition is NULL");
        }

        final StreamingContainerItem<BEANTYPE> result = new GenericStreamingContainerItem<BEANTYPE>( //
                _obj, //
                _beanDefinition //
        );
        return result;
    }
}
