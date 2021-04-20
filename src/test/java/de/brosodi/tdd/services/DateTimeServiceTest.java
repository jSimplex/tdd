package de.brosodi.tdd.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class DateTimeServiceTest {

	private DateTimeService classUnderTest = new DateTimeService();

	@Test
	public void simpleTest() {
		LocalDateTime result = classUnderTest.getCurrentDateTime();
		assertThat(result).isCloseTo(LocalDateTime.now(), within(100, ChronoUnit.MILLIS));
	}

}
