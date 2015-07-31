/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer.demo;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vaadin.addons.streamingcontainer.AbstractQuery;
import org.vaadin.addons.streamingcontainer.GenericQueryResult;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryResult;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonQuery.
 */
@Stateful
public class PersonQuery extends AbstractQuery<Person>
{
    /** The Constant PERSISTENCE_UNIT_NAME. */
    public static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";

    /** The Constant MAX_NUMBER_OF_ENTITIES. */
    private static final int MAX_NUMBER_OF_ENTITIES = 123456;

    /** The Constant MAX_BATCH_SIZE. */
    private static final int MAX_BATCH_SIZE = 3459;

    /** The entity manager factory. */
    private static EntityManagerFactory entityManagerFactory = null;

    /** The initialized. */
    private boolean initialized = false;

    /** The stream closed. */
    private boolean streamClosed = true;

    /** The entity counter. */
    private int entityCounter = 0;

    /** The last has more. */
    private boolean lastHasMore = false;

    /**
     * Instantiates a new person query.
     *
     * @param _queryDefinition
     *            the _query definition
     */
    public PersonQuery(final QueryDefinition<Person> _queryDefinition,
                       final Filter[] _additionalFilters,
                       final Object[] _sortPropertyIds,
                       final boolean[] _sortPropertyAcendingStates)
    {
        super(_queryDefinition, _additionalFilters, _sortPropertyIds, _sortPropertyAcendingStates);
        System.out.println("CALL [" + this.hashCode() + "] PersonQuery.CONSTRUCTOR()");
    }

    /**
     * Initialize.
     */
    private void initialize()
    {
        // SIMULATE OPEN STREAM!
        System.out.println("CALL [" + this.hashCode() + "] PersonQuery.initialize()");
        streamClosed = false;
        initialized = true;
        if (null == entityManagerFactory) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }

    }

    /**
     * @see org.vaadin.addons.streamingcontainer.Query#readNext(int)
     */
    @Override
    public QueryResult<Person> readNext(final int _maxNumberOfObjects)
    {
        System.out.println("CALL [" + this.hashCode() + "] PersonQuery.readNext(" + _maxNumberOfObjects + ")");
        final List<Person> personList;
        final boolean hasMore;
        if (_maxNumberOfObjects <= 0) {
            personList = null;
            hasMore = lastHasMore;
        }
        else {
            if (!initialized) {
                initialize();
            }

            // SIMULATE LOAD NEXT ENTITIES
            System.out.println("NOTE [" + this.hashCode()
                    + "] PersonQuery.readNext(...) -> SIMULATE LOAD NEXT ENTITIES");
            EntityManager entityManager = null;
            try {
                entityManager = entityManagerFactory.createEntityManager();
                final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                final CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
                final Root<Person> from = criteriaQuery.from(Person.class);
                criteriaQuery //
                    .select(from)
                    //.where(criteriaBuilder.equal(from.get("firstName"), "Peter"))
                    .orderBy(criteriaBuilder.asc(from.get("id")));
                final TypedQuery<Person> query = entityManager.createQuery("from Person", Person.class);
                final int maxResult = Math.min(//
                        Math.min(_maxNumberOfObjects, MAX_BATCH_SIZE),//
                        MAX_NUMBER_OF_ENTITIES - entityCounter //
                );
                query.setMaxResults(maxResult);
                personList = query.getResultList();
                final int personListSize = personList.size();
                entityCounter += personListSize;
                hasMore = (personListSize > 0);
                lastHasMore = hasMore;
            }
            finally {
                if ((null != entityManager) && entityManager.isOpen()) {
                    entityManager.close();
                }
            }
        }

        final QueryResult<Person> result = new GenericQueryResult<Person>(personList, hasMore);
        return result;
    }

    /**
     * @see org.vaadin.test.web.prototype2.IStreamedReadingQuery#closeStream()
     */
    @Override
    public void closeStream()
    {
        System.out.println("CALL [" + this.hashCode() + "] PersonQuery.closeStream()");
        if (!streamClosed) {
            // SIMULATE CLOSE STREAM!
            System.out.println("NOTE PersonQuery.closeStream() -> SIMULATE CLOSE STREAM");
            streamClosed = true;
        }
    }
}
