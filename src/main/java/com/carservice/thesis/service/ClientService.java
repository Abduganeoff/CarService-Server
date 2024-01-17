package com.carservice.thesis.service;

import com.carservice.thesis.dto.ClientRequestDto;
import com.carservice.thesis.entity.Car;
import com.carservice.thesis.entity.Client;
import com.carservice.thesis.entity.ColorType;
import com.carservice.thesis.exception.NotFoundOrAlreadyExistException;
import com.carservice.thesis.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client createClientWithCars(ClientRequestDto request) {
        Client client = new Client();
        client.setFirstname(request.getFirstname());
        client.setLastname(request.getLastname());
        client.setEmail(request.getEmail());
        client.setPhoneNumber(request.getPhoneNumber());

        List<Car> cars = request.getCars().stream().map(carDto -> {
            Car car = new Car();
            car.setModel(carDto.getModel());
            car.setMake(carDto.getMake());
            car.setLicenceNumber(carDto.getLicenceNumber());
            car.setColor(carDto.getColorType());
            car.setClient(client); // Set the client
            return car;
        }).collect(Collectors.toList());

        client.setCars(cars);
        return clientRepository.save(client);
    }

    public Client getClient(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundOrAlreadyExistException("Client not found with id: " + id));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
