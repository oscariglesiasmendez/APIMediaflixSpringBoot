package com.castelao.mediaflix_v4.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.castelao.mediaflix_v4.dto.ClientDto;
import com.castelao.mediaflix_v4.entities.Client;
import com.castelao.mediaflix_v4.mapper.ClientMapper;
import com.castelao.mediaflix_v4.repository.ClientRepository;

@Service
public class ClientService {

	private static Logger LOG = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	private ClientRepository clientRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<Client> getAllClientes() {
		return clientRepository.findAll();
	}

	public ClientDto crearCliente(ClientDto clienteDto) {
		if (clientRepository.existsByEmail(clienteDto.getEmail())) {
			LOG.error("Cliente con email ya existente: " + clienteDto.getEmail());
			throw new DataIntegrityViolationException("Cliente con email ya existente: " + clienteDto.getEmail());
		}

		Client cliente = ClientMapper.toEntity(clienteDto);
		cliente = clientRepository.save(cliente);
		ClientDto dtoCreated = ClientMapper.toDto(cliente);
		return dtoCreated;
	}

	/**
	 * Si el id del cliente recibido existe, actualiza el mismo con los campos
	 * recibidos en clienteDto
	 * 
	 * Devuelve el cliente actualizado
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id             del cliente a buscar
	 * @param clienteDetails objeto con todos los campos a sobreescribir en la
	 *                       entidad
	 * @return Optional<ClienteDto>
	 */
	public Optional<ClientDto> update(Long id, ClientDto clienteDto) {
		Optional<Client> optionalCliente = clientRepository.findById(id);
		if (optionalCliente.isPresent()) {
			Client cliente = optionalCliente.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto cliente a la entidad
			modelMapper.map(clienteDto, cliente);

			Client clienteSaved = clientRepository.save(cliente);
			return Optional.of(ClientMapper.toDto(clienteSaved));
		} else {
			LOG.info("cliente no encontrado: " + id);
			return Optional.empty();
		}
	}

	public Client getById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            LOG.info("Cliente no encontrado: " + id);
            return null;
        }
    }

	/**
	 * Busca clientes que tengan un nif similar a lo recibido como argumento
	 * 
	 * @param nif
	 * @return List<ClienteDto>
	 */
	public List<ClientDto> searchByNif(String nif) {
		List<ClientDto> dtos = new ArrayList<ClientDto>();
		List<Client> clientes = clientRepository.findByNifContaining(nif);

		if (clientes != null) {
			dtos = ClientMapper.toDto(clientes);
		}

		return dtos;
	}

	/**
	 * Busca clientes que tengan un nombre similar a lo recibido como argumento
	 * 
	 * @param name
	 * @return List<ClienteDto>
	 */
	public List<ClientDto> searchByName(String name) {
		List<ClientDto> dtos = new ArrayList<ClientDto>();
		List<Client> clientes = clientRepository.findByNombreContaining(name);

		if (clientes != null) {
			dtos = ClientMapper.toDto(clientes);
		}

		return dtos;
	}

	/**
	 * Busca clientes que tengan una fecha de alta mayor que la proporcionada
	 * 
	 * @param date
	 * @return List<ClienteDto>
	 */
	public List<ClientDto> searchByDate(Date date) {
		List<ClientDto> dtos = new ArrayList<ClientDto>();
		List<Client> clientes = clientRepository.findByFechaAltaGreaterThan(date);

		if (clientes != null) {
			dtos = ClientMapper.toDto(clientes);
		}

		return dtos;
	}

	/**
	 * Busca clientes que estén dados de alta actualmente
	 * 
	 * @param
	 * @return List<ClienteDto>
	 */
	public List<ClientDto> searchByVigente() {
		List<ClientDto> dtos = new ArrayList<ClientDto>();
		List<Client> clientes = clientRepository.findByVigenteTrue();

		if (clientes != null) {
			dtos = ClientMapper.toDto(clientes);
		}

		return dtos;
	}

	/**
	 * Busca clientes que NO estén dados de alta actualmente
	 * 
	 * @param
	 * @return List<ClienteDto>
	 */
	public List<ClientDto> searchByNoVigente() {
		List<ClientDto> dtos = new ArrayList<ClientDto>();
		List<Client> clientes = clientRepository.findByVigenteFalse();

		if (clientes != null) {
			dtos = ClientMapper.toDto(clientes);
		}

		return dtos;
	}

	/**
	 * Si existe el cliente con el id recibido como argumento cambia el campo
	 * vigente a false y devuelve true
	 * 
	 * Sino existe devuelve falso
	 * 
	 * @param id del cliente a desactivar
	 * @return true / false
	 */
	public boolean desactivarCliente(Long id) {
		Optional<Client> optionalCliente = clientRepository.findById(id);
		if (optionalCliente.isPresent()) {
			clientRepository.desactivarCliente(id);
			return true;
		} else {
			LOG.info("cliente no encontrado: " + id);
			return false;
		}
	}

	/**
	 * Si existe el cliente con el id recibido como argumento cambia el campo
	 * vigente a true y devuelve true
	 * 
	 * Sino existe devuelve falso
	 * 
	 * @param id del cliente a desactivar
	 * @return true / false
	 */
	public boolean activarCliente(Long id) {
		Optional<Client> optionalCliente = clientRepository.findById(id);
		if (optionalCliente.isPresent()) {
			clientRepository.activarCliente(id);
			return true;
		} else {
			LOG.info("cliente no encontrado: " + id);
			return false;
		}
	}

}
