package org.vaadin.addons.streamingcontainer.demo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.streamingcontainer.GenericBeanDefinition;
import org.vaadin.addons.streamingcontainer.GenericQueryDefinition;
import org.vaadin.addons.streamingcontainer.LazyStreamingContainer;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryFactory;
import org.vaadin.addons.streamingcontainer.StreamingContainer;

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

    /**
     * Initialize db.
     *
     * @return the entity manager
     */
    private EntityManager initializeDB()
    {
        try {
            Class.forName("org.h2.Driver");
        }
        catch (final ClassNotFoundException _ex) {
            throw new RuntimeException(_ex);
        }
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

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

        return entityManager;
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
        final GenericBeanDefinition<Person> beanDefinition = new GenericBeanDefinition<Person>(Person.class)
            .addOrSetPropertyDefinitionsFromType()
            .setIdPropertyId("id");
        beanDefinition.getAsGenericPropertyDefinition("id").setSortable(true);
        // beanDefinition.getAsGenericPropertyDefinition("firstName").setSortable(true);
        beanDefinition.getAsGenericPropertyDefinition("lastName").setSortable(true);
        beanDefinition.getAsGenericPropertyDefinition("age").setSortable(true);
        final QueryDefinition<Person> queryDefinition = new GenericQueryDefinition<Person>(beanDefinition)
            .setBatchSizeHint(100)
            .setMaxQuerySizeHint(200000)
            .setSortProperties(new String[] { "id" }, new boolean[] { false })
            .addFilters( //
                    new SimpleStringFilter("lastName", "er", true, false), //
                    new Between("age", 25, 35) //
            );
        final EntityManager entityManager = initializeDB();
        final QueryFactory<Person> queryFactory = new GenericJpaQueryFactory<Person>(entityManager);
        final StreamingContainer<?> container = new LazyStreamingContainer<Person>(queryFactory, queryDefinition);
        final Grid grid = new Grid(container);
        grid.setColumnReorderingAllowed(true);
        grid.setColumnOrder("id", "firstName", "lastName", "age");
        grid.setHeightByRows(20);
        grid.setHeightMode(HeightMode.ROW);
        grid.setWidth(50, Unit.PERCENTAGE);

        // final HeaderRow gridHeader = grid.addHeaderRowAt(1);
        // gridHeader.getCell("id").setComponent(new TextField());

        // final FooterRow gridFooter = grid.addFooterRowAt(0);
        // final Collection<Object> columnIds = new ArrayList<Object>(grid.getColumns().size());
        // for (final Column column : grid.getColumns()) {
        // columnIds.add(column.getPropertyId());
        // }
        // gridFooter.join(columnIds.toArray(new Object[columnIds.size()]));

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        // layout.setStyleName("demoContentLayout");
        // layout.setSizeFull();
        layout.addComponent(grid);
        setContent(layout);
    }
}
