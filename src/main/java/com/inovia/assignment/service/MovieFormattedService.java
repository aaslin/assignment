package com.inovia.assignment.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inovia.assignment.entitiy.Movie;
import com.inovia.assignment.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieFormattedService {

    public static class MovieFormattedDTO {
        final Long id;
        final String title;
        final String duration;

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDuration() {
            return duration;
        }

        @JsonCreator
        public MovieFormattedDTO(
                @JsonProperty("id") Long id,
                @JsonProperty("title") String title,
                @JsonProperty("duration") String duration) {
            this.id = id;
            this.title = title;
            this.duration = duration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MovieFormattedDTO that = (MovieFormattedDTO) o;
            return Objects.equals(id, that.id) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(duration, that.duration);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, duration);
        }
    }

    @Autowired
    MovieRepository movieRepository;

    public Iterable<MovieFormattedDTO> findAll() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .map(this::createMovieFormattedDTO)
                .collect(Collectors.toList());
    }

    public Optional<MovieFormattedDTO> findById(long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.map(this::createMovieFormattedDTO);

    }

    MovieFormattedDTO createMovieFormattedDTO(Movie movie) {
        return  new MovieFormattedDTO(movie.getId(), movie.getTitle(), formatDuration(movie.getDuration()));
    }

    String formatDuration(int duration) {
        int hours = duration / 60;
        int minutes = duration % 60;

        return String.format("%dh %dm", hours, minutes);
    }
}
