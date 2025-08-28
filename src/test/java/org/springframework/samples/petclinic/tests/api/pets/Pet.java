package org.springframework.samples.petclinic.tests.api.pets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.samples.petclinic.tests.api.pettypes.PetType;

@Data // создает геттеры, сеттеры, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

	private Integer id;

	private String name;

	private String birthDate;

	private PetType type; // cat, dog

	private Integer ownerId;

}
