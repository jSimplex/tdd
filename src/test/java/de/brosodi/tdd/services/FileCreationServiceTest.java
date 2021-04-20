package de.brosodi.tdd.services;


import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FileCreationServiceTest {

	@InjectMocks
	private FileCreationService classUnderTest;

	@Mock
	private DateTimeService dateTimeServiceMock;
	
	@Test
	public void testCreationService() throws IOException {
		LocalDateTime inputValue = LocalDateTime.now();
		when(dateTimeServiceMock.getCurrentDateTime()).thenReturn(inputValue);
		classUnderTest.writeStartTimeToFile();
		try (FileReader fileReader = new FileReader("dateTimeOfStart.txt");) {
			BufferedReader br=new BufferedReader(fileReader); 
			LocalDateTime result  =  LocalDateTime.parse(br.readLine(), FileCreationService.formatter);
			assertThat(result).isEqualTo(inputValue);
		}
	}
}
