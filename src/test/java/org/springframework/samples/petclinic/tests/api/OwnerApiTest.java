package org.springframework.samples.petclinic.tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OwnerApiTest {

	@BeforeClass
	public void setup() {
		// Базовый URL (замени на актуальный, если у тебя локально или в AWS)
		RestAssured.baseURI = System.getProperty("baseUrl");
	}

	@Test
	public void createAndGetOwner() {
		// 1. Создаём нового владельца
		String newOwner = """
				{
				  "firstName": "John",
				  "lastName": "Doe",
				  "address": "123 Main St",
				  "city": "Springfield",
				  "telephone": "1234567890"
				}
				""";

		Response postResponse = given().contentType(ContentType.JSON)
			.body(newOwner)
			.when()
			.post("/api/owners")
			.then()
			.statusCode(201) // успешное создание
			.extract()
			.response();

		int ownerId = postResponse.jsonPath().getInt("id");

		// 2. Получаем владельца по ID
		given().when()
			.get("/api/owners/{id}", ownerId)
			.then()
			.statusCode(200)
			.body("firstName", equalTo("John"))
			.body("lastName", equalTo("Doe"))
			.body("city", equalTo("Springfield"));
	}

}
