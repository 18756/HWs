package ru.itmo.wp.model.service;


import java.util.List;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImpl();

    public List<Talk> findAllByUserId(long userId) {
        return talkRepository.findAllByUserId(userId);
    }

    public void sendMessage(long sourceUserId, long targetUserId, String text) {
        talkRepository.sendMessage(sourceUserId, targetUserId, text);
    }
}
