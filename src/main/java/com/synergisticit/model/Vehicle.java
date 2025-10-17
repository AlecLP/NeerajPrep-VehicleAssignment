package com.synergisticit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String make;
	private String model;
	private String licensePlate;
	private Integer mileage;
	private Integer manufactureYear;
	
	public void startEngine() {
		System.out.println("Engine started");
	}
	
	public void stopEngine() {
		System.out.println("Engine stopped");
	}
	
	public abstract void drive();

}
