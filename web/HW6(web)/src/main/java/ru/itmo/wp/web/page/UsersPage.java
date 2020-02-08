package ru.itmo.wp.web.page;

import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class UsersPage extends Page {

    private void findAll() {
        view.put("users", userService.findAll());
    }

    private void findUser() {
        view.put("user", userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void updateAdmin() throws ValidationException {
        shouldLogin();
        if (!getUser().isAdmin()) {
            throw new ValidationException("You should to be admin");
        }
        long userId = getLongParametr(request.getParameter("userId"));
        long newValue = getLongParametr(request.getParameter("newValue"));
        userService.updateAdmin(userId, newValue);
        if (userId == getUser().getId()) {
            getUser().setAdmin(false);
        }
    }
}
