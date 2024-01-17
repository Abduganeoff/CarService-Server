package com.carservice.thesis.service;

import com.carservice.thesis.entity.Car;
import com.carservice.thesis.exception.NotFoundOrAlreadyExistException;
import com.carservice.thesis.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Car createCar(Car car) {
        return carRepository.save(car);
    }


    public Car getCar(Integer id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundOrAlreadyExistException("Car not found with id: " + id));
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Transactional
    public Car updateCar(Integer id, Car carDetails) {
        Car car = getCar(id);
        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setColor(carDetails.getColor());
        car.setLicenceNumber(car.getLicenceNumber());
        return carRepository.save(car);
    }


    @Transactional
    public void deleteCar(Integer id) {
        Car car = getCar(id);
        car.setClient(null); // Disconnect from client
        carRepository.save(car);
        carRepository.deleteById(id);
    }
}
