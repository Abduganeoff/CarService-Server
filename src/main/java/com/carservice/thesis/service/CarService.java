package com.carservice.thesis.service;

import com.carservice.thesis.dto.CarAttributeType;
import com.carservice.thesis.dto.CarChartRequestDto;
import com.carservice.thesis.dto.CarResponseDto;
import com.carservice.thesis.entity.Car;
import com.carservice.thesis.exception.NotFoundException;
import com.carservice.thesis.exception.OperationNotAllowedException;
import com.carservice.thesis.repository.CarRepository;
import com.carservice.thesis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public CarResponseDto createCar(Car car) {
        Car savedCar = carRepository.save(car);
        return convertToDto(savedCar);
    }


    public CarResponseDto getCar(Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found with id: " + id));
        return convertToDto(car);    }

    public List<CarResponseDto> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CarResponseDto> getUnassignedCars() {
        return carRepository.findUnassignedCars().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CarResponseDto updateCar(Integer id, Car carDetails) {
        Car car = getCarEntity(id);
        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setColor(carDetails.getColor());
        car.setLicenceNumber(car.getLicenceNumber());

        Car updatedCar = carRepository.save(car);
        return convertToDto(updatedCar);
    }


    @Transactional
    public void deleteCar(Integer id) {
        Car car = getCarEntity(id);

        boolean isCarInOrder = orderRepository.existsByLicenceNumber(car.getLicenceNumber());
        if (isCarInOrder) {
            throw new OperationNotAllowedException("Car with id " + id + " cannot be deleted as it is associated with an order.");
        }

        car.setClient(null); // Disconnect from client
        carRepository.save(car);
        carRepository.deleteById(id);
    }


    private Car getCarEntity(Integer id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found with id: " + id));
    }

    @Transactional
    public Map<String, Long> getCarChartData(CarChartRequestDto requestDto) {
        List<Car> cars = carRepository.findAll();

        if (requestDto.getType() == CarAttributeType.MODEL) {
            return cars.stream()
                    .collect(Collectors.groupingBy(Car::getModel, Collectors.counting()));
        } else if (requestDto.getType() == CarAttributeType.MAKE) {
            return cars.stream()
                    .collect(Collectors.groupingBy(Car::getMake, Collectors.counting()));
        } else {
            throw new IllegalArgumentException("Invalid attribute type");
        }
    }

    private CarResponseDto convertToDto(Car car) {
        return new CarResponseDto(
                car.getId(),
                car.getModel(),
                car.getMake(),
                car.getLicenceNumber(),
                car.getColor()
        );
    }
}
