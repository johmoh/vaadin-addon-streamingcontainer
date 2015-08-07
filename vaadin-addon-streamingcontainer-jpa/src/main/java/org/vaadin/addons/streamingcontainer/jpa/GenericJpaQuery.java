package org.vaadin.addons.streamingcontainer.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.vaadin.addons.streamingcontainer.BeanDefinition;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryResult;
import org.vaadin.addons.streamingcontainer.generic.GenericQueryResult;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericJpaQuery.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericJpaQuery<BEANTYPE> extends AbstractJpaQuery<BEANTYPE>
{
    /** The stream closed. */
    private Boolean streamClosed = null;

    /** The last has more. */
    private boolean lastHasMore = false;

    /** The next first row num. */
    private int nextFirstRowNumber = 0;

    /**
     * Instantiates a new jpa query.
     *
     * @param _entityManager
     *            the _entity manager
     * @param _queryDefinition
     *            the _query definition
     * @param _additionalFilters
     *            the _additional filters
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAscendingStates
     *            the _sort property ascending states
     */
    public GenericJpaQuery(final EntityManager _entityManager,
                           final JpaTypedQueryBuilder<BEANTYPE> _jpaTypedQueryBuilder,
                           final QueryDefinition<BEANTYPE> _queryDefinition,
                           final Filter[] _additionalFilters,
                           final Object[] _sortPropertyIds,
                           final boolean[] _sortPropertyAscendingStates)
    {
        super( //
                _entityManager, //
                _jpaTypedQueryBuilder, //
                _queryDefinition, //
                _additionalFilters, //
                _sortPropertyIds, //
                _sortPropertyAscendingStates //
        );

        System.out.println("CALL [" + this.hashCode() + "] GenericJpaQuery.CONSTRUCTOR()");
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.Query#readNext(int)
     */
    @Override
    public QueryResult<BEANTYPE> readNext(final int _maxNumberOfObjects)
    {
        System.out.println("CALL [" + this.hashCode() + "] JpaQuery.readNext(" + _maxNumberOfObjects + ")");
        final List<BEANTYPE> personList;
        final boolean hasMore;
        if (_maxNumberOfObjects <= 0) {
            personList = null;
            hasMore = lastHasMore;
        }
        else if (Boolean.TRUE.equals(streamClosed)) {
            personList = null;
            hasMore = lastHasMore;
        }
        else {
            final QueryDefinition<BEANTYPE> queryDefinition = getQueryDefinition();
            final BeanDefinition<BEANTYPE> beanDefinition = queryDefinition.getBeanDefinition();
            final Class<BEANTYPE> beanType = beanDefinition.getType();
            final Object idPropertyId = beanDefinition.getIdPropertyId();
            final String idPropertyIdAsString = ((null == idPropertyId) ? null : idPropertyId.toString());
            final Collection<Filter> fixedFilters = queryDefinition.getFilters();
            final Filter[] additionalFilters = getAdditionalFilters();
            final Object[] sortPropertyIds;
            final boolean[] sortPropertyAscendingStates;
            if (null != getSortPropertyIds()) {
                sortPropertyIds = getSortPropertyIds();
                sortPropertyAscendingStates = getSortPropertyAscendingStates();
            }
            else {
                sortPropertyIds = queryDefinition.getSortPropertyIds();
                sortPropertyAscendingStates = queryDefinition.getSortPropertyAscendingStates();
            }

            final int maxNumberOfObjectsPlusOne = _maxNumberOfObjects + 1; // +1 for "has more" functionality
            final EntityManager entityManager = getEntityManager();
            final JpaTypedQueryBuilder<BEANTYPE> jpaTypedQueryBuilder = getJpaTypedQueryBuilder();
            final TypedQuery<BEANTYPE> query = jpaTypedQueryBuilder.build( //
                    beanType, //
                    idPropertyIdAsString, //
                    fixedFilters, //
                    additionalFilters, //
                    sortPropertyIds, //
                    sortPropertyAscendingStates, //
                    nextFirstRowNumber, //
                    maxNumberOfObjectsPlusOne, //
                    entityManager //
                );

            final List<BEANTYPE> queryResult = query.getResultList();
            final int queryResultSize = (queryResult.isEmpty() ? 0 : queryResult.size());
            if (queryResultSize <= _maxNumberOfObjects) {
                personList = queryResult;
                hasMore = false;
                closeStream();
            }
            else {
                personList = queryResult.subList(0, _maxNumberOfObjects);
                hasMore = true;
                nextFirstRowNumber += _maxNumberOfObjects;
            }

            lastHasMore = hasMore;
        }

        System.out.println("NOTE [" + this.hashCode() + "] JpaQuery.readNext(...): hasMore=" + hasMore
                + " nextFirstRowNumber=" + nextFirstRowNumber);

        final QueryResult<BEANTYPE> result = new GenericQueryResult<BEANTYPE>(personList, hasMore);
        return result;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.Query#closeStream()
     */
    @Override
    public void closeStream()
    {
        System.out.println("CALL [" + this.hashCode() + "] JpaQuery.closeStream()");
        if (!Boolean.TRUE.equals(streamClosed)) {
            nextFirstRowNumber = -1;
            streamClosed = Boolean.TRUE;
        }
    }
}
