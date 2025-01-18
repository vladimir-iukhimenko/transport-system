package com.transportsystem.backend.repository;

import com.transportsystem.backend.model.TransportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Repository
public interface TransportModelRepository extends JpaRepository<TransportModel, Integer> {
}
