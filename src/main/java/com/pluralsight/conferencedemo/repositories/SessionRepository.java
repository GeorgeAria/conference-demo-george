package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

//The "extend JpaRepository" part makes this a Spring JPA Repository.
//Within JpaRepository, "Session" is the data type, and "Long" is the primary key.
//-This gives us the ability to do "Find, Update, Save, Delete and other operations to be used in
//our "Session" JPA class.

public interface SessionRepository extends JpaRepository<Session, Long>
{

}
