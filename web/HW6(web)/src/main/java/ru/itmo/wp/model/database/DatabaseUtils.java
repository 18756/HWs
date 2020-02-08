package ru.itmo.wp.model.database;

import org.mariadb.jdbc.MariaDbDataSource;
import ru.itmo.wp.model.exception.RepositoryException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    private static final String STRING_CLASS_NAME = "java.lang.String";
    private static final String INT_CLASS_NAME = "java.lang.Integer";
    private static final String LONG_CLASS_NAME = "java.lang.Long";

    public static DataSource getDataSource() {
        return DataSourceHolder.INSTANCE;
    }

    private static final class DataSourceHolder {
        private static final DataSource INSTANCE;
        private static final Properties properties = new Properties();

        static {
            try {
                properties.load(DataSourceHolder.class.getResourceAsStream("/application.properties"));
            } catch (IOException e) {
                throw new RuntimeException("Can't load /application.properties.", e);
            }

            try {
                MariaDbDataSource instance = new MariaDbDataSource();
                instance.setUrl(properties.getProperty("database.url"));
                instance.setUser(properties.getProperty("database.user"));
                instance.setPassword(properties.getProperty("database.password"));
                INSTANCE = instance;
            } catch (SQLException e) {
                throw new RuntimeException("Can't initialize DataSource.", e);
            }

            try (Connection connection = INSTANCE.getConnection()) {
                if (connection == null) {
                    throw new RuntimeException("Can't create testing connection via DataSource.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Can't create testing connection via DataSource.", e);
            }
        }
    }

    public static QueryResult selectQuery(String query, Object... params) {
        try (Connection connection = getDataSource().getConnection()) {
            PreparedStatement statement = prepareQuery(connection, query, params);
            return new QueryResult(statement, statement.executeQuery());
        } catch (SQLException e) {
            throw new RepositoryException("Can't find User.", e);
        }
    }

    public static void updateQuery(String query, Object... params) {
        try (Connection connection = getDataSource().getConnection()) {
            PreparedStatement statement = prepareQuery(connection, query, params);
            if (statement.executeUpdate() != 1) {
                throw new RepositoryException("Failed update query: " + query);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find User.", e);
        }
    }

    private static PreparedStatement prepareQuery(Connection connection, String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            switch (params[i].getClass().getName()) {
                case STRING_CLASS_NAME: {
                    statement.setString(i + 1, (String) params[i]);
                    break;
                }
                case INT_CLASS_NAME: {
                    statement.setInt(i + 1, (int) params[i]);
                    break;
                }
                case LONG_CLASS_NAME: {
                    statement.setLong(i + 1, (long) params[i]);
                    break;
                }
            }
        }
        return statement;
    }
}
