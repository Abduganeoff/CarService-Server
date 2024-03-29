package com.carservice.thesis.service;

import com.carservice.thesis.dto.CarRequestDto;
import com.carservice.thesis.dto.CarResponseDto;
import com.carservice.thesis.dto.ClientRequestDto;
import com.carservice.thesis.dto.ClientResponseDto;
import com.carservice.thesis.entity.Car;
import com.carservice.thesis.entity.Client;
import com.carservice.thesis.exception.AlreadyExistsException;
import com.carservice.thesis.exception.NotFoundException;
import com.carservice.thesis.repository.CarRepository;
import com.carservice.thesis.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public ClientResponseDto createClientWithCars(ClientRequestDto request) {
        Client client = new Client();
        client.setFirstname(request.getFirstname());
        client.setLastname(request.getLastname());
        client.setEmail(request.getEmail());
        client.setPhoneNumber(request.getPhoneNumber());

        // Check if the request contains cars
        if (request.getCarIds() != null && !request.getCarIds().isEmpty()) {
            for (Integer carId : request.getCarIds()) {
                Car car = carRepository.findById(carId)
                        .orElseThrow(() -> new NotFoundException("Car not found with id: " + carId));

                // Check if the car is already assigned
                if (car.getClient() != null) {
                    throw new AlreadyExistsException("Car with id " + carId + " is already assigned.");
                }

                car.setClient(client);
                client.getCars().add(car);
            }
        }

        Client savedClient = clientRepository.save(client);
        return convertToDto(savedClient);
    }

    public ClientResponseDto getClient(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        return convertToDto(client);
    }

    public List<ClientResponseDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Car> getCarsByClientId(Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + clientId));

        return client.getCars();
    }

    @Transactional
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    private ClientResponseDto convertToDto(Client client) {
        List<CarResponseDto> carDtos = client.getCars().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ClientResponseDto(
                client.getId(),
                client.getFirstname(),
                client.getLastname(),
                client.getEmail(),
                client.getPhoneNumber(),
                carDtos
        );
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
