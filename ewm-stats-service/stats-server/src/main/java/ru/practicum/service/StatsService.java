package ru.practicum.service;

import ru.practicum.StatsDtoRequest;
import ru.practicum.StatsDtoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    StatsDtoRequest createStats(StatsDtoRequest statsDtoRequest);

    List<StatsDtoResponse> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
