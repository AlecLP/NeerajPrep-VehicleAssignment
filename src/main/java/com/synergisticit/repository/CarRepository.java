package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
