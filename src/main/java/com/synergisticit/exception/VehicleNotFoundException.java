package com.synergisticit.exception;

public class VehicleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(String message) {
		super(message);
	}
	
	public VehicleNotFoundException() {
	}

}
