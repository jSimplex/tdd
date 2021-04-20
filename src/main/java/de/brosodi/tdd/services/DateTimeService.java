package de.brosodi.tdd.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class DateTimeService {

	public LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now();
	}
}
