package com.castelao.mediaflix_v4.mapper;

import java.util.ArrayList;
import java.util.List;

import com.castelao.mediaflix_v4.dto.EmployeeDto;
import com.castelao.mediaflix_v4.entities.Employee;

public class EmployeeMapper {

	public static List<EmployeeDto> toDto(List<Employee> entities) {
        List<EmployeeDto> dtos = new ArrayList<>();
        for (Employee entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static EmployeeDto toDto(Employee entity) {
    	EmployeeDto dto = new EmployeeDto(entity.getEmployeeId(), entity.getFirstName(), entity.getLastName(), entity.getNif(), entity.getPassword(), entity.getEmail(), entity.getSalary(), entity.getHiringDate(), entity.getAvailable());
        return dto;
    }

    public static Employee toEntity(EmployeeDto dto) {
    	Employee entity = new Employee(dto.getEmployeeId(), dto.getFirstName(), dto.getLastName(), dto.getNif(), dto.getPassword(), dto.getEmail(), dto.getSalary(), dto.getHiringDate(), dto.getAvailable());
        return entity;
    }
	
}
