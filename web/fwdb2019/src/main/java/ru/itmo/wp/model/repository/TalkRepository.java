package ru.itmo.wp.model.repository;

import java.util.List;
import ru.itmo.wp.model.domain.Talk;

public interface TalkRepository {
    public List<Talk> findAllByUserId(long userId);
    public void sendMessage(long sourceUserId, long targetUserId, String text);
}
