package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.exception.VehicleNotFoundException;
import com.synergisticit.model.Vehicle;
import com.synergisticit.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {
	
	private final VehicleRepository vehicleRepository;
	
	public Vehicle save(Vehicle v) {
		Vehicle saved = vehicleRepository.save(v);
		log.info("Saved vehicle with id=" +saved.getId());
		return saved;
	}
	
	public List<Vehicle> findAll(){
		return vehicleRepository.findAll();
	}
	
	public Vehicle findById(Long id) throws VehicleNotFoundException {
		return vehicleRepository.findById(id).orElseThrow(() -> {
			return new VehicleNotFoundException("Vehicle with id=" +id +" does not exist");
		});
	}
	
	public void deleteById(Long id) {
		log.info("Deleting vehicle with id=" +id);
		vehicleRepository.deleteById(id);
	}

}
