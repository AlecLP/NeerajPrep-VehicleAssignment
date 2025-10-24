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
	
	public Truck(String make, String model, String licensePlate, Integer mileage, Integer manufactureYear, Integer towingWeightLimit) {
		super(1L, make, model, licensePlate, mileage, manufactureYear);
		this.towingWeightLimit = towingWeightLimit;
	}

	@Override
	public void drive() {
		System.out.println("Driving truck");
	}

}
