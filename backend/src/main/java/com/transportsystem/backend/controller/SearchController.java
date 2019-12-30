package com.transportsystem.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.transportsystem.backend.service.SearchService;

import java.util.List;

@Controller
public class SearchController {
    private SearchService searchService;

    @Autowired
    public void setSearchService(SearchService searchService) {this.searchService = searchService;}

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("query") String query, @RequestParam("context") String context)
    {
        ModelAndView modelAndView = new ModelAndView();
        if(context.equals("employees")){
            List listemployees = searchService.searchEmployee(query);
            modelAndView.addObject("employees",listemployees);
        }

        if(context.equals("transports")){
            List listtransports = searchService.searchTransport(query);
            modelAndView.addObject("transports",listtransports);
        }

        if(context.equals("transportmodels")){
            List listtransportmodels = searchService.searchTransportModel(query);
            modelAndView.addObject("transportmodels",listtransportmodels);
        }

        if(context.equals("goods")){
            List listgoods = searchService.searchGoods(query);
            modelAndView.addObject("goods",listgoods);
        }

        if(context.equals("nomenclatures")){
            List listnomenclatures = searchService.searchNomenclature(query);
            modelAndView.addObject("nomenclatures",listnomenclatures);
        }

        if(context.equals("transportorders")){
            List listtransportorders = searchService.searchTransportOrder(query);
            modelAndView.addObject("transportorders",listtransportorders);
        }

        modelAndView.setViewName(context + "/" + context);
        return modelAndView;
    }

}
