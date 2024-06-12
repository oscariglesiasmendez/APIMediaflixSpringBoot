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

import com.castelao.mediaflix_v4.dto.ClientDto;
import com.castelao.mediaflix_v4.dto.ProductDto;
import com.castelao.mediaflix_v4.entities.Client;
import com.castelao.mediaflix_v4.mapper.ClientMapper;
import com.castelao.mediaflix_v4.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

	@Autowired
	private ClientService clienteService;

	
	@Operation(summary = "Get all clients")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class)) }) })
	@GetMapping
	public List<ClientDto> findAll() {
		List<Client> clientes = clienteService.getAllClientes();
		return ClientMapper.toDto(clientes);
	}
	
	
	@Operation(summary = "Get by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class)) }) })
	@GetMapping("/{id}")
	public ClientDto findById(@PathVariable(value = "id") Long clientId) {
		Client clientes = clienteService.getById(clientId);
		return ClientMapper.toDto(clientes);
	}
	
	
	@Operation(summary = "Gets lastest clients")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class)) }) })
	@GetMapping("/latest")
    public ResponseEntity<List<Client>> findLatestClients() {
        List<Client> clients = clienteService.findLatest20Clients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
	
	
	@Operation(summary = "Create a client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Client created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/add")
	public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clienteDto) {
		//System.out.println(clienteDto);
		ClientDto dtoWithId = clienteService.crearCliente(clienteDto);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Update a client by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Client not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long clientId,
			@Valid @RequestBody ClientDto clienteDto) {

		Optional<ClientDto> clientDtoUpdated = clienteService.update(clientId, clienteDto);
		if (clientDtoUpdated.isPresent()) {
			return ResponseEntity.ok(clientDtoUpdated);
		} else {
			return responseNotFound(clientId);
		}
	}

	
	/*
	@Operation(summary = "Get all clients with %nif%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))) }) })
	@GetMapping(value = "/nif")
	public List<ClientDto> searchByNif(@RequestParam(name = "nif") String nif) {
		List<ClientDto> dtos = clienteService.searchByNif(nif);
		return dtos;
	}
	*/
	
	
	@Operation(summary = "Get all clients with %name%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))) }) })
	@GetMapping(value = "/name")
	public List<ClientDto> searchByName(@RequestParam(name = "name") String name) {
		List<ClientDto> dtos = clienteService.searchByName(name);
		return dtos;
	}
	
	
	@Operation(summary = "Get all clients with %date%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))) }) })
	@GetMapping(value = "/date")
	public List<ClientDto> searchByDate(@RequestParam(name = "date") Date date) {
		List<ClientDto> dtos = clienteService.searchByDate(date);
		return dtos;
	}
	
	@Operation(summary = "Get the cliente with this %email%")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Client found", content = {
	                @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class)) }),
	        @ApiResponse(responseCode = "404", description = "Client not found", content = {
	                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@GetMapping(value = "/email")
	public ClientDto searchByEmail(@RequestParam(name = "email") String email) {
		return clienteService.searchByEmail(email);
	}
	
	@GetMapping("/email/{email}")
    public ResponseEntity<?> getClientByEmail(@PathVariable String email) {
        // Aquí deberías implementar la lógica para buscar el cliente por el correo electrónico
        ClientDto client = clienteService.searchByEmail(email);
        
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
	
	
	@Operation(summary = "Get all available clients")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))) }) })
	@GetMapping(value = "/available")
	public List<ClientDto> searchAvailable() {
		List<ClientDto> dtos = clienteService.searchByVigente();
		return dtos;
	}
	
	
	@Operation(summary = "Get all NOT available clients")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clients found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))) }) })
	@GetMapping(value = "/notavailable")
	public List<ClientDto> searchNotAvailable() {
		List<ClientDto> dtos = clienteService.searchByNoVigente();
		return dtos;
	}
	
	
	@Operation(summary = "Deactivate a client by its id (change its availability)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client deactivated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Client not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
    @PutMapping(value = "/deactivate/{id}")
    public ResponseEntity<?> deactivateClient(@PathVariable("id") Long clienteId) {
        boolean deactivated = clienteService.desactivarCliente(clienteId);
        if (deactivated) {
            return ResponseEntity.ok().build();
        } else {
            return responseNotFound(clienteId);
        }
    }

	
	@Operation(summary = "Activate a client by its id (change its availability)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client deactivated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Client not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<?> reactivateClient(@PathVariable("id") Long clienteId) {
        boolean reactivated = clienteService.activarCliente(clienteId);
        if (reactivated) {
            return ResponseEntity.ok().build();
        } else {
            return responseNotFound(clienteId);
        }
    }
	
	
	private ResponseEntity<?> responseNotFound(Long clientId) {
		String errorMessage = "Client with id '" + clientId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}
	
}
