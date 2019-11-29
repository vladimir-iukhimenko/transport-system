package transportsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transportsystem.dao.NomenclatureDAO;
import transportsystem.model.Nomenclature;

import java.util.List;

/**
 * Created by Владимир on 29.11.2019.
 */
@Service
public class NomenclatureService {
    private NomenclatureDAO nomenclatureDAO;

    @Autowired
    public void setNomenclatureDAO(NomenclatureDAO nomenclatureDAO) {this.nomenclatureDAO = nomenclatureDAO;}

    @Transactional
    public void add(Nomenclature nomenclature) {nomenclatureDAO.add(nomenclature);}

    @Transactional
    public void delete(Nomenclature nomenclature) {nomenclatureDAO.delete(nomenclature);}

    @Transactional
    public void edit(Nomenclature nomenclature) {nomenclatureDAO.edit(nomenclature);}

    @Transactional
    public Nomenclature getNomenclatureById(Integer id) {return nomenclatureDAO.getNomenclatureById(id);}

    @Transactional
    public List<Nomenclature> getAllNomenclatures() {return nomenclatureDAO.getAllNomenclatures();}
}
