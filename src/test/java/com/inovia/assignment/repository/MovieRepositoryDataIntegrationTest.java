package com.inovia.assignment.repository;

import com.inovia.assignment.entitiy.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;
import java.util.Optional;

@DataJpaTest
public class MovieRepositoryDataIntegrationTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void givenMovie_whenFindAll_thenReturnMovie() {
        // Given
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDuration(100);
        em.persist(movie);

        // When
        Iterable<Movie> actual = movieRepository.findAll();

        // Then
        Iterable<Movie> expected = Collections.singletonList(movie);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void givenNewMovie_whenSave_thenCreateMovie() {
        // Given
        Movie expected = new Movie();
        expected.setTitle("Test Movie");
        expected.setDuration(100);

        // When
        movieRepository.save(expected);

        // Then
        Movie actual = em.find(Movie.class, expected.getId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenExistingMovie_whenSave_thenUpdateMovie() {
        // Given
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDuration(100);
        em.persist(movie);


        // When
        movieRepository.save(movie);

        // Then
        Movie actual = em.find(Movie.class, movie.getId());
        Assertions.assertEquals(movie, actual);
    }

    @Test
    public void givenMovie_whenDeleteById_thenDeleteMovie() {
        // Given
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDuration(100);
        em.persist(movie);

        // When
        movieRepository.deleteById(movie.getId());

        // Then
        Movie actual = em.find(Movie.class, movie.getId());
        Assertions.assertNull(actual);
    }

    @Test
    public void givenMovie_whenFindById_thenReturnMovie() {
        // Given
        Movie expected = new Movie();
        expected.setTitle("Test Movie");
        expected.setDuration(100);
        em.persist(expected);

        // When
        Optional<Movie> actual = movieRepository.findById(expected.getId());

        // Then
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(expected, actual.get());
    }

    @Test
    public void givenMovie_whenFindByTitleContaining_thenReturnMovie() {
        // Given
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDuration(100);
        em.persist(movie);

        // When
        Iterable<Movie> actual = movieRepository.findByTitleContaining("Test");

        // Then
        Iterable<Movie> expected = Collections.singleton(movie);
        Assertions.assertIterableEquals(expected, actual);

    }
}