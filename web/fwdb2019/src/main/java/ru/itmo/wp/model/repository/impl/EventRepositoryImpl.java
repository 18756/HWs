package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Type;
import ru.itmo.wp.model.repository.EventRepository;

import static ru.itmo.wp.model.database.DatabaseUtils.updateQuery;

public class EventRepositoryImpl implements EventRepository {
    @Override
    public void save(long userId, Type type) {
        updateQuery("INSERT INTO `Event` (`userId`, `type`,`creationTime`) VALUES (?, ?, NOW())", userId + "", type.name());
    }
}
