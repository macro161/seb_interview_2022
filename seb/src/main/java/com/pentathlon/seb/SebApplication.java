package com.pentathlon.seb;

import com.pentathlon.seb.util.CsvUtil;
import com.pentathlon.seb.util.InMemoryDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SebApplication.class, args);
		InMemoryDatabase.athletes = CsvUtil.readAthletesFromCSV();
		
	}
}
