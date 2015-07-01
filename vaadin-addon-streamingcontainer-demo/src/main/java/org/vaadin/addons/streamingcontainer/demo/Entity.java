/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer.demo;

import java.io.Serializable;

/**
 * The Class Entity.
 */
public class Entity implements Serializable
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2083924967400399194L;

    /** The id. */
    private final int id;

    /** The text. */
    private final String text;

    /**
     * Instantiates a new entity.
     *
     * @param _id the _id
     * @param _text the _text
     */
    public Entity(final int _id, final String _text)
    {
        this.id = _id;
        this.text = _text;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public final int getId()
    {
        return id;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public final String getText()
    {
        return text;
    }
}
