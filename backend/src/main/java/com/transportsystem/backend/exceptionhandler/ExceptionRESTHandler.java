package com.transportsystem.backend.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ExceptionRESTHandler implements ErrorController {

    private ErrorAttributes errorAttributes;

    @Autowired
    public void setErrorAttributes(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    @ResponseBody
    public ExceptionResponse error(WebRequest webRequest, HttpServletResponse response) throws IOException {
        if (response.getStatus() == 404) {response.sendRedirect("/");}
        return new ExceptionResponse(response.getStatus(), getErrorAttributes(webRequest));
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.putAll(errorAttributes.getErrorAttributes(webRequest, false));
        return errorMap;
    }
}
