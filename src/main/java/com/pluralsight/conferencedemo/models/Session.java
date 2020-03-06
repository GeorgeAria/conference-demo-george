package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

//This is a JPA entity.
//"sessions" is the name of the database table in out PostgreSQL database
//Class name is Session because it represents one row/instance of the "sessions" table
//@JsonIgnoreProperties: When serializing a Hibernate object, you don't want to serialize hibernateLazyInitializer or handler.


@Entity(name = "sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session
{
    //@Id tells JPA that the IDE is the primary key field
    //@GeneratedValue specifies how the primary key field is populated on a new record insert
    //With GenerationType.IDENTITY, JPA utilizes the Postgres created sequence for primary key values

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Note that variable names are NOT in camel case
    //They instead match the same naming conventions as the database column names
    //JPA will auto-bind to the columns as a result, allowing for speed an convenience

    private Long session_id;

    private String session_name;
    private String session_description;
    private Integer session_length;

    //We need to have JPA set up the SQL JOIN for us automatically when we call the "speakers" attribute.
    //- @ManytoMany means we are setting up a Many-to-many relationship and that we have a mapping
    //@JoinTable in our database.
    //@JoinTable lets us define that JoinTable and the foreign key columns.
    //- That table is "session_speakers", which has "session_id" and "speaker_id" foreign key to the
    //appropriate tables in the relationship.

    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))

    private List<Speaker> speakers;

    public Session()
    {

    }

    public Long getSession_id()
    {
        return session_id;
    }

    public void setSession_id(Long session_id)
    {
        this.session_id = session_id;
    }

    public String getSession_name()
    {
        return session_name;
    }

    public void setSession_name(String session_name)
    {
        this.session_name = session_name;
    }

    public String getSession_description()
    {
        return session_description;
    }

    public void setSession_description(String session_description)
    {
        this.session_description = session_description;
    }

    public Integer getSession_length()
    {
        return session_length;
    }

    public void setSession_length(Integer session_length)
    {
        this.session_length = session_length;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }
}
