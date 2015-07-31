package org.vaadin.addons.streamingcontainer.demo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.streamingcontainer.BeanDefinition;
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
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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
    /** The Constant PERSISTENCE_UNIT_NAME. */
    public static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";

    private static EntityManagerFactory entityManagerFactory;
    {
        try {
            Class.forName("org.h2.Driver");
            entityManagerFactory = Persistence.createEntityManagerFactory(PersonQuery.PERSISTENCE_UNIT_NAME);
        }
        catch (ClassNotFoundException _ex) {
            entityManagerFactory = null;
            _ex.printStackTrace();
        }
    }

    private static EntityManager entityManager;
    {
        entityManager = entityManagerFactory.createEntityManager();
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2665390395224393337L;

    /**
     * INITIALIZE: Insert data into database.
     */
    {
        final String[] firstNames = new String[] { "Jana", "Hannes", "Igor", "Max", "Steffanie", "Florentina",
                "Michael", "William", "Susanne", "Peter", "Dietmar" };
        final int firstNameNum = firstNames.length;
        final String[] lastNames = new String[] { "Mustermann", "Müller", "Bäcker", "Schmidt", "Dummbeutel", "Krause",
                "Wasweissich", "Nimmersatt", "Schaufelbagger", "Taugenichts", "Weiss" };
        final int lastNamesNum = lastNames.length;
        final Map<String, Integer> nameCounterMap = new HashMap<String, Integer>();
        final int step = 73;

        System.out.println("NOTE DemoUI: BEGIN INSERTING DATA...");
        try {
            final EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            int variant = 0;
            for (int i = 0; i < 10000; ++i) {
                final String firstName = firstNames[variant % firstNameNum];
                final String lastName = lastNames[variant % lastNamesNum];
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
                final int age = (20 + ((i + variant) % step)); 
                entityManager.persist(new Person(0, firstName, completeLastName, age));
                variant = ((variant + i + step) % step);
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
        final BeanDefinition<Person> beanDefinition = new GenericBeanDefinition<Person>(Person.class)
            .addOrSetPropertyDefinitionsFromType()
            .setIdPropertyId("id");
        final QueryDefinition<Person> queryDefinition = new GenericQueryDefinition<Person>(beanDefinition)
            .setBatchSizeHint(2547)
            .setMaxQuerySizeHint(200000)
            .setSortProperties(new String[] { "id" }, new boolean[] { false });
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final QueryFactory<Person> queryFactory = new JpaQueryFactory<Person>(entityManager, PersonQuery.class);
        final StreamingContainer<?> container = new LazyStreamingContainer<Person>(queryFactory, queryDefinition);
        final Grid grid = new Grid(container);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        // layout.setStyleName("demoContentLayout");
        // layout.setSizeFull();
        layout.addComponent(grid);
        setContent(layout);
    }
}
