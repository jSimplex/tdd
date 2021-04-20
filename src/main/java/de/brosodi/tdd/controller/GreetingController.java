package de.brosodi.tdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.brosodi.tdd.data.Greeting;
import de.brosodi.tdd.services.GreetingService;

@RestController
public class GreetingController {

	@Autowired
	private GreetingService greetingService;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("name"+name);
		Greeting result = greetingService.createGreeting(name);
		System.out.println("Result = "+result);
		return result;
	}
}
