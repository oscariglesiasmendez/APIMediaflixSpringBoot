package com.castelao.mediaflix_v4.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castelao.mediaflix_v4.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	@Query("SELECT g FROM Game g")
	Page<Game> findAllGamesPage(Pageable pageable);

	// Buscar videojuegos por desarrollador
	@Query("SELECT g FROM Game g WHERE g.developer LIKE %:developer%")
	Page<Game> findByDeveloper(@Param("developer") String developer, Pageable pageable);

	// Buscar videojuegos por plataforma
	@Query("SELECT g FROM Game g WHERE g.platform LIKE %:platform%")
	Page<Game> findByPlatform(@Param("platform") String platform, Pageable pageable);

	// Buscar videojuegos con duración en horas mayor a un valor
	@Query("SELECT g FROM Game g WHERE g.duration >= :duration")
	Page<Game> findByDurationGreaterThan(@Param("duration") Integer duration, Pageable pageable);

	// Buscar videojuegos por nombre
	@Query("SELECT g FROM Game g WHERE g.title LIKE %:title%")
	Page<Game> findByTitle(@Param("title") String title, Pageable pageable);

	// Buscar películas por nombre
	@Query("SELECT g FROM Game g WHERE g.title LIKE %:title%")
	List<Game> findByTitleWithoutPagination(@Param("title") String title);

	// Busca si existe ya un videojuego con ese nombre
	@Query("SELECT COUNT(g) > 0 FROM Game g WHERE g.title = :name")
	boolean existsByTitle(@Param("name") String name);

}
