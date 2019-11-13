package transportsystem.dao;

import org.springframework.stereotype.Repository;
import transportsystem.model.Engine;
import transportsystem.model.Transport;
import transportsystem.model.TransportModel;

import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Владимир on 09.11.2019.
 */
@Repository
public class TransportDAO{
    private static Map<Integer,Transport> transports = new HashMap<>();
    private static Map<Integer,TransportModel> transportmodels = new HashMap<>();
    //private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static final AtomicInteger AUTO_ID_SECOND = new AtomicInteger(0);

    static{
        Engine engine1 = new Engine("1,6 DSI",1.6,"Бензин");
        TransportModel model1 = new TransportModel("Volkswagen Polo","Белый","Volkswagen",600,engine1,"115 л.с.",AUTO_ID_SECOND.getAndIncrement());
        TransportModel model2 = new TransportModel("Skoda Rapid","Синий","Skoda",500, engine1, "106 л.с.",AUTO_ID_SECOND.getAndIncrement());
        Transport transport1 = new Transport("М226ВК40","104GP25B03B108775",model1.getId(),2013,"2009-04-20");
        Transport transport2 = new Transport("Н954СЕ40","244GP50G17C191542",model1.getId(),2018,"2001-02-01");
        Transport transport3 = new Transport("К062МТ40","472GP12T13D823851",model1.getId(),2016,"2010-01-15");
        Transport transport4 = new Transport("А405КН40","04FVE34GNGJ3ENR23",model2.getId(),2015,"2019-08-10");
        transports.put(transport1.getId(),transport1);
        transports.put(transport2.getId(),transport2);
        transports.put(transport3.getId(),transport3);
        transports.put(transport4.getId(),transport4);
        transportmodels.put(model1.getId(),model1);
        transportmodels.put(model2.getId(),model2);


    }

    public void add(Transport transport)
    {
        transports.put(transport.getId(),transport);
    }

    public void delete(Transport transport)
    {
        transports.remove(transport.getId());
    }

    public Transport getTransportById(int id)
    {
        return transports.get(id);
    }

    public TransportModel getTransportModelById(int id) { return transportmodels.get(id);}

    public void edit(Transport transport)
    {
        transports.put(transport.getId(),transport);
    }

    public List<Transport> getAllTransports()
    {
        return new ArrayList<>(transports.values());
    }

    public List<TransportModel> getAllTransportModels() {return new ArrayList<>(transportmodels.values());}
}
