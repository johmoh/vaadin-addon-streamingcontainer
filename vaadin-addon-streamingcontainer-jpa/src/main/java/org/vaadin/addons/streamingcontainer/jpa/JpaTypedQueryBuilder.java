package org.vaadin.addons.streamingcontainer.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Interface JpaTypedQueryBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public interface JpaTypedQueryBuilder<BEANTYPE>
{
    /**
     * Builds a JPA typed query.
     *
     * @param _beanType
     *            the _bean type
     * @param _idPropertyId
     *            the _id property id
     * @param _fixedFilters
     *            the _fixed filters
     * @param _additionalFilters
     *            the _additional filters
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAscendingStates
     *            the _sort property ascending states
     * @param _firstRowNumber
     *            the _first row number
     * @param _maxNumberOfObjects
     *            the _max number of objects
     * @param _entityManager
     *            the _entity manager
     * @return the typed query
     */
    public TypedQuery<BEANTYPE> build(final Class<BEANTYPE> _beanType,
                                      final String _idPropertyId,
                                      final Collection<Filter> _fixedFilters,
                                      final Filter[] _additionalFilters,
                                      final Object[] _sortPropertyIds,
                                      final boolean[] _sortPropertyAscendingStates,
                                      final int _firstRowNumber,
                                      final int _maxNumberOfObjects,
                                      final EntityManager _entityManager);
}
