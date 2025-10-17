package com.synergisticit.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.model.Car;
import com.synergisticit.model.Truck;
import com.synergisticit.model.Vehicle;
import com.synergisticit.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController {
	
	private final VehicleService vehicleService;
	
	@PostMapping("/truck")
	public ResponseEntity<Vehicle> saveTruck(@RequestBody Truck truck){
		return ResponseEntity.ok(vehicleService.save(truck));
	}
	
	@PostMapping("/car")
	public ResponseEntity<Vehicle> saveCar(@RequestBody Car car){
		return ResponseEntity.ok(vehicleService.save(car));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable Long id){
		return ResponseEntity.ok(vehicleService.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Vehicle>> findAll(){
		return ResponseEntity.ok(vehicleService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		vehicleService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
