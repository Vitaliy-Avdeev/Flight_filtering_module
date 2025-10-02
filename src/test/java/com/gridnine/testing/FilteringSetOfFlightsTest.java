package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilteringSetOfFlightsTest {
    Filter filterSegment = new FlightFilter();
    Segment testOne = new Segment(LocalDateTime.of(2025, 12, 10, 2, 10),
            LocalDateTime.of(2025, 12, 10, 10, 10));
    Segment testTwo = new Segment(LocalDateTime.of(2025, 11, 8, 5, 30),
            LocalDateTime.of(2025, 12, 8, 6, 0));
    Segment testThree = new Segment(LocalDateTime.of(2025, 5, 10, 5, 30),
            LocalDateTime.of(2025, 5, 10, 10, 0));
    Segment testFour = new Segment(LocalDateTime.of(2025, 9, 10, 5, 30),
            LocalDateTime.of(2025, 9, 9, 1, 0));
    List<Segment> segmentList = List.of(testOne);
    List<Segment> segmentListTwo = new ArrayList<>();
    List<Segment> segmentListThree = List.of(testTwo);
    List<Segment> segmentListFour = new ArrayList<>();
    List<Segment> segmentListFive = new ArrayList<>();
    Flight testFlight = new Flight(segmentList);
    Flight testFlightTwo = new Flight(segmentListTwo);
    Flight testFlightThree = new Flight(segmentListThree);
    Flight testFlightFour = new Flight(segmentListFour);
    Flight testFlightFive = new Flight(segmentListFive);
    List<Flight> expected = new ArrayList<>();
    List<Flight> actual = new ArrayList<>();

    @BeforeEach
    void addSegment() {
        segmentListTwo.add(testThree);
        segmentListFour.add(testFour);
        segmentListFive.add(testThree);
        segmentListFive.add(testTwo);
    }

    @Test
    void DepartureUpToTheCurrentTimeTest() {
        expected.add(testFlight);
        actual.add(testFlight);
        List<Flight> departureUpToTheCurrentTime = filterSegment.DepartureUpToTheCurrentTime(actual);
        assertEquals(departureUpToTheCurrentTime, expected);
    }

    @Test
    void SegmentsWithArrivalDateBeforeDepartureDateTest() {
        expected.add(testFlightTwo);
        actual.add(testFlightFour);
        actual.add(testFlightTwo);
        List<Flight> segmentsWithArrivalDateBeforeDepartureDate = filterSegment.SegmentsWithArrivalDateBeforeDepartureDate(actual);
        assertEquals(segmentsWithArrivalDateBeforeDepartureDate, expected);
    }

    @Test
    void TheTotalTimeOnEarthExceedsTwoHoursTest() {
        expected.add(testFlightThree);
        actual.add(testFlightFive);
        actual.add(testFlightThree);
        List<Flight> theTotalTimeOnEarthExceedsTwoHours = filterSegment.TheTotalTimeOnEarthExceedsTwoHours(actual);
        assertEquals(theTotalTimeOnEarthExceedsTwoHours, expected);
    }
}





