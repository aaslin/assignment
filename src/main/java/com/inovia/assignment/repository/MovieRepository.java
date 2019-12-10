package com.inovia.assignment.repository;

import com.inovia.assignment.entitiy.Movie;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RepositoryRestResource
@RolesAllowed("USER")
public interface MovieRepository extends Repository<Movie, Long> {

    Iterable<Movie> findAll();

    @RolesAllowed("ADMIN")
    Movie save(Movie movie);

    @RolesAllowed("ADMIN")
    void deleteById(Long id);

    Optional<Movie> findById(Long id);

    Iterable<Movie> findByTitleContaining(String pattern);
}
