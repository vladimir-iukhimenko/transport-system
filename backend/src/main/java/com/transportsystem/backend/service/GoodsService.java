package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.dao.GoodsDAO;
import com.transportsystem.backend.model.Goods;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Service
public class GoodsService {

    private GoodsDAO goodsDAO;

    @Autowired
    public void setGoodsDAO(GoodsDAO goodsDAO) {this.goodsDAO = goodsDAO;}

    @Transactional
    public void add(Goods goods) {goodsDAO.add(goods);}

    @Transactional
    public void delete(Goods goods) {goodsDAO.delete(goods);}

    @Transactional
    public void edit(Goods goods) {goodsDAO.edit(goods);}

    @Transactional
    public Goods getGoodsById(Integer id) {return goodsDAO.getGoodsById(id);}

    @Transactional
    public List<Goods> getAllGoods() {return goodsDAO.getAllGoods();}
}
