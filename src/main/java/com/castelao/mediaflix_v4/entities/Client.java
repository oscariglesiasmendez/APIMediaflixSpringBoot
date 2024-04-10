package com.castelao.mediaflix_v4.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@NotNull
	private String email;

	private String address;

	@NotNull
	private String password;

	private String nif;

	@NotNull
	@Column(name = "creation_date")
	private Date creationDate;

	@NotNull
	private Boolean available;

	public Client(Long clientId, @NotNull String firstName, String lastName, @NotNull String email, String address,
			@NotNull String password, String nif, @NotNull Date creationDate, @NotNull Boolean available) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.password = password;
		this.nif = nif;
		this.creationDate = creationDate;
		this.available = available;
	}

	

}
