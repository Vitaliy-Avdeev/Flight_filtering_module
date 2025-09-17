package com.gridnine.testing;

import java.util.List;

@SuppressWarnings("ALL")
public interface Filter {

    List<Flight> DepartureUpToTheCurrentTime(List<Flight> fls);

    List<Flight> SegmentsWithArrivalDateBeforeDepartureDate(List<Flight> fls);

    List<Flight> TheTotalTimeOnEarthExceedsTwoHours(List<Flight> fls);
}
