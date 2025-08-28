package org.springframework.samples.petclinic.tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.samples.petclinic.tests.api.owners.Owner;
import org.springframework.samples.petclinic.tests.api.pets.Pet;
import org.springframework.samples.petclinic.tests.api.pets.PetApi;
import org.springframework.samples.petclinic.tests.api.pettypes.PetType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class OwnersPetsTest {

	@BeforeClass
	public void setup() {
		// Базовый URL (замени на актуальный, если у тебя локально или в AWS)
		RestAssured.baseURI = System.getProperty("baseUrl");
	}

	@Test
	public void doThingsOnOwnerAndPetTest() {
		Owner owner = new Owner();
		owner.firstName = "Vasia";
		owner.lastName = "Pupenko";
		owner.address = "Paper str.";
		owner.city = "Wilmington";
		owner.telephone = "1234567890";

		Response postResponse = given().contentType(ContentType.JSON)
			.body(owner)
			.when()
			.post("/api/owners")
			.then()
			.statusCode(201) // успешное создание
			.extract()
			.response();

		int ownerId = postResponse.jsonPath().getInt("id");

		PetApi petApi = new PetApi();
		PetType cat = new PetType(1, "cat");
		Pet pet = new Pet(null, "Whiskers", "2023-02-01", cat, ownerId);
		Response postPetResponse = petApi.createPet(ownerId, pet);

		int petId = postPetResponse.jsonPath().getInt("id");

		Response petGetResponse = petApi.getPet(petId);
		String petName = petGetResponse.jsonPath().getString("name");
		String petType = petGetResponse.jsonPath().getString("type.name");
		int petOwnerId = petGetResponse.jsonPath().getInt("ownerId");
		Assert.assertEquals(petName, pet.getName());
		Assert.assertEquals(petType, pet.getType().getName());
		Assert.assertEquals(petOwnerId, pet.getOwnerId());

		pet.setName("Tommy");
		Response petPutResponse = petApi.modifyPet(petId, pet);
		Assert.assertEquals(petPutResponse.getStatusCode(), 204);

		petGetResponse = petApi.getPet(petId);
		petName = petGetResponse.jsonPath().getString("name");
		Assert.assertEquals(petName, pet.getName());

		Response petDeleteResponse = petApi.deletePet(petId);
		Assert.assertEquals(petDeleteResponse.getStatusCode(), 204);

		petGetResponse = petApi.getPet(petId);
		Assert.assertEquals(petGetResponse.getStatusCode(), 404);
	}

}
