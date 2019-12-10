package com.inovia.assignment.controller;

import com.inovia.assignment.service.MovieFormattedService;
import com.inovia.assignment.service.MovieFormattedService.MovieFormattedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping(value = "/moviesformatted", produces = "application/json")
@RolesAllowed("USER")
public class MovieFormattedController {

    @Autowired
    MovieFormattedService movieFormattedService;

    @GetMapping
    public Iterable<MovieFormattedDTO> findAll() {
        return movieFormattedService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieFormattedDTO> findById(@PathVariable Long id) {
        Optional<MovieFormattedDTO> optionalResult = movieFormattedService.findById(id);
        if (optionalResult.isPresent()) {
            MovieFormattedDTO result = optionalResult.get();
            return ResponseEntity.ok(result);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}