package org.vaadin.addons.streamingcontainer.demo;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.vaadin.addons.streamingcontainer.AbstractQuery;
import org.vaadin.addons.streamingcontainer.BeanDefinition;
import org.vaadin.addons.streamingcontainer.GenericQueryResult;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryResult;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Class JpaQuery.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class JpaQuery<BEANTYPE> extends AbstractQuery<BEANTYPE>
{
    /** The entity manager. */
    private final EntityManager entityManager;

    /** The stream closed. */
    private Boolean streamClosed = null;

    /** The last has more. */
    private boolean lastHasMore = false;

    /** The next start id. */
    private Object nextStartId = null;

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
    public JpaQuery(final EntityManager _entityManager,
                    final QueryDefinition<BEANTYPE> _queryDefinition,
                    final Filter[] _additionalFilters,
                    final Object[] _sortPropertyIds,
                    final boolean[] _sortPropertyAscendingStates)
    {
        super(_queryDefinition, _additionalFilters, _sortPropertyIds, _sortPropertyAscendingStates);

        System.out.println("CALL [" + this.hashCode() + "] JpaQuery.CONSTRUCTOR()");

        if (null == _entityManager) {
            throw new NullPointerException("_entityManager is NULL");
        }

        this.entityManager = _entityManager;
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

            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BEANTYPE> criteriaQuery = criteriaBuilder.createQuery(beanType);
            final Root<BEANTYPE> from = criteriaQuery.from(beanType);
            criteriaQuery //
                .select(from);

            final boolean automaticOrderById = ((null != idPropertyId) && (idPropertyId instanceof String));
            final String idPropertyIdAsString = (automaticOrderById ? (String) idPropertyId : null);
            if (automaticOrderById && (null != nextStartId)) {
                final Expression<Comparable<Object>> idPropertyExpression = from.get(idPropertyIdAsString);
                @SuppressWarnings("unchecked")
                final Comparable<Object> nextIdPropertyStartValue = (Comparable<Object>) nextStartId;
                final Predicate nextIdPropertyPredicate = criteriaBuilder.greaterThanOrEqualTo(idPropertyExpression,
                        nextIdPropertyStartValue);
                criteriaQuery.where(nextIdPropertyPredicate);
            }

            if (automaticOrderById) {
                criteriaQuery.orderBy(criteriaBuilder.asc(from.get((idPropertyIdAsString))));
            }

            final TypedQuery<BEANTYPE> query = entityManager.createQuery(criteriaQuery);
            query.setMaxResults(_maxNumberOfObjects + 1); // + 1 for hasMore
                                                          // functionality
            final List<BEANTYPE> queryResult = query.getResultList();
            final int queryResultSize = (queryResult.isEmpty() ? 0 : queryResult.size());
            hasMore = (queryResultSize > _maxNumberOfObjects);
            if (!hasMore) {
                personList = queryResult;
                closeStream();
            }
            else {
                personList = queryResult.subList(0, _maxNumberOfObjects);

                if (automaticOrderById) {
                    final BEANTYPE firstBeanForNextQuery = queryResult.get(_maxNumberOfObjects);
                    try {
                        final Field field = firstBeanForNextQuery.getClass().getDeclaredField(idPropertyIdAsString);
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        nextStartId = field.get(firstBeanForNextQuery);
                    }
                    catch (final Exception _ex) {
                        throw new RuntimeException(_ex);
                    }
                }
            }

            lastHasMore = hasMore;
        }

        System.out.println("NOTE [" + this.hashCode() + "] JpaQuery.readNext(...): hasMore=" + hasMore
                + " nextStartId=" + nextStartId);

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
            nextStartId = null;
            streamClosed = Boolean.TRUE;
        }
    }
}
