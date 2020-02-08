package ru.itmo.wp.web.page;

import ru.itmo.wp.model.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage extends Page {
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        shouldLogin();
    }

    private void create() throws ValidationException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        articleService.create(getUser().getId(), title, text);
        indexRedirect("Your article are successfully created");
    }
}
