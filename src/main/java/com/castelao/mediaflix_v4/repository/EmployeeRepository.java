package com.castelao.mediaflix_v4.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.castelao.mediaflix_v4.entities.Employee;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name%")
    List<Employee> findByNombreContaining(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.nif LIKE %:nif%")
    List<Employee> findByNifContaining(@Param("nif") String nif);

    @Query("SELECT e FROM Employee e WHERE e.hiringDate >= :date")
    List<Employee> findByFechaContratacionGreaterThan(@Param("date") Date date);

    @Query("SELECT e FROM Employee e WHERE e.available = true")
    List<Employee> findByVigenteTrue();

    @Query("SELECT e FROM Employee e WHERE e.available = false")
    List<Employee> findByVigenteFalse();

    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.nif = :nif")
    boolean existsByNif(@Param("nif") String nif);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.available = false WHERE e.employeeId = :id")
    void desactivarEmpleado(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.available = true WHERE e.employeeId = :id")
    void activarEmpleado(@Param("id") Long id);
}
