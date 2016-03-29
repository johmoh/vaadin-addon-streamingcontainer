package org.vaadin.addons.streamingcontainer.demo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.streamingcontainer.BeanDefinition;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryFactory;
import org.vaadin.addons.streamingcontainer.StatusPropertyDefinition;
import org.vaadin.addons.streamingcontainer.StreamingContainer;
import org.vaadin.addons.streamingcontainer.generic.GenericQueryDefinition;
import org.vaadin.addons.streamingcontainer.generic.GenericStreamingContainerBuilder;
import org.vaadin.addons.streamingcontainer.generic.GenericTypeBasedBeanDefinitionBuilder;
import org.vaadin.addons.streamingcontainer.jpa.generic.GenericJpaQueryFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class DemoUI.
 */
@Theme("demo")
@Title("StreamingContainer Add-on Demo")
@Widgetset("org.vaadin.addons.streamingcontainer.demo.DemoWidgetset")
public class DemoUI extends UI
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2665390395224393337L;

    /** The Constant PERSISTENCE_UNIT_NAME. */
    public static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";

    /** The Constant NUMBER_OF_TEST_DATA. */
    public static final int NUMBER_OF_TEST_DATA = 10000;

    /** The entity manager. */
    private static EntityManager entityManager = null;

    /**
     * Initialize db.
     */
    private synchronized void initializeDB()
    {
        if (null != entityManager) {
            return;
        }

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();

        final String[] firstNames = new String[] { "Jana", "Hannes", "Igor", "Max", "Steffanie", "Florentina",
                "Michael", "William", "Susanne", "Peter", "Dietmar" };
        final int firstNameNum = firstNames.length;
        final String[] lastNames = new String[] { "Mustermann", "Müller", "Bäcker", "Schmidt", "Dummbeutel", "Krause",
                "Wasweissich", "Nimmersatt", "Schaufelbagger", "Taugenichts", "Weiss", "Schulze" };
        final int lastNamesNum = lastNames.length;
        final Map<String, Integer> nameCounterMap = new HashMap<String, Integer>();

        System.out.println("NOTE DemoUI: BEGIN INSERTING DATA...");
        entityManager.setProperty("hibernate.show_sql", Boolean.FALSE);
        try {
            final EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            for (int i = 0; i < NUMBER_OF_TEST_DATA; ++i) {
                final String firstName = firstNames[i % firstNameNum];
                final String lastName = lastNames[i % lastNamesNum];
                final String fullName = (firstName + lastName);
                final int lastNameSuffixCounter;
                if (!nameCounterMap.containsKey(fullName)) {
                    lastNameSuffixCounter = 0;
                }
                else {
                    lastNameSuffixCounter = (1 + nameCounterMap.get(fullName));
                }
                nameCounterMap.put(fullName, lastNameSuffixCounter);
                final String completeLastName = (lastName + " " + String.valueOf(lastNameSuffixCounter));
                final int age = (20 + (i % 60));
                entityManager.persist(new Person(0, firstName, completeLastName, age));
            }

            entityManager.flush();
            transaction.commit();
        }
        catch (final Exception _ex) {
            _ex.printStackTrace();
        }
        System.out.println("NOTE DemoUI: ...ENDED INSERTING DATA");
    }

    /**
     * The Class Servlet.
     */
    @WebServlet(value = "/*", name = "DemoUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DemoUI.class, productionMode = false)
    public static class Servlet extends VaadinServlet
    {
        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = -9034621732245419226L;
    }

    /**
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(final VaadinRequest request)
    {
    	// Initialize DB - What a hack...
        initializeDB();

        // Build a bean definition - A bean definition describes the data of a bean in the container
        final BeanDefinition<Person> beanDefinition = new GenericTypeBasedBeanDefinitionBuilder<Person>(Person.class)
            .setPropertyDefinitionSortable("id", true)
            .setPropertyDefinitionSortable("lastName", true)
            .setPropertyDefinitionSortable("age", true)
            .addOrSetPropertyDefinition(StatusPropertyDefinition.getInstance())
            .build();
        
        // Build a query definition - A query definition describes the query used by a container
        final QueryDefinition<Person> queryDefinition = new GenericQueryDefinition<Person>(beanDefinition)
            .setSortProperties(new String[] { "id" }, new boolean[] { false })
            .addFilters( // Add some static search conditions
                    new SimpleStringFilter("lastName", "er", true, false), //
                    new Between("age", 25, 35) //
            );
        
        // Create a query factory - A query factory is used by the container to create a query if needed
        final QueryFactory<Person> queryFactory = new GenericJpaQueryFactory<Person>(entityManager);
        
        // Build a container
        final StreamingContainer<Person> container = new GenericStreamingContainerBuilder<Person>(queryFactory,
                queryDefinition) //
            .setInitialBatchSizeHint(200)
            .setBatchSizeHint(100)
            .setMaxQuerySizeHint(200000)
            .build();
        
        // Build layout - Here: create a VAADIN grid and set the container
        final Grid grid = new Grid(container);
        grid.setColumnReorderingAllowed(true);
        grid.setColumnOrder("id", "firstName", "lastName", "age");
        grid.setHeightByRows(20);
        grid.setHeightMode(HeightMode.ROW);
        grid.setWidth(50, Unit.PERCENTAGE);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();
        layout.addComponent(grid);
        setContent(layout);
    }
}
