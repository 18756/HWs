package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalksPage extends CommonPage {
    private final TalkService talkService = new TalkService();

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            setMessage("You aren't logged in");
            throw new RedirectException("/index");
        }
        view.put("users", userService.findAll());
    }

    protected void sendMessage() {
        long sourceUserId = getUser().getId();
        long targetUserId = Long.parseLong(request.getParameter("targetUserId"));
        String text = request.getParameter("text");
        if (Strings.isNullOrEmpty(text)) {
            setMessage("Empty message");
        } else {
            talkService.sendMessage(sourceUserId, targetUserId, text);
        }
    }

    @Override
    protected void after(Map<String, Object> view) {
        super.after(view);
        view.put("talks", talkService.findAllByUserId(getUser().getId()));
    }
}
