package com.gridnine.testing;

import java.util.List;
@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        Filter fil = new FlightFilter();
        System.out.println("Тестовый набор рейсов:");
        printResultFilterList(flights);
        System.out.println("\n Исключён из тестового набора рейс с вылетом до текущего момента времени:");
        printResultFilterList(fil.DepartureUpToTheCurrentTime(flights));
        System.out.println("\n Исключён из тестового набора рейс с датой прилёта раньше даты вылета:");
        printResultFilterList(fil.SegmentsWithArrivalDateBeforeDepartureDate(flights));
        System.out.println("\n Исключёны из тестового набора рейсы, где общее время, проведённое на земле (интервал между прилётом одного сегмента и вылетом следующего за ним), превышает два часа:");
        printResultFilterList(fil.TheTotalTimeOnEarthExceedsTwoHours(flights));
    }

    public static void printResultFilterList(List<Flight> list) {
        list.forEach(System.out::println);
    }
}
