package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Type;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;

public class EventService {
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public void save(long userId, Type type) {
        eventRepository.save(userId, type);
    }
}
