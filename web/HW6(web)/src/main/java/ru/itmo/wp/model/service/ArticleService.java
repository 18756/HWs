package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import java.util.List;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

public class ArticleService {
    private static final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (Strings.isNullOrEmpty(title)) {
            throw new ValidationException("Empty title");
        }
        if (Strings.isNullOrEmpty(text)) {
            throw new ValidationException("Empty text");
        }
    }

    public void checkRight(long articleId, long userId) throws ValidationException {
        if (!articleRepository.checkRight(articleId, userId)) {
            throw new ValidationException("It's not your article or article isn't exist");
        }
    }

    public void updateHidden(long articleId, String newValue) {
        articleRepository.updateHidden(articleId, newValue);
    }

    public void create(long userId, String title, String text) throws ValidationException {
        validateArticle(title, text);
        articleRepository.create(userId, title, text);
    }

    public List<Article> findAllShow() {
        return articleRepository.findAllShow();
    }

    public List<Article> findAllByUserId(long userId) {
        return articleRepository.findAllByUserId(userId);
    }
}
