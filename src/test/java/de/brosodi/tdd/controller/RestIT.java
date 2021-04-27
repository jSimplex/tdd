package de.brosodi.tdd.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.brosodi.tdd.services.DateTimeService2;

public class RestIT {

	
	@Test
	public void testExample() {
	  DateTimeService2 dt2 = new DateTimeService2();
	  
	  LocalDateTime currentDateTime = dt2.getCurrentDateTime();
	}
	
}
