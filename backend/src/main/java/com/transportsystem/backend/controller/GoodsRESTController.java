package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.model.Goods;
import com.transportsystem.backend.model.Nomenclature;
import com.transportsystem.backend.service.GoodsService;
import com.transportsystem.backend.service.JsonService;
import com.transportsystem.backend.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class GoodsRESTController {
    private GoodsService goodsService;
    private NomenclatureService nomenclatureService;
    private JsonService jsonService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {this.goodsService = goodsService;}

    @Autowired
    public void setNomenclatureService(NomenclatureService nomenclatureService) {this.nomenclatureService = nomenclatureService;}

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

    @GetMapping("/goods")
    public List<Goods> readAllGoods() {
        List<Goods> goods = goodsService.getAllGoodsWithoutTransportOrders();
        return goods;
    }

    @GetMapping("/goods/{id}")
    public Goods readGoods(@PathVariable int id) {
        Goods goods = goodsService.getGoodsById(id);
        return goods;
    }

    @PostMapping(value = "/goods/add",consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Goods createGoods(@RequestBody String data) throws JsonProcessingException {
        Integer nomenclatureid = jsonService.getValueFromJson(data,"nomenclatureid").asInt();
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(nomenclatureid);
        Goods goods = jsonService.getObjectMapper().readValue(data,Goods.class);
        goods.addNomenclature(nomenclature);
        goodsService.add(goods);
        return goods;
    }

    @PutMapping(value = "/goods/edit",consumes = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Goods updateGoods(@RequestBody String data) throws JsonProcessingException {
        Integer nomenclatureid = jsonService.getValueFromJson(data,"nomenclatureid").asInt();
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(nomenclatureid);
        Goods goods = jsonService.getObjectMapper().readValue(data,Goods.class);
        goods.addNomenclature(nomenclature);
        goodsService.edit(goods);
        return goods;
    }

    @DeleteMapping("/goods/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteGoods(@PathVariable int id) {
        Goods goods = goodsService.getGoodsById(id);
        goodsService.delete(goods);
    }

}
