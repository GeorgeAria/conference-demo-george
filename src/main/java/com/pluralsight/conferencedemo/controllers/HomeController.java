package com.pluralsight.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController
{
    //- @Value tells SpringBoot and Spring to look in the properties section of the app, find app.version and
    //put the value in the following variable. In this case, it is the variable appVersion.

    @Value("${app.version}")
    private String appVersion;

    //@RequestMapping will work when some queries the root/home section of your application.
    //In this case, it would be the url, "http://localhost:5000/".
    //Jackson will eventually take the output of getStatus() and marshall that into JSON.

    @GetMapping
    @RequestMapping("/")
    public Map getStatus()
    {
        Map map = new HashMap<String, String>();
        map.put("app-version", appVersion);
        return map;
    }
}
