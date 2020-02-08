package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.AddNoticeForm;

@Component
public class NoticeCreateValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return AddNoticeForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
    }
}
