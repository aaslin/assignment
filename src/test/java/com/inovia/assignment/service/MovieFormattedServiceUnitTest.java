package com.inovia.assignment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MovieFormattedServiceUnitTest {

    @ParameterizedTest
    @CsvSource({"59,0h 59m", "60, 1h 0m", "61,1h 1m"})
    public void givenDurationInMinutes_whenFormatDuration_returnDurationInHoursAndMinutes(int given, String expected) {
        Assertions.assertEquals(expected, new MovieFormattedService().formatDuration(given));
    }
}
