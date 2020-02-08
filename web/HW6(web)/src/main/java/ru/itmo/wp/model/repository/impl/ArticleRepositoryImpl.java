package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.repository.ArticleRepository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import static ru.itmo.wp.model.database.DatabaseUtils.updateQuery;

public class ArticleRepositoryImpl extends CommonRepositoryImpl<Article> implements ArticleRepository {
    @Override
    public void create(long userId, String title, String text) {
        updateQuery("INSERT INTO `Article` (`userId`, `title`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())",
                userId, title, text);
    }

    public List<Article> findAllShow() {
        return findAll("SELECT Article.*, User.login FROM Article INNER JOIN User ON Article.userId=User.id WHERE Article.hidden='show' ORDER BY creationTime DESC");
    }

    @Override
    public List<Article> findAllByUserId(long userId) {
        return findAll("SELECT * FROM Article WHERE userId=? ORDER BY creationTime DESC", userId);
    }

    @Override
    public boolean checkRight(long articleId, long userId) {
        if (findAll("SELECT * FROM Article WHERE id=? AND userId=?", articleId, userId).isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void updateHidden(long articleId, String newValue) {
        updateQuery("UPDATE Article SET hidden=? WHERE id=?", newValue, articleId);
    }


    @Override
    public Article toClassObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Article article = new Article();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    article.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    article.setUserId(resultSet.getLong(i));
                    break;
                case "login":
                    article.setUserLogin(resultSet.getString(i));
                    break;
                case "title":
                    article.setTitle(resultSet.getString(i));
                    break;
                case "text":
                    article.setText(resultSet.getString(i));
                    break;
                case "hidden":
                    article.setHidden(resultSet.getString(i));
                    break;
                case "creationTime":
                    article.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return article;
    }
}
