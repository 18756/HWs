package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class EnterPage extends Page {
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        shouldLogout();
    }

    private void enter() throws ValidationException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.validateAndFindByLoginAndPassword(login, password);
        setUser(user);
        indexRedirect("Hello, " + user.getLogin());
    }
}
