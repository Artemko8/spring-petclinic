package org.springframework.samples.petclinic.tests.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
	protected RequestSpecification requestSpec;

	public BaseApi() {
		RestAssured.baseURI = "http://localhost:9966/petclinic/api";
		requestSpec = RestAssured.given()
			.contentType("application/json")
			.accept("application/json");
	}
}
