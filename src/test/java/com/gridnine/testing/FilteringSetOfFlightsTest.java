package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
public class FilteringSetOfFlightsTest {
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now().plusDays(3);
    public static Flight normalFourHourFlight = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(4))
    ));
    public static Flight normalFlightTwoSegments = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(4), CURRENT_DATE.plusHours(6))
    ));
    public static Flight theFlightPrecedesTheCurrentDate_1 = new Flight(List.of(
            new Segment(CURRENT_DATE.minusDays(5), CURRENT_DATE)
    ));
    public static Flight theFlightPrecedesTheCurrentDate_2 = new Flight(List.of(
            new Segment(CURRENT_DATE.minusWeeks(1), CURRENT_DATE)
    ));
    public static Flight arrivalBeforeDeparture_1 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.minusHours(7))
    ));
    public static Flight arrivalBeforeDeparture_2 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(3)),
            new Segment(CURRENT_DATE.plusHours(5), CURRENT_DATE.plusHours(4)),
            new Segment(CURRENT_DATE.plusHours(6), CURRENT_DATE.plusHours(8))
    ));
    public static Flight moreTwoHours_1 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(5), CURRENT_DATE.plusHours(6))
    ));
    public static Flight moreTwoHours_2 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(3), CURRENT_DATE.plusHours(5)),
            new Segment(CURRENT_DATE.plusHours(8), CURRENT_DATE.plusHours(9))
    ));
    public static List<Flight> allFlight = Arrays.asList(normalFourHourFlight, normalFlightTwoSegments, theFlightPrecedesTheCurrentDate_1, theFlightPrecedesTheCurrentDate_2, arrivalBeforeDeparture_1, arrivalBeforeDeparture_2, moreTwoHours_1, moreTwoHours_2);
    public static List<Flight> actualListDepartureThePast = Arrays.asList(theFlightPrecedesTheCurrentDate_1, theFlightPrecedesTheCurrentDate_2);
    public static List<Flight> actualListArrivalBeforeDeparture = Arrays.asList(arrivalBeforeDeparture_1, arrivalBeforeDeparture_2);
    public static List<Flight> actualListMoreTwoHours = Arrays.asList(moreTwoHours_1, moreTwoHours_2);
    Filter filterSegment = new FlightFilter();

    @Test
    void DepartureUpToTheCurrentTimeTest() {
        List<Flight> expected = filterSegment.DepartureUpToTheCurrentTime(allFlight);
        assertEquals(expected, actualListDepartureThePast);
    }

    @Test
    void SegmentsWithArrivalDateBeforeDepartureDateTest() {
        List<Flight> expected = filterSegment.SegmentsWithArrivalDateBeforeDepartureDate(allFlight);
        assertEquals(expected, actualListArrivalBeforeDeparture);
    }

    @Test
    void TheTotalTimeOnEarthExceedsTwoHoursTest() {
        List<Flight> expected = filterSegment.TheTotalTimeOnEarthExceedsTwoHours(allFlight);
        assertEquals(expected, actualListMoreTwoHours);
    }
}





