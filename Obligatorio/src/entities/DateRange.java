package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;

    public DateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = LocalDate.parse(endDate, formatter);
    }

    public boolean includes(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }

    public boolean contains(LocalDate songDate) {
        return (songDate.isEqual(startDate) || songDate.isAfter(startDate)) &&
                (songDate.isEqual(endDate) || songDate.isBefore(endDate));
    }
}
