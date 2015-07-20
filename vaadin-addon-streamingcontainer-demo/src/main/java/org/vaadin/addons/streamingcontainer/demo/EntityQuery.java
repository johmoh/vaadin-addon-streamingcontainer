/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer.demo;

import java.util.ArrayList;

import org.vaadin.addons.streamingcontainer.AbstractQuery;
import org.vaadin.addons.streamingcontainer.GenericQueryResult;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryResult;

import com.vaadin.data.Container.Filter;

/**
 * The Class EntityQuery.
 */
public class EntityQuery extends AbstractQuery<Entity>
{
    /** The Constant MAX_NUMBER_OF_ENTITIES. */
    private static final int MAX_NUMBER_OF_ENTITIES = 123456;

    /** The Constant MAX_BATCH_SIZE. */
    private static final int MAX_BATCH_SIZE = 3459;

    /** The entity counter. */
    private int entityCounter = 0;

    /** The initialized. */
    private boolean initialized = false;

    /** The stream closed. */
    private boolean streamClosed = true;

    /**
     * Instantiates a new entity query.
     *
     * @param _queryDefinition
     *            the _query definition
     */
    public EntityQuery(final QueryDefinition<Entity> _queryDefinition,
                       final Filter[] _additionalFilters,
                       final Object[] _sortPropertyIds,
                       final boolean[] _sortPropertyAcendingStates)
    {
        super(_queryDefinition, _additionalFilters, _sortPropertyIds, _sortPropertyAcendingStates);
        System.out.println("CALL [" + this.hashCode() + "] EntityQuery.CONSTRUCTOR()");
    }

    /**
     * Initialize.
     */
    private void initialize()
    {
        System.out.println("CALL [" + this.hashCode() + "] EntityQuery.initialize()");
        // SIMULATE OPEN STREAM!
        System.out.println("NOTE EntityQuery.initialize() -> SIMULATE OPEN STREAM");
        streamClosed = false;
        initialized = true;
    }

    /**
     * @see org.vaadin.addons.streamingcontainer.Query#readNext(int)
     */
    @Override
    public QueryResult<Entity> readNext(final int _maxNumberOfObjects)
    {
        System.out.println("CALL [" + this.hashCode() + "] EntityQuery.readNext(" + _maxNumberOfObjects + ")");
        final ArrayList<Entity> newEntities;
        if (_maxNumberOfObjects <= 0) {
            newEntities = null;
        }
        else {
            if (!initialized) {
                initialize();
            }

            // SIMULATE LOAD NEXT ENTITIES
            System.out.println("NOTE [" + this.hashCode()
                    + "] EntityQuery.readNext(...) -> SIMULATE LOAD NEXT ENTITIES");
            final int numberOfEntitiesToLoad = Math.min(_maxNumberOfObjects, MAX_NUMBER_OF_ENTITIES - entityCounter);
            newEntities = new ArrayList<Entity>(numberOfEntitiesToLoad);
            int i = 0;
            while (i < numberOfEntitiesToLoad) {
                ++i;

                final int id = entityCounter + i;
                final Entity entity = new Entity(id, "Entity #" + id);
                newEntities.add(entity);

                if (i >= MAX_BATCH_SIZE) {
                    break;
                }
            }
            entityCounter += i;
        }

        final boolean hasMore = (entityCounter < MAX_NUMBER_OF_ENTITIES);

        final QueryResult<Entity> result = new GenericQueryResult<Entity>(newEntities, hasMore);
        return result;
    }

    /**
     * @see org.vaadin.test.web.prototype2.IStreamedReadingQuery#closeStream()
     */
    @Override
    public void closeStream()
    {
        System.out.println("CALL [" + this.hashCode() + "] EntityQuery.closeStream()");
        if (!streamClosed) {
            // SIMULATE CLOSE STREAM!
            System.out.println("NOTE EntityQuery.closeStream() -> SIMULATE CLOSE STREAM");
            streamClosed = true;
        }
    }
}
