package com.castelao.mediaflix_v4.dto;

import java.sql.Date;

import com.castelao.mediaflix_v4.entities.Client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO para nuestras peticiones al Controller
 * 
 * @author
 *
 */
@Data
@NoArgsConstructor
public class ClientDto {

	private Long clientId;

	@NotEmpty
	private String firstName;

	private String lastName;

	@NotEmpty
	private String email;

	@NotNull
	private Date creationDate;

	@NotNull
	private Boolean available;

	public ClientDto(@NotEmpty String firstName, String lastName, @NotEmpty String email, String address,
			@NotEmpty String password, String nif, @NotNull Date creationDate, @NotNull Boolean available) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.creationDate = creationDate;
		this.available = available;
	}

	public ClientDto(Long clienteId, @NotEmpty String firstName, String lastName, @NotEmpty String email,
			@NotNull Date creationDate, @NotNull Boolean available) {
		this.clientId = clienteId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.creationDate = creationDate;
		this.available = available;
	}

	public ClientDto(Client client) {
		this.clientId = client.getClientId();
		this.firstName = client.getFirstName();
		this.lastName = client.getLastName();
		this.email = client.getEmail();
		this.creationDate = client.getCreationDate();
		this.available = client.getAvailable();
	}

}

/*
 * @Data
 * 
 * @NoArgsConstructor public class ClientDto {
 * 
 * private Long clientId;
 * 
 * @NotEmpty private String firstName;
 * 
 * private String lastName;
 * 
 * @NotEmpty private String email;
 * 
 * 
 * private String address;
 * 
 * @NotEmpty private String password;
 * 
 * private String nif;
 * 
 * @NotNull private Date creationDate;
 * 
 * @NotNull private Boolean available;
 * 
 * public ClientDto(@NotEmpty String firstName, String lastName, @NotEmpty
 * String email, String address,
 * 
 * @NotEmpty String password, String nif, @NotNull Date creationDate, @NotNull
 * Boolean available) { this.firstName = firstName; this.lastName = lastName;
 * this.email = email; this.address = address; this.password = password;
 * this.nif = nif; this.creationDate = creationDate; this.available = available;
 * }
 * 
 * public ClientDto(Long clienteId, @NotEmpty String firstName, String
 * lastName, @NotEmpty String email, String address, @NotEmpty String password,
 * String nif, @NotNull Date creationDate,
 * 
 * @NotNull Boolean available) { this.clientId = clienteId; this.firstName =
 * firstName; this.lastName = lastName; this.email = email; this.address =
 * address; this.password = password; this.nif = nif; this.creationDate =
 * creationDate; this.available = available; }
 * 
 * 
 * public ClientDto(Client client) { this.clientId = client.getClientId();
 * this.firstName = client.getFirstName(); this.lastName = client.getLastName();
 * this.email = client.getEmail(); this.address = client.getAddress();
 * this.password = client.getPassword(); this.nif = client.getNif();
 * this.creationDate = client.getCreationDate(); this.available =
 * client.getAvailable(); }
 * 
 * 
 * 
 * 
 * }
 */
