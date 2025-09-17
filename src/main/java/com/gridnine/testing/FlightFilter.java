package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
public class FlightFilter implements Filter {

    /**
     * Метод возвращает список полетов до текущего момента времени.
     * Сравнивается время сегментов полета относительно текущей даты
     *
     * @param fls список полетов
     * @return возвращает список {@link List<Flight>}, содержащий отфильтрованный список полетов
     */
    @Override
    public List<Flight> DepartureUpToTheCurrentTime(List<Flight> fls) {

        return fls.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))).toList();
    }

    /**
     * Метод возвращает список полетов с сегментами датой прилёта раньше даты вылета
     *
     * @param fls полный список полетов
     * @return возвращает список {@link List<Flight>}, содержащий отфильтрованный список полетов
     */
    @Override
    public List<Flight> SegmentsWithArrivalDateBeforeDepartureDate(List<Flight> fls) {
        return fls.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    /**
     * Метод осуществляет фильтрацию списка рейсов в соответствии с условием, что общее время, проведённое на земле,
     * превышает два часа.
     *
     * @param fls полный список полетов
     * @return возвращает список {@link List<Flight>}, содержащий отфильтрованный список полетов
     */
    @Override
    public List<Flight> TheTotalTimeOnEarthExceedsTwoHours(List<Flight> fls) {
        List<Flight> listMoreTwoHours = new ArrayList<>();
        for (Flight flight : fls) {
            List<Segment> segments = flight.getSegments();
            IntStream.range(0, segments.size() - 1).forEach(i -> {
                LocalDateTime depTime = segments.get(i + 1).getDepartureDate();
                LocalDateTime arrTime = segments.get(i).getArrivalDate();
                if (depTime.isAfter(arrTime.plusHours(2))) {
                    listMoreTwoHours.add(flight);
                }
            });
        }
        return listMoreTwoHours;
    }
}


