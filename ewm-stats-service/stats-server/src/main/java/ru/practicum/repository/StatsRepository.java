package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.StatsDtoResponse;
import ru.practicum.model.Stats;

import java.time.LocalDateTime;
import java.util.List;


public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query("select new ru.practicum.StatsDtoResponse(s.app, s.uri, count(s.ip)) " +
            "from Stats as s " +
            "where s.timestamp between :start and :end " +
            "and ((:uris) is null or s.uri in (:uris)) " +
            "group by s.app, s.uri " +
            "order by count(s.ip) desc")
    List<StatsDtoResponse> getNotUniqueStats(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select new ru.practicum.StatsDtoResponse(s.app, s.uri, count(distinct s.ip)) " +
            "from Stats as s " +
            "where (s.timestamp between :start and :end) " +
            "and ((:uris) is null  or s.uri in (:uris)) " +
            "group by s.app, s.uri " +
            "order by count(distinct s.ip) desc")
    List<StatsDtoResponse> getUniqueStats(LocalDateTime start, LocalDateTime end, List<String> uris);
}
