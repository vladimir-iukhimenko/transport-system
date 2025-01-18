package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.GoodsRepository;
import com.transportsystem.backend.model.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Service
public class GoodsService {

    private GoodsRepository goodsRepository;

    @Autowired
    public void setGoodsDAO(GoodsRepository goodsRepository) {this.goodsRepository = goodsRepository;}

    @Transactional
    public void add(Goods goods) {
        goodsRepository.save(goods);}

    @Transactional
    public void delete(Goods goods) {
        goodsRepository.delete(goods);}

    @Transactional
    public void edit(Goods goods) {
        goodsRepository.save(goods);}

    @Transactional
    public Goods getGoodsById(Integer id) {return goodsRepository.findById(id).orElse(null);}

    @Transactional
    public List<Goods> getAllGoods() {return goodsRepository.findAll();}

    @Transactional
    public List<Goods> getAllGoodsWithoutTransportOrders() {
        List<Goods> allGoods = getAllGoods();
        List<Goods> goodsWithoutTransportOrders = new ArrayList<>();
        allGoods.forEach(goods -> {
            if (goods.getTransportorder() == null)
            {
                goodsWithoutTransportOrders.add(goods);
            }
        });
        return goodsWithoutTransportOrders;
    }
}
