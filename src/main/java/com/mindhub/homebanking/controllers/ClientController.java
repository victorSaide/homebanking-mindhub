package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        List<Client> allClients = clientRepository.findAll();

        List<ClientDTO> convertedList = allClients
                .stream()
                .map(client -> new ClientDTO(client))
                .collect(toList());
        return convertedList;
    }


    @GetMapping("/clients/{id}")
    public ClientDTO getClientById (@PathVariable Long id){
        Optional<Client> clientOptional = clientRepository.findById(id);
        return new ClientDTO(clientOptional.get());
    }
}