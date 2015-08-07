package org.vaadin.addons.streamingcontainer;

// TODO: Auto-generated Javadoc
/**
 * The Interface StreamingContainerItemBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface StreamingContainerItemBuilder<BEANTYPE>
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
    public StreamingContainerItem<BEANTYPE> build(final BEANTYPE _obj, final BeanDefinition<BEANTYPE> _beanDefinition);
}
