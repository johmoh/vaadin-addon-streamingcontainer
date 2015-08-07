package org.vaadin.addons.streamingcontainer.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.AbstractJunctionFilter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.IsNull;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericJpaTypedQueryBuilder.
 *
 * @param <BEANTYPE>
 *            the generic type
 */
public class GenericJpaTypedQueryBuilder<BEANTYPE> implements JpaTypedQueryBuilder<BEANTYPE>
{
    /** The Constant instance. */
    @SuppressWarnings("rawtypes")
    private static final GenericJpaTypedQueryBuilder instance = new GenericJpaTypedQueryBuilder();

    /**
     * Instantiates a new jpa utility.
     */
    private GenericJpaTypedQueryBuilder()
    {
        // INTENTIONALLY LEFT BLANK
    }

    /**
     * Gets the single instance of GenericJpaTypedQueryBuilder.
     *
     * @param <BEANTYPE>
     *            the generic type
     * @return single instance of GenericJpaTypedQueryBuilder
     */
    public static <BEANTYPE> GenericJpaTypedQueryBuilder<BEANTYPE> getInstance()
    {
        @SuppressWarnings("unchecked")
        final GenericJpaTypedQueryBuilder<BEANTYPE> result = instance;
        return result;
    }

    /**
     * Builds the combined filter.
     *
     * @param _fixedFilters
     *            the _fixed filters
     * @param _additionalFilters
     *            the _additional filters
     * @return the filter
     */
    public Filter buildCombinedFilter(final Collection<Filter> _fixedFilters, final Filter[] _additionalFilters)
    {
        int filterNum = 0;
        final boolean hasFixedFilters = ((null != _fixedFilters) && !_fixedFilters.isEmpty());
        if (hasFixedFilters) {
            filterNum += _fixedFilters.size();
        }
        final boolean hasAdditionalFilters = (null != _additionalFilters);
        if (hasAdditionalFilters) {
            filterNum += _additionalFilters.length;
        }

        final Filter result;
        if (filterNum == 0) {
            result = null;
        }
        else if (filterNum == 1) {
            if (hasFixedFilters) {
                result = _fixedFilters.iterator().next();
            }
            else {
                result = _additionalFilters[0];
            }
        }
        else {
            final Filter[] allFilters = new Filter[filterNum];
            int idx = 0;
            if (hasFixedFilters) {
                for (final Filter filter : _fixedFilters) {
                    allFilters[idx] = filter;
                    ++idx;
                }
            }
            if (hasAdditionalFilters) {
                for (final Filter _additionalFilter : _additionalFilters) {
                    allFilters[idx] = _additionalFilter;
                    ++idx;
                }
            }

            result = new And(allFilters);
        }

        return result;
    }

    /**
     * Gets the path.
     *
     * @param <VALUE_TYPE>
     *            the generic type
     * @param _propertyId
     *            the _property id
     * @param _from
     *            the _from
     * @return the path
     */
    public <VALUE_TYPE> Path<VALUE_TYPE> getPath(final Object _propertyId, final Root<BEANTYPE> _from)
    {
        if (null == _propertyId) {
            throw new NullPointerException("_propertyId is NULL");
        }
        if (!(_propertyId instanceof String)) {
            throw new IllegalArgumentException("_propertyId is not of type \"String\" (type=\""
                    + _propertyId.getClass().getName() + "\")");
        }
        final String propertyIdAsString = _propertyId.toString();
        if (propertyIdAsString.isEmpty()) {
            throw new IllegalArgumentException("_propertyId represents an empty \"String\"");
        }
        if (null == _from) {
            throw new NullPointerException("_from is NULL");
        }

        final Path<VALUE_TYPE> result = _from.get(propertyIdAsString);
        return result;
    }

