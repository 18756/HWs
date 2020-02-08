package ru.itmo.wp.web.page;

import ru.itmo.wp.model.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage extends Page {
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        shouldLogin();
    }

    private void action() {
        view.put("myArticles", articleService.findAllByUserId(getUser().getId()));
    }

    private void changeHidden() throws ValidationException {
        long articleId = getLongParametr(request.getParameter("articleId"));
        String newValue = request.getParameter("newValue");
        articleService.checkRight(articleId, getUser().getId());
        articleService.updateHidden(articleId, newValue);
    }
}
