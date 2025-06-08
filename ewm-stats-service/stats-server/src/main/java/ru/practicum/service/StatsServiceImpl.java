package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.StatsDtoRequest;
import ru.practicum.StatsDtoResponse;
import ru.practicum.exception.ValidationException;
import ru.practicum.model.Stats;
import ru.practicum.repository.StatsRepository;
import ru.practicum.mapper.StatsMapper;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    @Override
    public StatsDtoRequest createStats(StatsDtoRequest statsDtoRequest) {
        Stats stats = statsRepository.save(StatsMapper.toStats(statsDtoRequest));
        return StatsMapper.toStatsDto(stats);
    }

    @Override
    public List<StatsDtoResponse> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
      validateStartAndEndTime(start, end);
      if (unique) {
          return statsRepository.getUniqueStats(start, end, uris);
      } else {
          return statsRepository.getNotUniqueStats(start, end,uris);
      }
    }

    private void validateStartAndEndTime(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            log.error("Дата начала не может быть позже даты окончания.");
            throw new ValidationException("Дата начала не может быть позже даты окончания.");
        }
    }
}
