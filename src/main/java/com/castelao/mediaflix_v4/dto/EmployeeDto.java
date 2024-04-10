package com.castelao.mediaflix_v4.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.castelao.mediaflix_v4.entities.Employee;

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
public class EmployeeDto {

	private Long employeeId;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String nif;

	@NotEmpty
	private String password;

	@NotEmpty
	private String email;

	@NotNull
	private BigDecimal salary;

	@NotNull
	private Date hiringDate;

	@NotNull
	private Boolean available;

	public EmployeeDto(Long employeeId, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String nif,
			@NotEmpty String password, @NotEmpty String email, @NotNull BigDecimal salary, @NotNull Date hiringDate,
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

	public EmployeeDto(@NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String nif,
			@NotEmpty String password, @NotEmpty String email, @NotNull BigDecimal salary, @NotNull Date hiringDate,
			@NotNull Boolean available) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nif = nif;
		this.password = password;
		this.email = email;
		this.salary = salary;
		this.hiringDate = hiringDate;
		this.available = available;
	}

	public EmployeeDto(Employee employee) {
		this.employeeId = employee.getEmployeeId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.nif = employee.getNif();
		this.password = employee.getPassword();
		this.email = employee.getEmail();
		this.salary = employee.getSalary();
		this.hiringDate = employee.getHiringDate();
		this.available = employee.getAvailable();
	}

}
