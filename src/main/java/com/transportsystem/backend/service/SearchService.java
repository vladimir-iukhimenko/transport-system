package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.dao.Searcher;
import com.transportsystem.backend.model.*;

import java.util.List;

@Service
public class SearchService {
    private final String[] EMPLOYEE_SEARCH_FIELDS = {"surname", "name", "patronymic", "position"};
    private final String[] TRANSPORT_SEARCH_FIELDS = {"number", "vin", "producedyear"};
    private final String[] TRANSPORTMODEL_SEARCH_FIELDS = {"name", "color", "producer"};
    private final String[] GOODS_SEARCH_FIELDS = {"name"};
    private final String[] NOMENCLATURE_SEARCH_FIELDS = {"number"};
    private final String[] TRANSPORTORDER_SEARCH_FIELDS = {"ordernumber"};
    private Searcher searcher;

    @Autowired
    public void setEmployeeSearch(Searcher searcher) {
        this.searcher = searcher;
    }

    @Transactional
    public List searchEmployee(String text) {
        return searcher.search(text, Employee.class, EMPLOYEE_SEARCH_FIELDS);
    }

    @Transactional
    public List searchTransport(String text) {
        return searcher.search(text, Transport.class, TRANSPORT_SEARCH_FIELDS);
    }

    @Transactional
    public List searchTransportModel(String text) {
        return searcher.search(text, TransportModel.class, TRANSPORTMODEL_SEARCH_FIELDS);
    }

    @Transactional
    public List searchGoods(String text) {
        return searcher.search(text, Goods.class, GOODS_SEARCH_FIELDS);
    }

    @Transactional
    public List searchNomenclature(String text) {
        return searcher.search(text, Nomenclature.class, NOMENCLATURE_SEARCH_FIELDS);
    }

    @Transactional
    public List searchTransportOrder(String text) {
        return searcher.search(text, TransportOrder.class, TRANSPORTORDER_SEARCH_FIELDS);
    }
}
