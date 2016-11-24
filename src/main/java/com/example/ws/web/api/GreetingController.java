/**
 * 
 */
package com.example.ws.web.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ws.model.Greeting;


@RestController
public class GreetingController {

	//Declare id and Map to store greetings
	private static Long nextId;
	private static Map<Long,Greeting> greetingMap;
	
	//This is static method to save greetings
	private static Greeting save(Greeting greeting){
		if (greetingMap == null){
			greetingMap = new HashMap<Long,Greeting>();
			nextId = 1L;
		}
		greeting.setId(nextId);
		nextId += 1;
		greetingMap.put(greeting.getId(), greeting);
		return greeting;
	}
	
	static{
		Greeting g1 = new Greeting();
		g1.setText("Hello World");
		save(g1);
		
		Greeting g2 = new Greeting();
		g2.setText("Namaskar Hajur!");
		save(g2);
	}

	public GreetingController() {
	}

	@RequestMapping(value = "/api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingMap.values();
		return new ResponseEntity<Collection<Greeting>>(greetings,HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id){
		Greeting greeting = greetingMap.get(id);
		if (greeting == null){
			return new ResponseEntity<Greeting> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Greeting> (greeting, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/greetings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting){
		Greeting savedGreeting = save(greeting);
		return new ResponseEntity<Greeting> (savedGreeting, HttpStatus.CREATED);
	}
	
	

}
