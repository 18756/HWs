package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class RegisterPage extends Page {
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        shouldLogout();
    }

    private void register() throws ValidationException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        String password = request.getParameter("password");
        userService.validateRegistration(user, password);
        userService.register(user, password);
        indexRedirect("You are successfully registered!");
    }
}
