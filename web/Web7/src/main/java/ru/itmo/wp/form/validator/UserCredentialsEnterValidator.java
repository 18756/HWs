package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.UserService;
import ru.itmo.wp.domain.User;

@Component
public class UserCredentialsEnterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsEnterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentials enterForm = (UserCredentials) target;
            User user = userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword());
            if (user == null) {
                errors.rejectValue("password", "password.invalid-login-or-password", "invalid login or password");
            } else if (user.isDisabled()) {
                errors.rejectValue("login", "login.you-are-disabled", "you are disabled");
            }
        }
    }
}
