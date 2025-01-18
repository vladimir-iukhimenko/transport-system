package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.TransportModelRepository;
import com.transportsystem.backend.model.TransportModel;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Service
public class TransportModelService {
    private TransportModelRepository transportModelRepository;

    @Autowired
    public void setTransportModelDAO(TransportModelRepository transportModelRepository) {this.transportModelRepository = transportModelRepository;}

    @Transactional
    public void addTransportModel(TransportModel transportModel)
    {
        transportModelRepository.save(transportModel);
    }

    @Transactional
    public void deleteTransportModel(TransportModel transportModel) {
        transportModelRepository.delete(transportModel);}

    @Transactional
    public void editTransportModel(TransportModel transportModel) {
        transportModelRepository.save(transportModel);}

    @Transactional
    public TransportModel getTransportModelById(int id)
    {
        return transportModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<TransportModel> getAllTransportModels() { return transportModelRepository.findAll();}

}
