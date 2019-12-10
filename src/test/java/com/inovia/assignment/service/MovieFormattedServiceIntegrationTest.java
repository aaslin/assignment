package com.inovia.assignment.service;

import com.inovia.assignment.entitiy.Movie;
import com.inovia.assignment.repository.MovieRepository;
import com.inovia.assignment.service.MovieFormattedService.MovieFormattedDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class MovieFormattedServiceIntegrationTest {

    @TestConfiguration
    static class MovieFormattedServiceTestContextConfiguration {

        @Bean
        public MovieFormattedService movieFormattedService() {
            return new MovieFormattedService();
        }
    }

    @Autowired
    MovieFormattedService movieFormattedService;

    @MockBean
    MovieRepository movieRepository;

    @Test
    public void givenMovie_whenFindAll_thenReturnMovieWithDurationInHoursAndMinutes() {
        // Given
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("The Lord of the Rings: The Fellowship of the Ring");
        movie.setDuration(178);
        Mockito.when(movieRepository.findAll()).thenReturn(Collections.singletonList(movie));

        // When
        Iterable<MovieFormattedDTO> actual = movieFormattedService.findAll();

        // Then
        Iterable<MovieFormattedDTO> expected = Collections.singleton(
                new MovieFormattedDTO(movie.getId(), movie.getTitle(), "2h 58m")
        );
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void givenMovie_whenFindById_thenReturnMovieWithDurationInHoursAndMinutes() {
        // Given
        Movie movie = new Movie();
        movie.setTitle("The Lord of the Rings: The Fellowship of the Ring");
        movie.setDuration(178);
        Mockito.when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        // When
        Optional<MovieFormattedDTO> result = movieFormattedService.findById(movie.getId());

        // Then
        MovieFormattedDTO expected = new MovieFormattedDTO(movie.getId(), movie.getTitle(), "2h 58m");
        Assertions.assertTrue(result.isPresent());
        MovieFormattedDTO actual = result.get();
        Assertions.assertEquals(expected, actual);
    }
}
