package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {
    private LocalDate terminationInclusive;
    private Long numberOfOccurrences;

    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
         super(title, start, duration, frequency);
            this.terminationInclusive = terminationInclusive;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
            this.numberOfOccurrences = numberOfOccurrences;
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        if(terminationInclusive == null) {
            switch (getFrequency()){
                case DAYS : 
                    this.terminationInclusive = getStart().plusDays(this.numberOfOccurrences).toLocalDate();
                case WEEKS:
                    this.terminationInclusive = getStart().plusWeeks(this.numberOfOccurrences).toLocalDate();
                case MONTHS:
                    this.terminationInclusive = getStart().plusMonths(this.numberOfOccurrences).toLocalDate();
            }
        }
        return terminationInclusive;
    }

    public long getNumberOfOccurrences() {
        long nbJours = (getTerminationDate().getDayOfYear() + getTerminationDate().getYear()*365) - (getStart().getDayOfYear() + getStart().getYear()*365);
        if(numberOfOccurrences == null) {
            switch (getFrequency()){
                case DAYS :
                    numberOfOccurrences = nbJours;
                case WEEKS:
                    numberOfOccurrences = nbJours/7;
                case MONTHS:
                    numberOfOccurrences = nbJours/30;
            }
        }
        return numberOfOccurrences;
    }
        
}
