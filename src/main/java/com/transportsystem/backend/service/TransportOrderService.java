package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.TransportOrderRepository;
import com.transportsystem.backend.model.TransportOrder;

import java.util.List;

/**
 * Created by DZCKJS0 on 02.12.2019.
 */
@Service
public class TransportOrderService {
    private TransportOrderRepository transportOrderRepository;

    @Autowired
    public void setTransportOrderDAO(TransportOrderRepository transportorderRepository) {
        this.transportOrderRepository = transportorderRepository;
    }

    @Transactional
    public void addTransportOrder(TransportOrder transportOrder) {
        transportOrderRepository.save(transportOrder);}

    @Transactional
    public void deleteTransportOrder(TransportOrder transportOrder) {
        transportOrderRepository.delete(transportOrder);}

    @Transactional
    public void editTransportOrder(TransportOrder transportOrder) {
        transportOrderRepository.save(transportOrder);}

    @Transactional
    public TransportOrder getTransportOrderById(int id) {return transportOrderRepository.findById(id).orElse(null);}

    @Transactional
    public List<TransportOrder> getAllTransportOrders() {return transportOrderRepository.findAll();}

}
