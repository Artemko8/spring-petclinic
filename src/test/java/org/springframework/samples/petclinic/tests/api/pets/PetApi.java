package org.springframework.samples.petclinic.tests.api.pets;

import org.springframework.samples.petclinic.tests.api.BaseApi;
import io.restassured.response.Response;

public class PetApi extends BaseApi {

	public Response createPet(int ownerId, Pet pet) {
		return requestSpec.body(pet).post("owners/{ownerId}/pets", ownerId);
	}

	public Response modifyPet(int id, Pet pet) {
		return requestSpec.body(pet).put("pets/{id}", id);
	}

	public Response getPet(int id) {
		return requestSpec.pathParam("id", id).get("pets/{id}");
	}

	public Response deletePet(int id) {
		return requestSpec.delete("pets/{id}", id);
	}

}
