package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ItemBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface ItemBuilder<BEANTYPE>
{
    /**
     * Builds the.
     *
     * @param _obj
     *            the _obj
     * @param _beanDefinition
     *            the _bean definition
     * @return the streaming container item
     */
    public Item<BEANTYPE> build(final BEANTYPE _obj, final BeanDefinition<BEANTYPE> _beanDefinition);
}
