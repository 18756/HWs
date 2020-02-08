package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import static ru.itmo.wp.model.database.DatabaseUtils.updateQuery;

public class TalkRepositoryImpl extends CommonRepositoryImpl<Talk> implements TalkRepository {
    private final UserRepository userRepository = new UserRepositoryImpl();


    @Override
    public List<Talk> findAllByUserId(long userId) {
        return findAll("SELECT * FROM `Talk` WHERE `sourceUserId`=? OR `targetUserId`=? ORDER BY `creationTime`",
                userId + "", userId + "");
    }

    @Override
    public void sendMessage(long sourceUserId, long targetUserId, String text) {
        updateQuery("INSERT INTO `Talk` (`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES(?, ?, ?, NOW())",
                sourceUserId + "", targetUserId + "", text);
    }

    @Override
    public Talk toClassObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }
        return talk;
    }
}
