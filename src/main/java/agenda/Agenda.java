package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    private Set<Event> events = new HashSet<>();

    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> eventsOfTheDay = new ArrayList<>();
        for (Event e : events) {
            if (e.isInDay(day)){
                eventsOfTheDay.add(e);
            }
        }
        return eventsOfTheDay;
    }

    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        List<Event> eventList = new ArrayList<>();
        for (Event e: events) {
            if (e.getTitle().equals(title)) eventList.add(e);
        }
        return eventList;
    }

    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event e) {
        LocalDateTime eventStart = e.getStart();
        LocalDateTime eventFin = eventStart.plusMinutes(e.getDuration().toMinutes());
        for (Event eventInAgenda: events) {
            LocalDateTime eventInAgendaStart = eventInAgenda.getStart();
            LocalDateTime eventInAgendaFin = eventInAgendaStart.plusMinutes(e.getDuration().toMinutes());
            if (eventInAgendaStart.isAfter(eventStart) && eventInAgendaStart.isBefore(eventFin)
                || eventInAgendaFin.isAfter(eventStart) && eventInAgendaFin.isBefore(eventFin)
                || eventInAgendaStart.isBefore(eventStart) && eventInAgendaFin.isAfter(eventFin)) return false;
        }
        return true;
    }
}
