package com.synergisticit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergisticit.exception.VehicleNotFoundException;
import com.synergisticit.model.Car;
import com.synergisticit.model.Truck;
import com.synergisticit.model.Vehicle;
import com.synergisticit.service.VehicleService;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {
	
	@Autowired 
	MockMvc mockMvc;
	
	@MockitoBean
	VehicleService vehicleService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	Map<Long, Vehicle> vehicleList = new HashMap<Long, Vehicle>();
	
	@BeforeEach
	void setUp() {
		vehicleList.put(1L, new Truck("Toyota", "Tundra", "ABC-1234", 45000, 2012, 1500));
		vehicleList.put(1L, new Car("Toyota", "Camry", "DEF-4567", 45000, 2012, 4));
	}
	
	@AfterEach
	void tearDown() {
		vehicleList.clear();
	}
	
	@Test
	void saveTruck() throws Exception {
		Vehicle truck = new Truck("Ford", "F-150", "XYZ-7890", 45000, 2012, 4);
		when(vehicleService.save(truck)).thenReturn(truck);
		
		mockMvc.perform(post("/vehicle/truck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(truck)))
            .andExpect(status().isOk());
	}
	
	@Test
	void saveCar() throws Exception {
		Vehicle car = new Car("Ford", "Focus", "XYZ-7890", 45000, 2012, 4);
		when(vehicleService.save(car)).thenReturn(car);
		
		mockMvc.perform(post("/vehicle/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
            .andExpect(status().isOk());
	}
	
	@Test
	void testFindById_Found() throws Exception {
		when(vehicleService.findById(1L)).thenReturn(vehicleList.get(1L));
		
		mockMvc.perform(get("/vehicle/{id}", 1L))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(1));
	}
	
	@Test
	void testFindById_Notfound() throws Exception {
		when(vehicleService.findById(99L)).thenThrow(new VehicleNotFoundException());
		
		mockMvc.perform(get("/vehicle/{id}", 99L))
			.andExpect(status().isNotFound())
			.andExpect(content().string(""));
	}

}
