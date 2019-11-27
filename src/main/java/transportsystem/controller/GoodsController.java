package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Goods;
import transportsystem.service.GoodsService;
import java.util.List;
/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Controller
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {this.goodsService = goodsService;}

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ModelAndView listGoods() {
        List<Goods> listGoods = goodsService.getAllGoods();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods");
        modelAndView.addObject("goods",listGoods);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/add", method = RequestMethod.GET)
    public ModelAndView addGoods() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editorgoods");
        return modelAndView;
    }

    @RequestMapping(value = "/goods/add", method = RequestMethod.POST)
    public ModelAndView addGoods(@ModelAttribute("goods") Goods goods)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        goodsService.add(goods);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editGoods(@PathVariable("id") int id)
    {
        Goods goods = goodsService.getGoodsById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editorgoods");
        modelAndView.addObject("goods", goods);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/edit", method = RequestMethod.POST)
    public ModelAndView editGoods(@ModelAttribute("goods") Goods goods)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        goodsService.edit(goods);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGoods(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        Goods employee = goodsService.getGoodsById(id);
        goodsService.delete(employee);
        return modelAndView;
    }
}
