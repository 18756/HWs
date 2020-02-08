package ru.itmo.wp.servlet;

import org.apache.commons.codec.binary.Base64;
import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {

    private static final String IS_CAPTCHA_PASSED = "isPassedCaptcha";
    private static final String REAL_CAPTCHA = "realCaptcha";
    private static final String USER_CAPTCHA = "userCaptcha";
    private static final int MIN_CAPTCHA = 100;
    private static final int MAX_CAPTCHA = 999;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession();
            Object isCaptchaPassed = session.getAttribute(IS_CAPTCHA_PASSED);
            System.out.println(isCaptchaPassed);
            if (!"passed".equals(isCaptchaPassed)) {
                Object realCaptcha = session.getAttribute(REAL_CAPTCHA);
                String userCaptcha = request.getParameter(USER_CAPTCHA);
                if (realCaptcha != null && String.valueOf(realCaptcha).equals(userCaptcha)) {
                    session.setAttribute(IS_CAPTCHA_PASSED, "passed");
                } else {
                    int captcha = new Random().nextInt(MAX_CAPTCHA - MIN_CAPTCHA + 1) + MIN_CAPTCHA;
                    session.setAttribute(REAL_CAPTCHA, captcha);
                    response.setContentType("text/html");
                    Path captchaForm = Paths.get(getServletContext().getRealPath("/static/captcha_form.html"));
                    String file = new String(Files.readAllBytes(captchaForm), StandardCharsets.UTF_8);
                    PrintWriter writer = response.getWriter();
                    String base64Img = Base64.encodeBase64String(ImageUtils.toPng(captcha + ""));
                    writer.printf(file, base64Img);
                    writer.close();
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
