package org.springframework.samples.petclinic.tests.api.pettypes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetType {
	private Integer id;
	private String name;
}
