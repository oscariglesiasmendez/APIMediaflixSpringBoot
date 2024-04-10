package com.castelao.mediaflix_v4.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.mediaflix_v4.dto.EmployeeDto;
import com.castelao.mediaflix_v4.entities.Employee;
import com.castelao.mediaflix_v4.mapper.EmployeeMapper;
import com.castelao.mediaflix_v4.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

	@Autowired
	private EmployeeService empleadoService;
	
	@Operation(summary = "Get all employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }) })
	@GetMapping
	public List<EmployeeDto> findAll() {
		List<Employee> clientes = empleadoService.getAllEmpleados();
		return EmployeeMapper.toDto(clientes);
	}
	
	
	@Operation(summary = "Create a employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Employee created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/add")
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		EmployeeDto dtoWithId = empleadoService.crearEmpleado(employeeDto);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Update a employee by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Client not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody EmployeeDto employeeDto) {

		Optional<EmployeeDto> employeeDtoUpdated = empleadoService.update(employeeId, employeeDto);
		if (employeeDtoUpdated.isPresent()) {
			return ResponseEntity.ok(employeeDtoUpdated);
		} else {
			return responseNotFound(employeeId);
		}
	}
	
	
	@Operation(summary = "Get all employees with %nif%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class))) }) })
	@GetMapping(value = "/nif")
	public List<EmployeeDto> searchByNif(@RequestParam(name = "nif") String nif) {
		List<EmployeeDto> dtos = empleadoService.searchByNif(nif);
		return dtos;
	}
	
	
	@Operation(summary = "Get all employees with %name%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class))) }) })
	@GetMapping(value = "/name")
	public List<EmployeeDto> searchByName(@RequestParam(name = "name") String name) {
		List<EmployeeDto> dtos = empleadoService.searchByName(name);
		return dtos;
	}
	
	
	@Operation(summary = "Get all employees with %date%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class))) }) })
	@GetMapping(value = "/date")
	public List<EmployeeDto> searchByDate(@RequestParam(name = "date") Date date) {
		List<EmployeeDto> dtos = empleadoService.searchByDate(date);
		return dtos;
	}
	
	
	@Operation(summary = "Get all available employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class))) }) })
	@GetMapping(value = "/available")
	public List<EmployeeDto> searchAvailable() {
		List<EmployeeDto> dtos = empleadoService.searchByVigente();
		return dtos;
	}
	
	
	@Operation(summary = "Get all NOT available employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class))) }) })
	@GetMapping(value = "/notavailable")
	public List<EmployeeDto> searchNotAvailable() {
		List<EmployeeDto> dtos = empleadoService.searchByNoVigente();
		return dtos;
	}
	
	
	@Operation(summary = "Deactivate an employee by its id (change its availability)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee deactivated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
    @PutMapping(value = "/deactivate/{id}")
    public ResponseEntity<?> deactivateEmployee(@PathVariable("id") Long employeeId) {
        boolean deactivated = empleadoService.desactivarEmployee(employeeId);
        if (deactivated) {
            return ResponseEntity.ok().build();
        } else {
            return responseNotFound(employeeId);
        }
    }
	
	
	@Operation(summary = "Activate an employee by its id (change its availability)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee deactivated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<?> reactivateClient(@PathVariable("id") Long employeeId) {
        boolean reactivated = empleadoService.activarEmployee(employeeId);
        if (reactivated) {
            return ResponseEntity.ok().build();
        } else {
            return responseNotFound(employeeId);
        }
    }
	
	
	
	private ResponseEntity<?> responseNotFound(Long employeeId) {
		String errorMessage = "Employee with id '" + employeeId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}
	
	
}
