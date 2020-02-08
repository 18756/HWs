package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.QueryResult;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import static ru.itmo.wp.model.database.DatabaseUtils.selectQuery;
import static ru.itmo.wp.model.database.DatabaseUtils.updateQuery;

public class UserRepositoryImpl extends CommonRepositoryImpl<User> implements UserRepository {


    private User findUserQuery(String query, String... params) {
        QueryResult SelectResult = selectQuery(query, params);
        try {
            return toClassObject(SelectResult.statement.getMetaData(), SelectResult.resultSet);
        } catch (SQLException e) {
            throw new RepositoryException("Can't find User.", e);
        }
    }

    @Override
    public User find(long id) {
        return findUserQuery("SELECT * FROM User WHERE id=?", id + "");
    }

    @Override
    public User findByLogin(String login) {
        return findUserQuery("SELECT * FROM User WHERE login=?", login);
    }

    @Override
    public User findByEmail(String email) {
        return findUserQuery("SELECT * FROM User WHERE email=?", email);
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return findUserQuery("SELECT * FROM User WHERE login=? AND passwordSha=?", login, passwordSha);
    }

    @Override
    public User findByEmailAndPasswordSha(String email, String passwordSha) {
        return findUserQuery("SELECT * FROM User WHERE email=? AND passwordSha=?", email, passwordSha);
    }

    @Override
    public List<User> findAllUsers() {
        return findAll("SELECT * FROM User ORDER BY id DESC");
    }

    @Override
    public int findCount() {
        return findAllUsers().size();
    }

    @Override
    public void save(User user, String passwordSha) {
        updateQuery("INSERT INTO `User` (`login`, `email`, `passwordSha`, `creationTime`) VALUES (?, ?, ?, NOW())", user.getLogin(), user.getEmail(), passwordSha);
    }

    @Override
    public User toClassObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "email":
                    user.setEmail(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }
}




