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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ws.model.Greeting;


@RestController
public class GreetingController {

	private static Long nextId;
	private static Map<Long,Greeting> greetingMap;
	
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

}
