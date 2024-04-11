package com.castelao.mediaflix_v4.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castelao.mediaflix_v4.entities.Client;

import jakarta.transaction.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("SELECT c FROM Client c WHERE c.firstName LIKE %:name%")
    List<Client> findByNombreContaining(@Param("name") String name);

    @Query("SELECT c FROM Client c WHERE c.nif LIKE %:nif%")
    List<Client> findByNifContaining(@Param("nif") String nif);

    @Query("SELECT c FROM Client c WHERE c.creationDate >= :date")
    List<Client> findByFechaAltaGreaterThan(@Param("date") Date date);

    // Busca clientes que estén vigentes
    @Query("SELECT c FROM Client c WHERE c.available = true")
    List<Client> findByVigenteTrue();

    // Busca clientes que estén vigentes
    @Query("SELECT c FROM Client c WHERE c.available = false")
    List<Client> findByVigenteFalse();
    
    @Query("SELECT COUNT(c) > 0 FROM Client c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);

    // Método para desactivar un cliente estableciendo el campo vigente a false
    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.available = false WHERE c.clientId = ?1")
    void desactivarCliente(Long id);

    // Método para activar un cliente estableciendo el campo vigente a true
    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.available = true WHERE c.clientId = ?1")
    void activarCliente(Long id);
    
    @Query("SELECT c FROM Client c ORDER BY c.creationDate DESC")
    List<Client> findLatestClients(Pageable pageable);

}
