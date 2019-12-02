package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Goods;
import transportsystem.model.Nomenclature;
import transportsystem.service.GoodsService;
import transportsystem.service.NomenclatureService;

import java.util.List;
/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Controller
public class GoodsController {
    private GoodsService goodsService;

    private NomenclatureService nomenclatureService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {this.goodsService = goodsService;}

    @Autowired
    public void setNomenclatureService(NomenclatureService nomenclatureService) {this.nomenclatureService = nomenclatureService;}

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ModelAndView listGoods() {
        List<Goods> listGoods = goodsService.getAllGoods();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods/goods");
        modelAndView.addObject("goods",listGoods);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/add", method = RequestMethod.GET)
    public ModelAndView addGoods() {
        ModelAndView modelAndView = new ModelAndView();
        List<Nomenclature> nomenclatures = nomenclatureService.getAllNomenclatures();
        modelAndView.setViewName("goods/editorgoods");
        modelAndView.addObject("nomenclatures", nomenclatures);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/add", method = RequestMethod.POST)
    public ModelAndView addGoods(@ModelAttribute("goods") Goods goods, @RequestParam("nomenclatureid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(id);
        goods.addNomenclature(nomenclature);
        modelAndView.setViewName("redirect:/goods");
        goodsService.add(goods);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editGoods(@PathVariable("id") int id)
    {
        Goods goods = goodsService.getGoodsById(id);
        List<Nomenclature> nomenclatures = nomenclatureService.getAllNomenclatures();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods/editorgoods");
        modelAndView.addObject("goods", goods);
        modelAndView.addObject("nomenclatures", nomenclatures);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/edit", method = RequestMethod.POST)
    public ModelAndView editGoods(@ModelAttribute("goods") Goods goods, @RequestParam("nomenclatureid") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(id);
        goods.addNomenclature(nomenclature);
        modelAndView.setViewName("redirect:/goods");
        goodsService.edit(goods);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGoods(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        Goods goods = goodsService.getGoodsById(id);
        goodsService.delete(goods);
        return modelAndView;
    }
}
