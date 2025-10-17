package com.synergisticit.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Truck extends Vehicle {
	
	private Integer towingWeightLimit;

	@Override
	public void drive() {
		System.out.println("Driving truck");
	}

}