    /**
     * Builds the where predicate from filter.
     *
     * @param _filter
     *            the _filter
     * @param _criteriaBuilder
     *            the _criteria builder
     * @param _from
     *            the _from
     * @return the predicate
     */
    public Predicate buildWherePredicateFromFilter(final Filter _filter,
                                                   final CriteriaBuilder _criteriaBuilder,
                                                   final Root<BEANTYPE> _from)
    {
        if (null == _filter) {
            throw new NullPointerException("_filter is NULL");
        }
        if (null == _criteriaBuilder) {
            throw new NullPointerException("_criteriaBuilder is NULL");
        }
        if (null == _from) {
            throw new NullPointerException("_from is NULL");
        }

        final Predicate result;
        // JUNCTION FILTER
        if (_filter instanceof AbstractJunctionFilter) {
            final AbstractJunctionFilter junctionFilter = (AbstractJunctionFilter) _filter;
            final Collection<Filter> subFilters = junctionFilter.getFilters();

            if ((null == subFilters) || subFilters.isEmpty()) {
                result = null;
            }
            else if (subFilters.size() == 1) {
                final Filter subFilter = subFilters.iterator().next();
                result = buildWherePredicateFromFilter(subFilter, _criteriaBuilder, _from);
            }
            else {
                final Collection<Predicate> subPredicates = new ArrayList<Predicate>(subFilters.size());
                for (final Filter subFilter : subFilters) {
                    final Predicate subPredicate = buildWherePredicateFromFilter(subFilter, _criteriaBuilder, _from);
                    if (null != subPredicate) {
                        subPredicates.add(subPredicate);
                    }
                }
                final Predicate[] subPredicatesAsArray = subPredicates.toArray(new Predicate[subPredicates.size()]);

                // JUNCTION FILTER - AND
                if (_filter instanceof And) {
                    result = _criteriaBuilder.and(subPredicatesAsArray);
                }
                // JUNCTION FILTER - OR
                else if (_filter instanceof Or) {
                    result = _criteriaBuilder.or(subPredicatesAsArray);
                }
                // JUNCTION FILTER - UNSUPPORTED
                else {
                    throw new UnsupportedOperationException("Unsupported junction filter of type \""
                            + _filter.getClass().getName() + "\"");
                }
            }
        }
        // BETWEEN
        else if (_filter instanceof Between) {
            final Between betweenFilter = (Between) _filter;
            final Object propertyId = betweenFilter.getPropertyId();
            final Object startValue = betweenFilter.getStartValue();
            if (!(startValue instanceof Comparable)) {
                throw new UnsupportedOperationException(
                        "Unsupported start value of between filter. Only \"Comparable\" values are supported (value-type=\""
                                + startValue.getClass().getName() + "\").");
            }

            final Object endValue = betweenFilter.getEndValue();
            if (!(endValue instanceof Comparable)) {
                throw new UnsupportedOperationException(
                        "Unsupported end value of between filter. Only \"Comparable\" values are supported (value-type=\""
                                + startValue.getClass().getName() + "\").");
            }

            @SuppressWarnings("unchecked")
            final Comparable<Object> startValueAsComparable = (Comparable<Object>) startValue;
            @SuppressWarnings("unchecked")
            final Comparable<Object> endValueAsComparable = (Comparable<Object>) endValue;

            final Path<Comparable<Object>> path = getPath(propertyId, _from);
            result = _criteriaBuilder.between( //
                    path, //
                    startValueAsComparable, //
                    endValueAsComparable //
                );
        }
        // COMPARE
        else if (_filter instanceof Compare) {
            final Compare compareFilter = (Compare) _filter;
            final Object propertyId = compareFilter.getPropertyId();
            final Object value = compareFilter.getValue();
            if (null == value) {
                throw new NullPointerException("Filter value is NULL");
            }

            // COMPARE - EQUAL
            if (compareFilter instanceof Compare.Equal) {
                final Path<Object> path = getPath(propertyId, _from);
                result = _criteriaBuilder.equal(path, value);
            }
            // COMPARE - ...
            else {
                if (!(value instanceof Comparable)) {
                    throw new UnsupportedOperationException(
                            "Unsupported value of compare filter. Only \"Comparable\" values are supported (value-type=\""
                                    + value.getClass().getName() + "\").");
                }

                final Path<Comparable<Object>> path = getPath(propertyId, _from);

                @SuppressWarnings("unchecked")
                final Comparable<Object> valueAsComparable = (Comparable<Object>) value;
                // COMPARE - GREATER
                if (compareFilter instanceof Compare.Greater) {
                    result = _criteriaBuilder.greaterThan(path, valueAsComparable);
                }
                // COMPARE - GREATER OR EQUAL
                else if (compareFilter instanceof Compare.GreaterOrEqual) {
                    result = _criteriaBuilder.greaterThanOrEqualTo(path, valueAsComparable);
                }
                // COMPARE - LESS
                else if (compareFilter instanceof Compare.Less) {
                    result = _criteriaBuilder.lessThan(path, valueAsComparable);
                }
                // COMPARE - LESS OR EQUAL
                else if (compareFilter instanceof Compare.LessOrEqual) {
                    result = _criteriaBuilder.lessThanOrEqualTo(path, valueAsComparable);
                }
                // COMPARE - UNSUPPORTED
                else {
                    throw new UnsupportedOperationException("Unsupported compare filter of type \""
                            + compareFilter.getClass().getName() + "\"");
                }
            }
        }
        // IS NULL
        else if (_filter instanceof IsNull) {
            final IsNull isNullFilter = (IsNull) _filter;
            final Object propertyId = isNullFilter.getPropertyId();
            final Path<Object> path = getPath(propertyId, _from);
            result = path.isNull();
        }
        // LIKE
        else if (_filter instanceof Like) {
            final Like likeFilter = (Like) _filter;
            final Object propertyId = likeFilter.getPropertyId();
            final String value = likeFilter.getValue();
            final Path<String> path = getPath(propertyId, _from);
            if (null == value) {
                // value is NULL
                // -=> NULL is processed like an empty string
                // -=> means: accept strings which contain an empty string
                // -=> between two consecutive characters there is always an empty string
                // -=> accept every string
                result = null;
            }
            else {
                final boolean caseSensitive = likeFilter.isCaseSensitive();
                final String likeText = (caseSensitive ? value : value.toLowerCase());
                final Expression<String> expression = (caseSensitive ? path : _criteriaBuilder.lower(path));
                result = _criteriaBuilder.like(expression, likeText);
            }
        }
        // NOT
        else if (_filter instanceof Not) {
            final Not notFilter = (Not) _filter;
            final Filter subFilter = notFilter.getFilter();
            final Predicate subPredicate = buildWherePredicateFromFilter(subFilter, _criteriaBuilder, _from);
            result = subPredicate.not();
        }
        // SIMPLE STRING FILTER
        else if ((_filter instanceof Like) || (_filter instanceof SimpleStringFilter)) {
            final SimpleStringFilter simpleStringFilter = (SimpleStringFilter) _filter;
            final Object propertyId = simpleStringFilter.getPropertyId();
            final String value = simpleStringFilter.getFilterString();
            final Path<String> path = getPath(propertyId, _from);
            if (null == value) {
                // value is NULL
                // -=> NULL is processed like an empty string
                // -=> means: accept strings which contain an empty string
                // -=> between two consecutive characters there is always an empty string
                // -=> accept every string
                result = null;
            }
            else if (value.isEmpty()) {
                // value is empty
                // -=> means: accept strings which contain an empty string
                // -=> between two consecutive characters there is always an empty string
                // -=> accept every string
                result = null;
            }
            else {
                final boolean valueStartsWithWildcard = value.startsWith("%");
                final boolean valueEndsWithWildcard = value.endsWith("%");
                if (((valueStartsWithWildcard ? 1 : 0) + (valueEndsWithWildcard ? 1 : 0)) >= value.length()) {
                    // value IN {"%", "%%"}
                    // -=> means: accept every string
                    result = null;
                }
                else {
                    final boolean ignoreCase = simpleStringFilter.isIgnoreCase();
                    final Expression<String> expression = (ignoreCase ? _criteriaBuilder.lower(path) : path);
                    String likeText = (ignoreCase ? value.toLowerCase() : value);

                    final boolean onlyMatchPrefix = simpleStringFilter.isOnlyMatchPrefix();
                    if (!onlyMatchPrefix && !valueStartsWithWildcard) {
                        likeText = "%" + likeText;
                    }
                    if (!valueEndsWithWildcard) {
                        likeText += "%";
                    }

                    result = _criteriaBuilder.like(expression, likeText);
                }
            }
        }
        // UNSUPPORTED
        else {
            throw new UnsupportedOperationException("Unsuppored filter of type \"" + _filter.getClass().getName()
                    + "\"");
        }

        return result;
    }

