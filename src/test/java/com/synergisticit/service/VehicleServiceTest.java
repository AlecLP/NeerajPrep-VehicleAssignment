package com.synergisticit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.synergisticit.exception.VehicleNotFoundException;
import com.synergisticit.model.Car;
import com.synergisticit.model.Vehicle;
import com.synergisticit.repository.VehicleRepository;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
	
	@Mock
	private VehicleRepository vehicleRepository;
	
	@InjectMocks
	private VehicleService vehicleService;
	
	private Car car;
	
	@BeforeEach
	void setUp() {
        car = new Car();
        car.setId(1L);
        car.setMake("Toyota");
        car.setModel("Camry");
        car.setLicensePlate("ABC-1234");
        car.setMileage(50000);
        car.setManufactureYear(2015);
        car.setNumDoors(4);
    }
	
	@AfterEach
	void tearDown() {
		car = null;
	}
	
	@Test
	void testSaveVehicle() {
	    when(vehicleRepository.save(car)).thenReturn(car);

	    Vehicle saved = vehicleService.save(car);

	    assertNotNull(saved);
	    assertEquals(1L, saved.getId());
	    assertEquals("Toyota", saved.getMake());
	    verify(vehicleRepository, times(1)).save(car);
	}
	
	@Test
	void testFindAllVehicles() {
	    List<Vehicle> vehicles = List.of(car);
	    when(vehicleRepository.findAll()).thenReturn(vehicles);

	    List<Vehicle> result = vehicleService.findAll();

	    assertEquals(1, result.size());
	    assertEquals("Toyota", result.get(0).getMake());
	    verify(vehicleRepository, times(1)).findAll();
	}
	
	@Test
	void testFindById_Found() throws VehicleNotFoundException {
	    when(vehicleRepository.findById(1L)).thenReturn(Optional.of(car));

	    Vehicle result = vehicleService.findById(1L);

	    assertEquals("Toyota", result.getMake());
	    verify(vehicleRepository, times(1)).findById(1L);
	}
	
	@Test
	void testFindByIdNotFound() {
	    when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

	    assertThrows(VehicleNotFoundException.class, () -> vehicleService.findById(1L));

	    verify(vehicleRepository, times(1)).findById(1L);
	}
	
	@Test
	void testDeleteById() {
	    Long id = 1L;

	    vehicleService.deleteById(id);

	    verify(vehicleRepository, times(1)).deleteById(id);
	}

}
