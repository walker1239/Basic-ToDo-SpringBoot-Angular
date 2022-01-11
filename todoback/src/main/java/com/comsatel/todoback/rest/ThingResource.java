package com.comsatel.todoback.rest;

import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import com.comsatel.todoback.models.Thing;
import com.comsatel.todoback.repository.ThingRepository;

@RestController
public class ThingResource {
    @Autowired
    ThingRepository thingRepository;
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(){
        return "Hello World!";
    }

    @RequestMapping(value = "/things", method = RequestMethod.GET)
	public List<Thing> getAll() {
        return thingRepository.findAll();
    }

    @RequestMapping(value = "/things/{id}", method = RequestMethod.GET)
    public ResponseEntity<Thing> get(@PathVariable Long id) {
        Thing thing=thingRepository.findById(id);
        if (Objects.isNull(thing)) 
            return new ResponseEntity<Thing>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Thing>(thing, HttpStatus.OK);
    }

    @RequestMapping(value = "/thing", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Thing thing) throws URISyntaxException {
        thingRepository.insert(thing);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/things/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Thing thing) throws URISyntaxException {
        Thing currentThing = thingRepository.findById(id);
        if (Objects.isNull(thing)) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        currentThing.setDescription(thing.getDescription());
        currentThing.setState(thing.getState());
        thingRepository.update(currentThing);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/things/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Thing thing=thingRepository.findById(id);
        if (Objects.isNull(thing)) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        thingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
