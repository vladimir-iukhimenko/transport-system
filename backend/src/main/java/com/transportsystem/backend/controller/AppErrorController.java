package com.transportsystem.backend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public void backToMain(HttpServletResponse httpResponse) throws IOException {
        httpResponse.sendRedirect("/");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
