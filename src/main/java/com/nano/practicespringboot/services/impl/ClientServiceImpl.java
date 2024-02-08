package com.nano.practicespringboot.services.impl;

import com.nano.practicespringboot.presenters.ClientPresenter;
import com.nano.practicespringboot.entities.ClientModel;
import com.nano.practicespringboot.repositories.ClientRepository;
import com.nano.practicespringboot.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ClientPresenter> getByParameters(String idNumber, String names) {
        return clientRepository.getByParamerters(idNumber, names).stream()
                .map(this::clientModelToPresenter).collect(Collectors.toList());
    }

    private ClientPresenter clientModelToPresenter(ClientModel client) {
        return ClientPresenter.builder().id(client.getId()).idType(client.getIdType()).idNumber(client.getIdNumber())
                .names(client.getNames()).email(client.getEmail()).phoneNumber(client.getPhoneNumber()).build();
    }
}