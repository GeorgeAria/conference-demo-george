package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

//This is a JPA entity.
//"speakers" is the name of the database table in out PostgreSQL database
//Class name is Speaker because it represents one row/instance of the "speakers" table
//@JsonIgnoreProperties: When serializing a Hibernate object, you don't want to serialize hibernateLazyInitializer or handler.


@Entity(name = "speakers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker
{
    //@Id tells JPA that the IDE is the primary key field
    //@GeneratedValue specifies how the primary key field is populated on a new record insert
    //With GenerationType.IDENTITY, JPA utilizes the Postgres created sequence for primary key values

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speaker_id;

    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    //- @Lob stands for "Large Object". Binary data can get really big and this helps JPA deal with
    //large data.
    // @Type will help Hibernate deal with binary data. Hibernate is the JPA implementation
    //that we are using.
    //- NOTE: A new property needs to be added to the "applications.properties" file in the "resources"
    //folder to get Lob working here.

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] speaker_photo;

    //- @ManytoMany is created to be the other side of the existing many-to-many relationship in
    //the "Session" class.
    //"speakers" is the attribute in the Session class.
    //@JsonIgnore prevents back serialization back to the sessions. (Infinite loop essentially).
    //Jackson will be basically ignore this when it goes to reload the sessions.

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;

    public Speaker()
    {

    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Long speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }
}
