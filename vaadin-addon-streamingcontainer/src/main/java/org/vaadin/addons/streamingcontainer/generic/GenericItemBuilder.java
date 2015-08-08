package org.vaadin.addons.streamingcontainer.generic;

import org.vaadin.addons.streamingcontainer.BeanDefinition;
import org.vaadin.addons.streamingcontainer.Item;
import org.vaadin.addons.streamingcontainer.ItemBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericItemBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericItemBuilder<BEANTYPE> implements ItemBuilder<BEANTYPE>
{
    /** The Constant instance. */
    @SuppressWarnings("rawtypes")
    private static final GenericItemBuilder instance = new GenericItemBuilder();

    /**
     * Instantiates a new generic streaming container item builder.
     */
    private GenericItemBuilder()
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
    public static <BEANTYPE> GenericItemBuilder<BEANTYPE> getInstance()
    {
        @SuppressWarnings("unchecked")
        final GenericItemBuilder<BEANTYPE> result = instance;
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.ItemBuilder#build(java.lang.Object,
     *      org.vaadin.addons.streamingcontainer.BeanDefinition)
     */
    @Override
    public Item<BEANTYPE> build(final BEANTYPE _obj, final BeanDefinition<BEANTYPE> _beanDefinition)
    {
        if (null == _obj) {
            throw new NullPointerException("_obj is NULL");
        }
        if (null == _beanDefinition) {
            throw new NullPointerException("_beanDefinition is NULL");
        }

        final Item<BEANTYPE> result = new GenericItem<BEANTYPE>( //
                _obj, //
                _beanDefinition //
        );
        return result;
    }
}
