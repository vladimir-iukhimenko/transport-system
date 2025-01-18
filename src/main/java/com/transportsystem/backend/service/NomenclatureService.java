package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.NomenclatureRepository;
import com.transportsystem.backend.model.Nomenclature;

import java.util.List;

/**
 * Created by Владимир on 29.11.2019.
 */
@Service
public class NomenclatureService {
    private NomenclatureRepository nomenclatureRepository;

    @Autowired
    public void setNomenclatureDAO(NomenclatureRepository nomenclatureRepository) {this.nomenclatureRepository = nomenclatureRepository;}

    @Transactional
    public void add(Nomenclature nomenclature) {
        nomenclatureRepository.save(nomenclature);}

    @Transactional
    public void delete(Nomenclature nomenclature) {
        nomenclatureRepository.delete(nomenclature);}

    @Transactional
    public void edit(Nomenclature nomenclature) {
        nomenclatureRepository.save(nomenclature);}

    @Transactional
    public Nomenclature getNomenclatureById(Integer id) {return nomenclatureRepository.findById(id).orElse(null);}

    @Transactional
    public List<Nomenclature> getAllNomenclatures() {return nomenclatureRepository.findAll();}
}
