package com.transportsystem.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.transportsystem.backend.model.Nomenclature;



/**
 * Created by Владимир on 29.11.2019.
 */
@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Integer> {
}
