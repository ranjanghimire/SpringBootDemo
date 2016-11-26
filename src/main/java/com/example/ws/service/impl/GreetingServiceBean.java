package com.example.ws.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ws.model.Greeting;
import com.example.ws.repository.GreetingRepository;
import com.example.ws.service.GreetingService;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GreetingServiceBean implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	public GreetingServiceBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = greetingRepository.findAll();
		return greetings;
	}

	@Override
	public Greeting findOne(Long id) {
		Greeting greeting = greetingRepository.findOne(id);
		return greeting;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
			// cannot create Greeting with specified id value
			return null;
		}
		Greeting savedGreeting = greetingRepository.save(greeting);
		return savedGreeting;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Greeting update(Greeting greeting) {
		Greeting greetingPersisted = findOne(greeting.getId());
		if (greetingPersisted == null) {
			// cannot update greeting that hasn't been persisted
			return null;
		}
		Greeting updateGreeting = greetingRepository.save(greeting);
		return updateGreeting;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(Long id) {
		greetingRepository.delete(id);
	}

}
