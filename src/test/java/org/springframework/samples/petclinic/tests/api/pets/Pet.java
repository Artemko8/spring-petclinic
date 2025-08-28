package org.springframework.samples.petclinic.tests.api.pets;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.samples.petclinic.tests.api.pettypes.PetType;

@Data
@AllArgsConstructor
public class Pet {
	private Integer id;
	private String name;
	private String birthDate;
	private PetType type;      // cat, dog
	private Integer ownerId;
}
