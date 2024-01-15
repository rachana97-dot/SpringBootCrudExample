package com.RestApiExample.RestApiExampleProg.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.RestApiExample.RestApiExampleProg.RestApiBean.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class ControllerClass1 {

	
	    private List<Car> cars = new ArrayList<>();

	    // Use a constructor or an initialization method for proper list initialization
	    public ControllerClass1() {
	        // Initialize the cars list in the constructor
	        cars.add(Car.displayCar("MH-4008", "Maruti Swift", "Red", "5.99 Lakh"));
	        cars.add(Car.displayCar("MH-9890", "Tata Nexon", "Blue", "8.10 Lakh"));
	        cars.add(Car.displayCar("UP-3333", "Toyota Fortuner", "Black", "51.44 Lakh"));
	        cars.add(Car.displayCar("MP-0920", "Hyundai Exter", "Green", "10.15 Lakh"));
	    }	    

    
    @GetMapping("/getAll")
    public List<Car> getAllCars() {
        return cars;
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable String carId) {
        Optional<Car> foundCar = findCarById(carId);
        return foundCar.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        car.setCarId(generateCarId()); // Generate or set a unique ID
        cars.add(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/{carId}/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car updatedCar, @PathVariable String carId) {
        Optional<Car> foundCar = findCarById(carId);
        if (foundCar.isPresent()) {
            Car existingCar = foundCar.get();
            existingCar.setCarName(updatedCar.getCarName());
            existingCar.setCarColor(updatedCar.getCarColor());
            existingCar.setCarPrice(updatedCar.getCarPrice());
            return new ResponseEntity<>(existingCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{carId}/delete")
    public ResponseEntity<String> deleteCar(@PathVariable String carId) {
        Optional<Car> foundCar = findCarById(carId);
        if (foundCar.isPresent()) {
            cars.remove(foundCar.get());
            return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }
    }

    private Optional<Car> findCarById(String carId) {
        return cars.stream()
                .filter(car -> car.getCarId().equals(carId))
                .findFirst();
    }

    private String generateCarId() {
        // Logic to generate a unique car ID, you can use UUID or any other mechanism
        // For simplicity, using a simple counter here
        return "CAR" + (cars.size() + 1);
    }
}
