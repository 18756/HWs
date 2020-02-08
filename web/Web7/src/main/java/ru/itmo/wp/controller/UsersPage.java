package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.UpdateDisabledForm;
import ru.itmo.wp.form.validator.UpdateDisabledValidator;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;
    private final UpdateDisabledValidator updateDisabledValidator;

    public UsersPage(UserService userService, UpdateDisabledValidator updateDisabledValidator) {
        this.userService = userService;
        this.updateDisabledValidator = updateDisabledValidator;
    }

    @InitBinder("updateDisabledForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(updateDisabledValidator);
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("updateDisabledForm", new UpdateDisabledForm());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String updateDisabled(@Valid @ModelAttribute("updateDisabledForm") UpdateDisabledForm updateDisabledForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (getUser() == null) {
            putMessage("You should login");
        }
        else if (!bindingResult.hasErrors()) {
            long userId = Long.parseLong(updateDisabledForm.getUserId());
            boolean newValue = updateDisabledForm.getNewValue().equals("1");
            System.out.println(newValue);
            userService.updateDisabled(userId, newValue);
            if (userId == getUser().getId()) {
                unsetUser();
            }
        }
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }
}
