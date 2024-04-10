package com.castelao.mediaflix_v4.mapper;

import java.util.ArrayList;
import java.util.List;

import com.castelao.mediaflix_v4.dto.ClientDto;
import com.castelao.mediaflix_v4.entities.Client;

public class ClientMapper {

	public static List<ClientDto> toDto(List<Client> entities) {
        List<ClientDto> dtos = new ArrayList<>();
        for (Client entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static ClientDto toDto(Client entity) {
    	ClientDto dto = new ClientDto(entity.getClientId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getAddress(), entity.getPassword(), entity.getNif(), entity.getCreationDate(), entity.getAvailable());
        return dto;
    }

    public static Client toEntity(ClientDto dto) {
    	Client entity = new Client(dto.getClientId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAddress(), dto.getPassword(), dto.getNif(), dto.getCreationDate(), dto.getAvailable());
        return entity;
    }
	
}
