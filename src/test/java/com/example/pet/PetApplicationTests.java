package com.example.pet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
@EntityScan(basePackages = {"com.example.pet.domain"})
@SpringBootTest
class PetApplicationTests {

	@Test
	void contextLoads() {
	}

}
