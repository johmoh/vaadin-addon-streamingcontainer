package org.vaadin.addons.streamingcontainer.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.streamingcontainer.BeanDefinition;
import org.vaadin.addons.streamingcontainer.GenericBeanDefinition;
import org.vaadin.addons.streamingcontainer.GenericQueryDefinition;
import org.vaadin.addons.streamingcontainer.GenericQueryFactory;
import org.vaadin.addons.streamingcontainer.QueryDefinition;
import org.vaadin.addons.streamingcontainer.QueryFactory;
import org.vaadin.addons.streamingcontainer.LazyStreamingContainer;
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
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2665390395224393337L;

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
        final BeanDefinition<Entity> beanDefinition = new GenericBeanDefinition<Entity>(Entity.class)
            .addOrSetDefinitionsFromType()
            .setIdPropertyId("id");
        final QueryDefinition<Entity> queryDefinition = new GenericQueryDefinition<Entity>(beanDefinition)
            .setBatchSizeHint(2547)
            .setMaxQuerySizeHint(200000)
            .setSortProperties(new String[] { "id" }, new boolean[] { false });
        final QueryFactory<Entity> queryFactory = new GenericQueryFactory<Entity>(EntityQuery.class);
        final StreamingContainer<?> container = new LazyStreamingContainer<Entity>(queryFactory, queryDefinition);
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