    /**
     * Builds the where predicate.
     *
     * @param _fixedFilters
     *            the fixed filters
     * @param _additionalFilters
     *            the _additional filters
     * @param _criteriaBuilder
     *            the _criteria builder
     * @param _from
     *            the _from
     * @return the predicate
     */
    public Predicate buildWherePredicate(final Collection<Filter> _fixedFilters,
                                         final Filter[] _additionalFilters,
                                         final CriteriaBuilder _criteriaBuilder,
                                         final Root<BEANTYPE> _from)
    {
        if (null == _criteriaBuilder) {
            throw new NullPointerException("_criteriaBuilder is NULL");
        }
        if (null == _from) {
            throw new NullPointerException("_from is NULL");
        }

        final Filter combinedFilter = buildCombinedFilter(_fixedFilters, _additionalFilters);
        final Predicate result;
        if (null == combinedFilter) {
            result = null;
        }
        else {
            result = buildWherePredicateFromFilter(combinedFilter, _criteriaBuilder, _from);
        }

        return result;
    }

    /**
     * Builds the order condition.
     *
     * @param _idPropertyId
     *            the _id property id
     * @param _sortPropertyIds
     *            the _sort property ids
     * @param _sortPropertyAscendingStates
     *            the _sort property ascending states
     * @param _criteriaBuilder
     *            the _criteria builder
     * @param _from
     *            the _from
     * @return the list
     */
    public List<Order> buildOrderCondition(final String _idPropertyId,
                                           final Object[] _sortPropertyIds,
                                           final boolean[] _sortPropertyAscendingStates,
                                           final CriteriaBuilder _criteriaBuilder,
                                           final Root<BEANTYPE> _from)
    {
        if ((null == _sortPropertyIds) != (null == _sortPropertyAscendingStates)) {
            throw new IllegalArgumentException(
                    "One array is NULL and the other is not (_sortPropertyIds, _sortPropertyAscendingStates)");
        }
        if ((null != _sortPropertyIds) && (_sortPropertyIds.length != _sortPropertyAscendingStates.length)) {
            throw new IllegalArgumentException(
                    "Arrays have diffenent size (_sortPropertyIds, _sortPropertyAscendingStates)");
        }
        if (null == _criteriaBuilder) {
            throw new NullPointerException("_criteriaBuilder is NULL");
        }
        if (null == _from) {
            throw new NullPointerException("_from is NULL");
        }

        final List<Order> result;
        if ((null == _sortPropertyIds) || (_sortPropertyIds.length == 0)) {
            result = null;
        }
        else {
            final List<Order> orders = new ArrayList<Order>(_sortPropertyIds.length + 1);
            boolean addIdPropertyOrdering = ((null != _idPropertyId) && !_idPropertyId.isEmpty());
            for (int i = 0; i < _sortPropertyIds.length; ++i) {
                final Object propertyId = _sortPropertyIds[i];
                if (null != propertyId) {
                    final String propertyIdAsString = propertyId.toString();
                    if (addIdPropertyOrdering && propertyId.equals(_idPropertyId)) {
                        addIdPropertyOrdering = false;
                    }

                    final Path<Object> path = _from.get(propertyIdAsString);
                    final boolean sortAscending = _sortPropertyAscendingStates[i];
                    final Order order = (sortAscending ? _criteriaBuilder.asc(path) : _criteriaBuilder.desc(path));
                    orders.add(order);
                }
            }

            if (addIdPropertyOrdering) {
                final Path<Object> path = _from.get(_idPropertyId);
                final Order order = _criteriaBuilder.asc(path);
                orders.add(order);
            }

            result = orders;
        }

        return result;
    }

