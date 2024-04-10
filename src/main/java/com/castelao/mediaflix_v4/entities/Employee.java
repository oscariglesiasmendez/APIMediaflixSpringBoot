package com.castelao.mediaflix_v4.entities;

import java.math.BigDecimal;
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
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	private String nif;

	@NotNull
	private String password;

	@NotNull
	private String email;

	@NotNull
	private BigDecimal salary;

	@NotNull
	@Column(name = "hiring_date")
	private Date hiringDate;

	@NotNull
	private Boolean available;

	public Employee(Long employeeId, @NotNull String firstName, @NotNull String lastName, @NotNull String nif,
			@NotNull String password, @NotNull String email, @NotNull BigDecimal salary, @NotNull Date hiringDate,
			@NotNull Boolean available) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nif = nif;
		this.password = password;
		this.email = email;
		this.salary = salary;
		this.hiringDate = hiringDate;
		this.available = available;
	}

}
