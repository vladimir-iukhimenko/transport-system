package transportsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transportsystem.dao.TransportDAO;
import transportsystem.model.Engine;
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
    public void addTransport(Transport transport)
    {
        TransportDAO.addTransport(transport);
    }

    @Transactional
    public void deleteTransport(Transport transport)
    {
        TransportDAO.deleteTransport(transport);
    }

    @Transactional
    public void editTransport(Transport transport)
    {
        TransportDAO.editTransport(transport);
    }

    @Transactional
    public Transport getTransportById(int id)
    {
        return TransportDAO.getTransportById(id);
    }

    @Transactional
    public List<Transport> getAllTransports()
    {
        return TransportDAO.getAllTransports();
    }


}
