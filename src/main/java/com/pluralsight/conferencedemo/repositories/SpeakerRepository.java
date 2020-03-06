package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

//The "extend JpaRepository" part makes this a Spring JPA Repository.
//Within JpaRepository, "Speaker" is the data type, and "Long" is the primary key.
//-This gives us the ability to do "Find, Update, Save, Delete and other operations to be used in
//our "Speaker" JPA class.

public interface SpeakerRepository extends JpaRepository<Speaker, Long>
{

}
