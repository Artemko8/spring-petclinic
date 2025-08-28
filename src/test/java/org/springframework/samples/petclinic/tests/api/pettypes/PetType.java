package org.springframework.samples.petclinic.tests.api.pettypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetType {

	private Integer id;

	private String name;

}
