package com.application.socialhub.repository;

import com.application.socialhub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value="SELECT * FROM events " +
            "WHERE event_receiver_id = :receiverId " +
            "ORDER BY id DESC " +
            "LIMIT :numberOfEvents", nativeQuery = true)
    List<Event> getLastEvents(@Param("receiverId") Long receiverId,@Param("numberOfEvents") int numberOfEvents);
}