    /**
     * Builds the criteria query.
     *
     * @param beanType
     *            the bean type
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
     * @param _entityManager
     *            the _entity manager
     * @return the criteria query
     */
    public CriteriaQuery<BEANTYPE> buildCriteriaQuery(final Class<BEANTYPE> beanType,
                                                      final String _idPropertyId,
                                                      final Collection<Filter> _fixedFilters,
                                                      final Filter[] _additionalFilters,
                                                      final Object[] _sortPropertyIds,
                                                      final boolean[] _sortPropertyAscendingStates,
                                                      final EntityManager _entityManager)
    {
        if ((null == _sortPropertyIds) != (null == _sortPropertyAscendingStates)) {
            throw new IllegalArgumentException(
                    "One array is NULL and the other is not (_sortPropertyIds, _sortPropertyAscendingStates)");
        }
        if ((null != _sortPropertyIds) && (_sortPropertyIds.length != _sortPropertyAscendingStates.length)) {
            throw new IllegalArgumentException(
                    "Arrays have diffenent size (_sortPropertyIds, _sortPropertyAscendingStates)");
        }
        if (null == _entityManager) {
            throw new NullPointerException("_entityManager is NULL");
        }

        final CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
        final CriteriaQuery<BEANTYPE> criteriaQuery = criteriaBuilder.createQuery(beanType);
        final Root<BEANTYPE> from = criteriaQuery.from(beanType);
        criteriaQuery //
            .select(from);

        final Predicate wherePredicate = buildWherePredicate( //
                _fixedFilters, //
                _additionalFilters, //
                criteriaBuilder, //
                from //
        );
        if (null != wherePredicate) {
            criteriaQuery.where(wherePredicate);
        }

        final List<Order> orders = buildOrderCondition( //
                _idPropertyId, //
                _sortPropertyIds, //
                _sortPropertyAscendingStates, //
                criteriaBuilder, //
                from //
        );
        if ((null != orders) && !orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }

        return criteriaQuery;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.jpa.JpaTypedQueryBuilder#build(java.lang.Class, java.lang.String,
     *      java.util.Collection, com.vaadin.data.Container.Filter[], java.lang.Object[], boolean[], int, int,
     *      javax.persistence.EntityManager)
     */
    @Override
    public TypedQuery<BEANTYPE> build(final Class<BEANTYPE> _beanType,
                                      final String _idPropertyId,
                                      final Collection<Filter> _fixedFilters,
                                      final Filter[] _additionalFilters,
                                      final Object[] _sortPropertyIds,
                                      final boolean[] _sortPropertyAscendingStates,
                                      final int _firstRowNumber,
                                      final int _maxNumberOfObjects,
                                      final EntityManager _entityManager)
    {
        if ((null == _sortPropertyIds) != (null == _sortPropertyAscendingStates)) {
            throw new IllegalArgumentException(
                    "One array is NULL and the other is not (_sortPropertyIds, _sortPropertyAscendingStates)");
        }
        if ((null != _sortPropertyIds) && (_sortPropertyIds.length != _sortPropertyAscendingStates.length)) {
            throw new IllegalArgumentException(
                    "Arrays have diffenent size (_sortPropertyIds, _sortPropertyAscendingStates)");
        }
        if (_firstRowNumber < 0) {
            throw new IllegalArgumentException("_firstRowNum must be greater or equal 0 (value=" + _firstRowNumber
                    + ")");
        }
        if (_maxNumberOfObjects < 0) {
            throw new IllegalArgumentException("_maxNumberOfObjects must be greater or equal 0 (value="
                    + _maxNumberOfObjects + ")");
        }
        if (null == _entityManager) {
            throw new NullPointerException("_entityManager is NULL");
        }

        final CriteriaQuery<BEANTYPE> criteriaQuery = buildCriteriaQuery( //
                _beanType, //
                _idPropertyId, //
                _fixedFilters, //
                _additionalFilters, //
                _sortPropertyIds, //
                _sortPropertyAscendingStates, //
                _entityManager //
        );
        final TypedQuery<BEANTYPE> result = _entityManager //
            .createQuery(criteriaQuery)
            .setFirstResult(_firstRowNumber)
            .setMaxResults(_maxNumberOfObjects);
        return result;
    }
}
