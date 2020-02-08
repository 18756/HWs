package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.AddNoticeForm;
import ru.itmo.wp.form.validator.NoticeCreateValidator;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AddNoticePage extends Page {
    private final NoticeService noticeService;
    private final NoticeCreateValidator noticeCreateValidator;

    public AddNoticePage(NoticeService noticeService, NoticeCreateValidator noticeCreateValidator) {
        this.noticeService = noticeService;
        this.noticeCreateValidator = noticeCreateValidator;
    }

    @InitBinder("addForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(noticeCreateValidator);
    }

    @GetMapping("/addNotice")
    public String addNotice(Model model) {
        model.addAttribute("addForm", new AddNoticeForm());
        return "AddNoticePage";
    }

    @PostMapping("/addNotice")
    public String addNotice(@Valid @ModelAttribute("addForm") AddNoticeForm addForm,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "AddNoticePage";
        }

        noticeService.addNotice(addForm);
        putMessage("The notice was created");

        return "redirect:/";
    }
}
