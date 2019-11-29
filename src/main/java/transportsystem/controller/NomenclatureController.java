package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Nomenclature;
import transportsystem.service.NomenclatureService;

import java.util.List;

/**
 * Created by Владимир on 29.11.2019.
 */
@Controller
public class NomenclatureController {
    private NomenclatureService nomenclatureService;

    @Autowired
    public void setNomenclatureService(NomenclatureService nomenclatureService) {this.nomenclatureService = nomenclatureService;}

    @RequestMapping(value = "/nomenclatures", method = RequestMethod.GET)
    public ModelAndView listNomenclatures() {
        List<Nomenclature> listNomenclatures = nomenclatureService.getAllNomenclatures();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("nomenclatures");
        modelAndView.addObject("nomenclatures",listNomenclatures);
        return modelAndView;
    }

    @RequestMapping(value = "/nomenclatures/add", method = RequestMethod.GET)
    public ModelAndView addNomenclature() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editornomenclature");
        return modelAndView;
    }

    @RequestMapping(value = "/nomenclatures/add", method = RequestMethod.POST)
    public ModelAndView addNomenclature(@ModelAttribute("nomenclature") Nomenclature nomenclature)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/nomenclatures");
        nomenclatureService.add(nomenclature);
        return modelAndView;
    }

    @RequestMapping(value = "/nomenclatures/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editNomenclature(@PathVariable("id") int id)
    {
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editornomenclatures");
        modelAndView.addObject("nomenclature", nomenclature);
        return modelAndView;
    }

    @RequestMapping(value = "/nomenclatures/edit", method = RequestMethod.POST)
    public ModelAndView editNomenclature(@ModelAttribute("nomenclature") Nomenclature nomenclature)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/nomenclatures");
        nomenclatureService.edit(nomenclature);
        return modelAndView;
    }

    @RequestMapping(value = "/nomenclatures/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGoods(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/nomenclatures");
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(id);
        nomenclatureService.delete(nomenclature);
        return modelAndView;
    }
}
