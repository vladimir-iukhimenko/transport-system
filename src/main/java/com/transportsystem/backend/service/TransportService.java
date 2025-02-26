package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.TransportRepository;
import com.transportsystem.backend.model.Transport;

import java.util.List;

/**
 * Created by Владимир on 10.11.2019.
 */
@Service
public class TransportService {
    private TransportRepository TransportRepository;

    @Autowired
    public void setTransportDAO(TransportRepository transportdao)
    {
        this.TransportRepository = transportdao;
    }

    @Transactional
    public void addTransport(Transport transport)
    {
        TransportRepository.save(transport);
    }

    @Transactional
    public void deleteTransport(Transport transport)
    {
        TransportRepository.delete(transport);
    }

    @Transactional
    public void editTransport(Transport transport)
    {
        TransportRepository.save(transport);
    }

    @Transactional
    public Transport getTransportById(int id)
    {
        return TransportRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Transport> getAllTransports()
    {
        return TransportRepository.findAll();
    }


}
