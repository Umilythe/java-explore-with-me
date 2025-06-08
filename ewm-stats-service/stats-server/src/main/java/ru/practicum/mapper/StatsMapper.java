package ru.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.StatsDtoRequest;
import ru.practicum.model.Stats;

@Component
public class StatsMapper {
    public static StatsDtoRequest toStatsDto(Stats stats) {
        return StatsDtoRequest.builder()
                .app(stats.getApp())
                .uri(stats.getUri())
                .ip(stats.getIp())
                .timestamp(stats.getTimestamp())
                .build();
    }

    public static Stats toStats(StatsDtoRequest statsDtoRequest) {
        return Stats.builder()
                .app(statsDtoRequest.getApp())
                .uri(statsDtoRequest.getUri())
                .ip(statsDtoRequest.getIp())
                .timestamp(statsDtoRequest.getTimestamp())
                .build();
    }
}
