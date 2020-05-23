package com.transportsystem.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonService {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getCommentFromJson(String json) throws JsonProcessingException {
        JsonNode content = objectMapper.readTree(json);
        return content.path("comment").asText();
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public JsonNode getValueFromJson(String json, String value) throws JsonProcessingException {
        JsonNode objectNode = objectMapper.readTree(json);
        return objectNode.path(value);
    }
}
