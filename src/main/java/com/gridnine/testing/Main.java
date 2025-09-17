package com.gridnine.testing;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        Filter fil = new FlightFilter();
        System.out.println("Все рейсы:");
        printResultFilterList(flights);
        System.out.println("Вылет до текущего момента времени:");
        printResultFilterList(fil.DepartureUpToTheCurrentTime(flights));
        System.out.println("Сегменты с датой прилёта раньше даты вылета:");
        printResultFilterList(fil.SegmentsWithArrivalDateBeforeDepartureDate(flights));
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа:");
        printResultFilterList(fil.TheTotalTimeOnEarthExceedsTwoHours(flights));
    }

    public static void printResultFilterList(List<Flight> list) {
        list.forEach(System.out::println);
    }
}


