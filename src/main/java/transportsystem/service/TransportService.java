package transportsystem.service;

import transportsystem.dao.TransportDAO;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;

import java.util.*;

/**
 * Created by Владимир on 10.11.2019.
 */
public class TransportService {
    private TransportDAO TransportDAO = new TransportDAO();

    public List<Transport> getAllTransports()
    {
        return TransportDAO.getAllTransports();
    }

    public List<TransportModel> getAllTransportModels() { return TransportDAO.getAllTransportModels();}

    public void add(Transport transport)
    {
        TransportDAO.add(transport);
    }

    public void delete(Transport transport)
    {
        TransportDAO.delete(transport);
    }

    public void edit(Transport transport)
    {
        TransportDAO.edit(transport);
    }

    public Transport getTransportById(int id)
    {
        return TransportDAO.getTransportById(id);
    }

    public TransportModel getTransportModelById(int id) {return TransportDAO.getTransportModelById(id);}
}
