package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    final UserService userService = new UserService();
    final ArticleService articleService = new ArticleService();

    HttpServletRequest request;
    Map<String, Object> view;
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
        this.view = view;
        User user = getUser();
        if (user != null) {
            view.put("user", user);
            if (user.isAdmin()) {
                view.put("admin", true);
            }
        }
    }

    private void action() {

    }

    protected void after() {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void indexRedirect(String message) {
        setMessage(message);
        throw new RedirectException("/index");
    }

    protected void shouldLogin() {
        if (getUser() == null) {
            indexRedirect("You should login");
        }
    }

    protected void shouldLogout() {
        if (getUser() != null) {
            indexRedirect("You should logout");
        }
    }


    protected void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    protected User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    protected void setUser(User user) {
        request.getSession().setAttribute("user", user);
    }

    protected long getLongParametr(String value) throws ValidationException {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new ValidationException("Wrong parameter");
        }
    }
}
