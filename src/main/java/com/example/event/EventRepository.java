package com.example.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    //<ReturnType> [operation]By[attribute](parameter)
    //FIND
    Event findEventByid(int id);

    //DELETE
    String  deleteEventByid(int id);
}
