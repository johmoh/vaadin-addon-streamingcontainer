package org.vaadin.addons.streamingcontainer;

import com.vaadin.data.Container.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public final class Constants
{
    /*************************************************************************
     * LIMITS
     *************************************************************************/

    /**
     * The Class Limits.
     */
    public static final class Limits
    {
        /**
         * Private constructor because it is not allowed to instantiate objects.
         */
        private Limits()
        {
        }

        /** The Constant MIN_BATCH_SIZE_LIMIT. */
        public static final int MIN_BATCH_SIZE_LIMIT = 25;

        /** The Constant MIN_INITIAL_BATCH_SIZE_LIMIT. */
        public static final int MIN_INITIAL_BATCH_SIZE_LIMIT = 40 + 1;

        /** The Constant MAX_NUMBER_OF_ITEMS_LIMIT. */
        public static final int MAX_NUMBER_OF_ITEMS_LIMIT = 100000;
    }

    /*************************************************************************
     * DEFAULTS
     *************************************************************************/

    /**
     * The Class Defaults.
     */
    public static final class Defaults
    {
        /**
         * Private constructor because it is not allowed to instantiate objects.
         */
        private Defaults()
        {
        }

        /** The Constant DEFAULT_BATCH_SIZE_HINT. */
        public static final int DEFAULT_BATCH_SIZE_HINT = 100;

        /** The Constant DEFAULT_MAX_QUERY_SIZE_HINT. */
        public static final int DEFAULT_MAX_QUERY_SIZE_HINT = 5000;
    }

    /*************************************************************************
     * OTHER CONSTANTS
     *************************************************************************/

    /**
     * Private constructor because it is not allowed to instantiate objects.
     */
    private Constants()
    {
    }

    /** The Constant EMPTY_ADDITIONAL_FILTER. */
    public static final Filter[] EMPTY_ADDITIONAL_FILTERS = new Filter[] {};

    /** The Constant EMPTY_SORT_PROPERTY_IDS. */
    public static final Object[] EMPTY_SORT_PROPERTY_IDS = new Object[] {};

    /** The Constant EMPTY_SORT_PROPERTY_ASCENDING_STATES. */
    public static final boolean[] EMPTY_SORT_PROPERTY_ASCENDING_STATES = new boolean[] {};
}
