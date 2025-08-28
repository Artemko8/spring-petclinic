package org.springframework.samples.petclinic.tests.api.owners;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.tests.api.BaseApi;
import io.restassured.response.Response;

public class OwnerApi extends BaseApi {

	public Response createOwner(Owner owner) {
		return requestSpec.body(owner).post("/owners");
	}

	public Response getOwner(int id) {
		return requestSpec.get("/owners/{id}", id);
	}

	public Response deleteOwner(int id) {
		return requestSpec.delete("/owners/{id}", id);
	}

}
