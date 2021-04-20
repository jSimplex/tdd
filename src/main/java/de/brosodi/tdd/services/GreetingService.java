package de.brosodi.tdd.services;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import de.brosodi.tdd.data.Greeting;
import de.brosodi.tdd.exceptions.NotAcceptableException;

@Service
public class GreetingService {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	
	public Greeting createGreeting(String name) {
		System.err.println(name);
		if (name.equals("TRUMP")) {
			throw new NotAcceptableException();
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}


}
