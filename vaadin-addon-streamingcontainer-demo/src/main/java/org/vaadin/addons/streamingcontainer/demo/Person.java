/**
 * @author johmoh
 */
package org.vaadin.addons.streamingcontainer.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 */
@Entity
@Table(name = "T_ENTITY")
public class Person implements Serializable
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2083924967400399194L;

    /** The id. */
    @Id
    @GeneratedValue
    private long id;

    /** The first name. */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /** The last name. */
    @Column(name = "LAST_NAME")
    private String lastName;

    /** The age. */
    @Column(name = "AGE")
    private int age;

    /**
     * Instantiates a new person.
     */
    public Person()
    {
        this(0, null, null, -1);
    }

    /**
     * Instantiates a new person.
     *
     * @param _id
     *            the _id
     * @param _firstName
     *            the _first name
     * @param _lastName
     *            the _last name
     * @param _age
     *            the _age
     */
    public Person(final long _id, final String _firstName, final String _lastName, final int _age)
    {
        this.id = _id;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.age = _age;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public final long getId()
    {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final long id)
    {
        this.id = id;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName
     *            the new first name
     */
    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName
     *            the new last name
     */
    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age
     *            the new age
     */
    public void setAge(final int age)
    {
        this.age = age;
    }
}
