package com.example.ws.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.ws.model.Greeting;
import com.example.ws.service.GreetingService;

@Service
public class GreetingServiceBean implements GreetingService {

	//Declare id and Map to store greetings
		private static Long nextId;
		private static Map<Long,Greeting> greetingMap;
		
		//This is static method to save greetings
		private static Greeting save(Greeting greeting){
			if (greetingMap == null){
				greetingMap = new HashMap<Long,Greeting>();
				nextId = 1L;
			}
			
			//If update ..
			if (greeting.getId() != null){ 
				Greeting oldGreeting = greetingMap.get(greeting.getId());
				if (oldGreeting == null){
					return null; //if the particular greeing is not present, no update.
				}
				//remove the old greeting and put the new greeting. (update simulation)
				greetingMap.remove(greeting.getId());
				greetingMap.put(greeting.getId(), greeting);
				return greeting;
			}
			
			//If create.. 
			greeting.setId(nextId);
			nextId += 1;
			greetingMap.put(greeting.getId(), greeting);
			return greeting;
		}
		
		//delete helper method
		private static boolean remove(Long id){
			Greeting deletedGreeting = greetingMap.remove(id);
			if (deletedGreeting == null){
				return false;
			}
			return true;
		}
		
		static{
			Greeting g1 = new Greeting();
			g1.setText("Hello World");
			save(g1);
			
			Greeting g2 = new Greeting();
			g2.setText("Namaskar Hajur!");
			save(g2);
		}
	
	public GreetingServiceBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = greetingMap.values();
		return greetings;
	}

	@Override
	public Greeting findOne(Long id) {
		Greeting greeting = greetingMap.get(id);
		return greeting;
	}

	@Override
	public Greeting create(Greeting greeting) {
		Greeting savedGreeting = save(greeting);
		return savedGreeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting updateGreeting = save(greeting);
		return updateGreeting;
	}

	@Override
	public void delete(Long id) {
		remove(id);
	}

}
