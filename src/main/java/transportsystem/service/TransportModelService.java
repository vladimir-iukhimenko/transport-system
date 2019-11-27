package transportsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transportsystem.dao.TransportModelDAO;
import transportsystem.model.TransportModel;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Service
public class TransportModelService {
    private TransportModelDAO transportModelDAO;

    @Autowired
    public void setTransportModelDAO(TransportModelDAO transportModelDAO) {this.transportModelDAO = transportModelDAO;}

    @Transactional
    public void addTransportModel(TransportModel transportModel)
    {
        transportModelDAO.addTransportModel(transportModel);
    }

    @Transactional
    public void deleteTransportModel(TransportModel transportModel) {transportModelDAO.addTransportModel(transportModel);}

    @Transactional
    public void editTransportModel(TransportModel transportModel) {transportModelDAO.editTransportModel(transportModel);}

    @Transactional
    public TransportModel getTransportModelById(int id)
    {
        return transportModelDAO.getTransportModelById(id);
    }

    @Transactional
    public List<TransportModel> getAllTransportModels() { return transportModelDAO.getAllTransportModels();}

}
