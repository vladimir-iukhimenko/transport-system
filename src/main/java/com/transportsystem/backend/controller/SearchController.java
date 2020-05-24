package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.transportsystem.backend.service.SearchService;

import java.util.List;

@RestController
@RequestMapping(path = "api/search",consumes = "application/json")
public class SearchController {
    private SearchService searchService;
    private JsonService jsonService;

    @Autowired
    public void setSearchService(SearchService searchService) { this.searchService = searchService; }

    @Autowired
    public void setJsonService(JsonService jsonService) { this.jsonService = jsonService; }

    @PostMapping
    public List search(@RequestBody String data) throws JsonProcessingException
    {
        String context = jsonService.getValueFromJson(data,"context").asText();
        String query = jsonService.getValueFromJson(data, "query").asText();

        if(context.equals("/employees")){
            return searchService.searchEmployee(query);
        }

        if(context.equals("/transports")){
            return searchService.searchTransport(query);
        }

        if(context.equals("/transportmodels")){
            return searchService.searchTransportModel(query);
        }

        if(context.equals("/goods")){
            return searchService.searchGoods(query);
        }

        if(context.equals("/nomenclatures")){
            return searchService.searchNomenclature(query);
        }

        if(context.equals("/transportorders") || context.equals("/")){
            return searchService.searchTransportOrder(query);
        }

        return null;
    }

}
