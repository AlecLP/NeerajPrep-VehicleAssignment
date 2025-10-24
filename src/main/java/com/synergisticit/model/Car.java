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
public class Car extends Vehicle {
	
	private Integer numDoors;
	
	public Car(String make, String model, String licensePlate, Integer mileage, Integer manufactureYear, Integer numDoors) {
		super(1L, make, model, licensePlate, mileage, manufactureYear);
		this.numDoors = numDoors;
	}

	@Override
	public void drive() {
		System.out.println("Driving car");
	}

}
