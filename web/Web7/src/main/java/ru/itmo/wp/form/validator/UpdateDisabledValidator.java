package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UpdateDisabledForm;
import ru.itmo.wp.service.UserService;

@Component
public class UpdateDisabledValidator implements Validator {
    private final UserService userService;

    public UpdateDisabledValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UpdateDisabledForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UpdateDisabledForm updateDisabledForm = (UpdateDisabledForm) target;
            try {
                long userId = Long.parseLong(updateDisabledForm.getUserId());
                if (userService.findById(userId) == null) {
                    errors.rejectValue("userId", "userId.no-such-user", "no such user");
                }
                if (!updateDisabledForm.getNewValue().equals("0") && !updateDisabledForm.getNewValue().equals("1")) {
                    errors.rejectValue("newValue", "newValue.incorrect-new-value", "incorrect new value");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("userId", "userId.incorrect-user-id", "incorrect user id");
            }
        }
    }
}
