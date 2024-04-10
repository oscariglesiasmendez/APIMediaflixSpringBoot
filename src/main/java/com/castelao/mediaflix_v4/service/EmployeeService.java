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
import com.castelao.mediaflix_v4.dto.EmployeeDto;
import com.castelao.mediaflix_v4.entities.Client;
import com.castelao.mediaflix_v4.entities.Employee;
import com.castelao.mediaflix_v4.mapper.ClientMapper;
import com.castelao.mediaflix_v4.mapper.EmployeeMapper;
import com.castelao.mediaflix_v4.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository empleadoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<Employee> getAllEmpleados() {
		return empleadoRepository.findAll();
	}
	
	
	public EmployeeDto crearEmpleado(EmployeeDto empleadoDto) {
    	if (empleadoRepository.existsByNif(empleadoDto.getNif())) {
			LOG.error("Empleado con NIF ya existente: " + empleadoDto.getNif());
			throw new DataIntegrityViolationException("Empleado con NIF ya existente: " + empleadoDto.getNif());
		}
    	
    	Employee empleado = EmployeeMapper.toEntity(empleadoDto);
    	empleado = empleadoRepository.save(empleado);
    	EmployeeDto dtoCreated = EmployeeMapper.toDto(empleado);
        return dtoCreated;
    }
	
	
	/**
	 * Si el id del empleado recibido existe, actualiza el mismo con los campos
	 * recibidos en empleadoDto
	 * 
	 * Devuelve el empleado actualizado
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id              del empleado a buscar
	 * @param empleadoDetails    objeto con todos los campos a sobreescribir en la entidad
	 * @return Optional<EmpleadoDto>
	 */
	public Optional<EmployeeDto> update(Long id, EmployeeDto empleadoDto) {
		Optional<Employee> optionalEmpleado = empleadoRepository.findById(id);
		if (optionalEmpleado.isPresent()) {
			Employee empleado = optionalEmpleado.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto cliente a la entidad
			modelMapper.map(empleadoDto, empleado);

			Employee empleadoSaved = empleadoRepository.save(empleado);
			return Optional.of(EmployeeMapper.toDto(empleadoSaved));
		} else {
			LOG.info("empleado no encontrado: " + id);
			return Optional.empty();
		}
	}
	
	
	public Optional<Employee> getById(Long id) {
		return empleadoRepository.findById(id);
	}
	
	
	
	/**
	 * Busca empleados que tengan un nif similar a lo recibido como argumento
	 * 
	 * @param nif
	 * @return List<EmpleadoDto>
	 */
	public List<EmployeeDto> searchByNif(String nif) {
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		List<Employee> empleados = empleadoRepository.findByNifContaining(nif);

		if (empleados != null) {
			dtos = EmployeeMapper.toDto(empleados);
		}

		return dtos;
	}
	
	
	
	/**
	 * Busca empleados que tengan un nombre similar a lo recibido como argumento
	 * 
	 * @param name
	 * @return List<EmpleadoDto>
	 */
	public List<EmployeeDto> searchByName(String name) {
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		List<Employee> empleados = empleadoRepository.findByNombreContaining(name);

		if (empleados != null) {
			dtos = EmployeeMapper.toDto(empleados);
		}

		return dtos;
	}
	
	
	/**
	 * Busca clientes que tengan una fecha de alta mayor que la proporcionada
	 * 
	 * @param date
	 * @return List<EmpleadoDto>
	 */
	public List<EmployeeDto> searchByDate(Date date) {
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		List<Employee> empleados = empleadoRepository.findByFechaContratacionGreaterThan(date);

		if (empleados != null) {
			dtos = EmployeeMapper.toDto(empleados);
		}

		return dtos;
	}
	
	
	/**
	 * Busca empleados que estén dados de alta actualmente
	 * 
	 * @param date
	 * @return List<EmpleadoDto>
	 */
	public List<EmployeeDto> searchByVigente() {
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		List<Employee> empleados = empleadoRepository.findByVigenteTrue();

		if (empleados != null) {
			dtos = EmployeeMapper.toDto(empleados);
		}

		return dtos;
	}
	
	
	/**
	 * Busca empleados que estén dados de alta actualmente
	 * 
	 * @param date
	 * @return List<EmpleadoDto>
	 */
	public List<EmployeeDto> searchByNoVigente() {
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		List<Employee> empleados = empleadoRepository.findByVigenteFalse();

		if (empleados != null) {
			dtos = EmployeeMapper.toDto(empleados);
		}

		return dtos;
	}
	
	
	/**
	 * Si existe el empleado con el id recibido como argumento cambia el campo vigente a false y devuelve
	 * true
	 * 
	 * Sino existe devuelve falso
	 * 
	 * @param id del empleado a desactivar
	 * @return true / false
	 */
	public boolean desactivarEmployee(Long id) {
		Optional<Employee> optionalCliente = empleadoRepository.findById(id);
		if (optionalCliente.isPresent()) {
			empleadoRepository.desactivarEmpleado(id);
			return true;
		} else {
			LOG.info("empleado no encontrado: " + id);
			return false;
		}
	}
	
	
	/**
	 * Si existe el empleado con el id recibido como argumento cambia el campo vigente a true y devuelve
	 * true
	 * 
	 * Sino existe devuelve falso
	 * 
	 * @param id del empleado a desactivar
	 * @return true / false
	 */
	public boolean activarEmployee(Long id) {
		Optional<Employee> optionalCliente = empleadoRepository.findById(id);
		if (optionalCliente.isPresent()) {
			empleadoRepository.activarEmpleado(id);
			return true;
		} else {
			LOG.info("empleado no encontrado: " + id);
			return false;
		}
	}
	
	
	
}
