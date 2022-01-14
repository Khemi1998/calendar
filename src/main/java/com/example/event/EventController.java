package com.example.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {
    @Autowired
    EventRepository repository;

    ArrayList<Event> events = new ArrayList<>();

    //READ
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents () {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    //READ
    @GetMapping("/events/{searching}")
    public List<Event> getSearchEvent (@PathVariable String searching) {
        List<Event> events = repository.findAll();
        List<Event> search = events.stream()
                .filter(event -> event.getTitle().toLowerCase().contains(searching))
                .collect(Collectors.toList());
        return search;
    }

    //READ
    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body( repository.findEventByid(Integer.parseInt(id)));
    }

    //CREATE
    @PostMapping("/event")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        repository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body("Event added: " + event.getTitle());
    }

    //DELETE
    @DeleteMapping("/event/{id}")
    @Transactional
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        repository.deleteEventByid(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.OK).body("Event with ID:" + id + " has been deleted");
    }
 }
