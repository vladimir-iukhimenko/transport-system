package transportsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transportsystem.dao.TransportOrderDAO;
import transportsystem.model.TransportOrder;

import java.util.List;

/**
 * Created by DZCKJS0 on 02.12.2019.
 */
@Service
public class TransportOrderService {
    private TransportOrderDAO transportOrderDAO;

    @Autowired
    public void setTransportOrderDAO(TransportOrderDAO transportorderDAO) {
        this.transportOrderDAO = transportorderDAO;
    }

    @Transactional
    public void addTransportOrder(TransportOrder transportOrder) {
        transportOrderDAO.addTransportOrder(transportOrder);}

    @Transactional
    public void deleteTransportOrder(TransportOrder transportOrder) {transportOrderDAO.deleteTransportOrder(transportOrder);}

    @Transactional
    public void editTransportOrder(TransportOrder transportOrder) {transportOrderDAO.editTransportOrder(transportOrder);}

    @Transactional
    public TransportOrder getTransportOrderById(int id) {return transportOrderDAO.getTransportOrderById(id);}

    @Transactional
    public List<TransportOrder> getAllTransportOrders() {return transportOrderDAO.getAllTransportOrders();}

}
