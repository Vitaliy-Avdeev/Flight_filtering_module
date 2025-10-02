package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
public class FlightFilter implements Filter {
    private final LocalDateTime timeNow;
    public FlightFilter() {
        this.timeNow = LocalDateTime.now();
    }

    /**
     * Метод осуществляет фильтрацию списка рейсов в соответствии с условием,
     * исключить рейсы вылет которых до текущего момента времени.
     * Сравнивается время сегментов полета относительно текущей даты
     *
     * @param fls список полетов
     * @return возвращает список {@link List<Flight>}, содержащий отфильтрованный список полетов
     */
    @Override
    public List<Flight> DepartureUpToTheCurrentTime(List<Flight> fls) {
        return fls.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> timeNow.isBefore(segment.getDepartureDate()))).collect(Collectors.toList());//segment.getDepartureDate().isBefore(LocalDateTime.now()))).toList();
    }


    /**
     * Метод осуществляет фильтрацию списка рейсов в соответствии с условием,
     * исключить сегменты с датой прилёта раньше даты вылета.
     *
     * @param fls полный список полетов
     * @return возвращает список {@link List<Flight>}, содержащий отфильтрованный список полетов
     */
    @Override
    public List<Flight> SegmentsWithArrivalDateBeforeDepartureDate(List<Flight> fls) {
        List<Flight> result = new ArrayList<>();
        fls.forEach(flight -> flight.getSegments().stream()
                .filter(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())).limit(1)
                .forEach(segment -> result.add(flight)));
        return result;
    }

    /**
     * Метод осуществляет фильтрацию списка рейсов в соответствии с условием, исключить рейсы где общее время,
     * проведённое на земле, превышает два часа.
     *
     * @param fls полный список полетов
     * @return возвращает список {@link List<Flight>}, содержащий отфильтрованный список полетов
     */
    @Override
    public List<Flight> TheTotalTimeOnEarthExceedsTwoHours(List<Flight> fls) {
        List<Flight> listMoreTwoHours = new ArrayList<>();
        listMoreTwoHours = fls.stream().filter(flight -> flight.getSegments().size() > 1).filter(flight -> {
            long countHours = IntStream.range(1, flight.getSegments().size()).map(i -> Math.toIntExact(checkTimeDifference(flight.getSegments().get(i - 1).getArrivalDate(),
                    flight.getSegments().get(i).getDepartureDate()))).sum();
            return countHours <= 2;
        })
                .collect(Collectors.toList());
        listMoreTwoHours.addAll(fls.stream().filter(flight -> flight.getSegments().size() <= 1).toList());
        return listMoreTwoHours;
    }
    /**
     * Метод для вычисления разницы между двумя промежутками времени(прибытия и отбытия)
     *
     * @param arrival   время прибытия
     * @param departure время отбытия
     * @return возвращает целочисленное значение типа long
     */
    private long checkTimeDifference(LocalDateTime arrival, LocalDateTime departure) {
        return ChronoUnit.HOURS.between(arrival, departure);
    }
}



