package com.transportsystem.backend.exceptionhandler;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
public class ExceptionResponse {
    private Integer status;
    private String path;
    private String message;
    private String trace;

    public ExceptionResponse(int status, Map<String, Object> errorAttributes) {
        this.setStatus(status);
        this.setPath((String) errorAttributes.get("path"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTrace((String) errorAttributes.get("trace"));
    }

}
