package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is a CRUD controller.
//@RestController means that this will respond to payloads incoming and outgoing as JSON REST endpoints.
//- @RequestMapping tells the router what the mapping url will look like. All requests to the url specified
//below will be sent to this controller.
@RestController
@RequestMapping("/api/v1/sessions")

public class SessionsController
{
    //@Autowired uses Spring to inject/auto wire "SessionRepository" when this controller is built.

    @Autowired
    private SessionRepository sessionRepository;

    //The list() method is a list/REST/API endpoint. It will return all of the sessions when called.
    //@GetMapping tells which HTTP verb to use (in this case the GET verb) to call this endpoint.
    //- The sessionRepository.findAll() method will query all of the sessions in the database and return them as a
    //list of Session objects.
    //- Spring will pass on this list of Session objects to Jackson (a serialization library), which will turn those
    //sessions into JSON and return them to the caller.

    @GetMapping
    public List<Session> list()
    {
        return sessionRepository.findAll();
    }

    //@RequestMapping is in addition to the class's version of @RequestMapping at the very top.
    //This @RequestMapping adds an additional id to the URL.
    //This id specifies a specific session, which we will return.
    //The parameter in the get() method is pulling that id out of the URL and injecting it into the method.
    //Spring MVC helps us with the @PathVariable.
    //The id is Long since it is long for our primary key.
    //- sessionRepository has a method getOne() where you can pass an id, which will return and query the session for
    //that id back to the caller.
    //In the end, it will be returned to the caller in JSON payload.

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id)
    {
        return sessionRepository.getOne(id);
    }


    //NOTE: REST controllers in Spring MVC will return 200s as the response status for all calls.
    //This is notable since typically when you create or post something, you get a 201 back.
    //- The @ResponseStatus annotation allows you to specify the response you want to occur when the method executes
    //and finishes.



    //The create() method will create a new session, after you pass info to the API endpoint, to the database.
    //@PostMapping will make the HTTP verb POST to be presented with this API call.
    //@RequestMapping is not required since we are pointing to the base part of the class, which is the
    //"api/v1/sessions" endpoint using the POST verb.
    //- Within the create() method, @RequestBody is taking in all of the attributes in a JSON payload and
    //marshalling them into a Session object.
    //Once this is done, it is passed to the sessionRepository, and will be saved and flushed.
    //Flush basically commits the changes you want to make to the database. Save is not enough.

    @PostMapping
    public Session create(@RequestBody final Session session)
    {
        return sessionRepository.saveAndFlush(session);
    }

    //@RequestMapping specifies that you need to pass in an id, but the method we are requesting is the HTTP method DELETE.
    //@RequestMapping is needed for DELETE since an @DeleteMapping does not exist.
    //@PathVariable takes the id value from the URL, like the get() method above.
    //The id will be used to delete a Session using an id.

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id)
    {
        //Also need to check for children records before deleting.
        sessionRepository.deleteById(id);
    }

    //@RequestMapping requires the id in the URL, and the method will be an HTTP PUT.
    //PUT and PATCH seem similar, but PUT is used for changing all the attributes and PATCH is for some attributes, not all.

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session)
    {
        //Since this is a PUT, we expect all attributes to be passed in.
        //To update a record, we must get the existing one. It is requested through the sessionRepository.
        //A BeanUtils object is then used to take the existing session and copies the incoming session data onto it.
        //-The third parameter of the copyProperties() method makes it ignore the properties on the entities or Java objects
        //that we do not want to copy from one to another. The "session_id" is ignored since it is the primary key.
        //Once this is done, it is passed to the sessionRepository, and will be saved and flushed.
        //Flush basically commits the changes you want to make to the database. Save is not enough.

        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
