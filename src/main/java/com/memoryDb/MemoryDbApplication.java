package com.memoryDb;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MemoryDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoryDbApplication.class, args);
	}

}
