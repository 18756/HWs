package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Type;

public interface EventRepository {
    void save(long userId, Type type);
}
