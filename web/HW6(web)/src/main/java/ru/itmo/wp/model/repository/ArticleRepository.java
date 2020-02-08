package ru.itmo.wp.model.repository;

import java.util.List;
import ru.itmo.wp.model.domain.Article;

public interface ArticleRepository {
    void create(long userId, String title, String text);
    List<Article> findAllShow();
    List<Article> findAllByUserId(long userId);
    boolean checkRight(long articleId, long userId);
    void updateHidden(long articleId, String newValue);
}
