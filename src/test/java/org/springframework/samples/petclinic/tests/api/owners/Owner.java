package org.springframework.samples.petclinic.tests.api.owners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

	public Integer id;

	public String firstName;

	public String lastName;

	public String address;

	public String city;

	public String telephone;

	// конструкторы, геттеры/сеттеры, toString()

}
