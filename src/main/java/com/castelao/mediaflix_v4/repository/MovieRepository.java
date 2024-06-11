package com.castelao.mediaflix_v4.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castelao.mediaflix_v4.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT m FROM Movie m")
	Page<Movie> findAllMoviesPage(Pageable pageable);

	// Buscar películas por nombre
	@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	Page<Movie> findByTitle(@Param("title") String title, Pageable pageable);

	// Buscar películas por nombre
	@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	List<Movie> findByTitleWithoutPagination(@Param("title") String title);

	// Buscar películas por director
	@Query("SELECT m FROM Movie m WHERE m.director LIKE %:director%")
	Page<Movie> findByDirector(@Param("director") String director, Pageable pageable);

	// Buscar películas por estudio
	@Query("SELECT m FROM Movie m WHERE m.studio LIKE %:studio%")
	Page<Movie> findByStudio(@Param("studio") String studio, Pageable pageable);

	// Buscar películas con duración en minutos mayor
	@Query("SELECT m FROM Movie m WHERE m.duration >= :duration")
	Page<Movie> findByDuracionGreaterThan(@Param("duration") Integer duration, Pageable pageable);

	// Busca si existe ya una película con ese nombre
	@Query("SELECT COUNT(m) > 0 FROM Movie m WHERE m.title = :title")
	boolean existsByTitle(@Param("title") String title);

}
