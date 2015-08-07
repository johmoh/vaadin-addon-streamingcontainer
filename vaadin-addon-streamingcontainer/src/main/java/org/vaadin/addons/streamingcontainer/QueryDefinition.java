/**
 *
 */
package org.vaadin.addons.streamingcontainer;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * @author johmoh
 */
public interface QueryDefinition<BEANTYPE> extends Serializable
{
    /**
     * Gets the bean definition.
     *
     * @return the bean definition
     */
    public BeanDefinition<BEANTYPE> getBeanDefinition();

    /**
     * Gets the initial batch size.
     *
     * @return the initial batch size
     */
    public int getInitialBatchSize();

    /**
     * Gets the batch size hint.
     *
     * @return the batch size hint
     */
    public int getBatchSizeHint();

    /**
     * Gets the max query size hint.
     *
     * @return the max query size hint
     */
    public int getMaxQuerySizeHint();

    /**
     * Gets the additional parameters.
     *
     * @return the additional parameters
     */
    public Map<Object, Object> getAdditionalParameters();

    /**
     * Checks for additional parameter.
     *
     * @param _key
     *            the _key
     * @return true, if successful
     */
    public boolean hasAdditionalParameter(final Object _key);

    /**
     * Gets the additional parameter.
     *
     * @param _key
     *            the _key
     * @return the additional parameter
     */
    public Object getAdditionalParameter(final Object _key);

    /**
     * Gets the sort property ids.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of the array!
     *
     * @return the sort property ids
     */
    public Object[] getSortPropertyIds();

    /**
     * Gets the sort property ascending states.<br>
     * <br>
     * <b>ATTENTION!<b> It is not allowed to modify the content of the array!
     *
     * @return the sort property ascending states
     */
    public boolean[] getSortPropertyAscendingStates();

    /**
     * Gets the filters.
     *
     * @return the filters
     */
    public Collection<Filter> getFilters();
}
