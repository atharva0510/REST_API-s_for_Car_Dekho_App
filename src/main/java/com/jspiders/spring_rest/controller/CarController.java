package com.jspiders.spring_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.spring_rest.pojo.Car;
import com.jspiders.spring_rest.response.ResponseStructure;
import com.jspiders.spring_rest.service.CarService;

@RestController
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@PostMapping(path = "/add_car")
	public ResponseEntity<ResponseStructure<Car>> addCar(@RequestBody Car car) {
		Car addedCar = carService.addCar(car);
		ResponseStructure<Car> responseStructure = new ResponseStructure<Car>();
		responseStructure.setMessage("Car Added Successfully..!!");
		responseStructure.setData(addedCar);
		responseStructure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Car>>(responseStructure, HttpStatus.OK);
	}
	
	@GetMapping(path = "/all_car")
	public ResponseEntity<ResponseStructure<List<Car>>> allCar() {
		List<Car> cars = carService.allCar();
		ResponseStructure<List<Car>> responseStructure = new ResponseStructure<List<Car>>();
		if (cars != null) {
			responseStructure.setMessage("Cars Found");
			responseStructure.setData(cars);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Car>>>(responseStructure, HttpStatus.FOUND);
		} else {
			responseStructure.setMessage("Cars Not Found");
			responseStructure.setData(cars);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Car>>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/delete_car")
	public ResponseEntity<ResponseStructure<Car>> deleteCar(@RequestParam(name = "id") int id) {
		Car carToBeDeleted = carService.deleteCar(id);
		ResponseStructure<Car> responseStructure = new ResponseStructure<Car>();
		if (carToBeDeleted != null) {
			responseStructure.setMessage("Car Deleted Successfully..!!");
			responseStructure.setData(carToBeDeleted);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("Car Not Found");
			responseStructure.setData(carToBeDeleted);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(path = "update_car")
	public ResponseEntity<ResponseStructure<Car>> updateCar(@RequestBody Car car) {
		Car carToBeUpdated = carService.updateCar(car);
		ResponseStructure<Car> responseStructure = new ResponseStructure<Car>();
		if (carToBeUpdated != null) {
			responseStructure.setMessage("Car Updated Successfully..!!");
			responseStructure.setData(carToBeUpdated);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("Car Not Found");
			responseStructure.setData(carToBeUpdated);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

}
