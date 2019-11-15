package transportsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transportsystem.dao.TransportDAO;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;

import java.util.*;

/**
 * Created by Владимир on 10.11.2019.
 */
@Service
public class TransportService {
    private TransportDAO TransportDAO;

    @Autowired
    public void setTransportDAO(TransportDAO transportdao)
    {
        this.TransportDAO = transportdao;
    }

    @Transactional
    public List<Transport> getAllTransports()
    {
        return TransportDAO.getAllTransports();
    }

    @Transactional
    public List<TransportModel> getAllTransportModels() { return TransportDAO.getAllTransportModels();}

    @Transactional
    public void add(Transport transport)
    {
        TransportDAO.add(transport);
    }

    @Transactional
    public void delete(Transport transport)
    {
        TransportDAO.delete(transport);
    }

    @Transactional
    public void edit(Transport transport)
    {
        TransportDAO.edit(transport);
    }

    @Transactional
    public Transport getTransportById(int id)
    {
        return TransportDAO.getTransportById(id);
    }

    @Transactional
    public TransportModel getTransportModelById(int id) {return TransportDAO.getTransportModelById(id);}
}
