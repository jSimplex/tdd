package de.brosodi.tdd.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileCreationService {

	public static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	@Autowired
	private DateTimeService dateTimeService;

	public void writeStartTimeToFile() throws IOException {
		try (FileWriter myWriter = new FileWriter("dateTimeOfStart.txt");) {
			myWriter.write(dateTimeService.getCurrentDateTime().format(formatter));
		}
	}
}
