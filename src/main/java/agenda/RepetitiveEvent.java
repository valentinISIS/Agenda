package agenda;

import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    private ChronoUnit frequency;
    private Set<LocalDate> exceptions = new HashSet<>();

    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        exceptions.add(date);
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency;
    }

    @Override
    public boolean isInDay(LocalDate aDay) {
        if (super.isInDay(aDay)) return true;
        if (aDay.isBefore((ChronoLocalDate) this.getStart().toLocalDate())) return false;
        if (exceptions.contains(aDay)) return false;
        switch (frequency){
            case DAYS:
                return true;
            case WEEKS:
                if (aDay.getDayOfWeek().equals(getStart().getDayOfWeek())) return true;
            case MONTHS:
                if (aDay.getDayOfMonth() == getStart().getDayOfMonth()) return true;
        }
        return false;
    }
}
