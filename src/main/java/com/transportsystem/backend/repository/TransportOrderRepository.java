package com.transportsystem.backend.repository;

import com.transportsystem.backend.model.TransportOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by DZCKJS0 on 02.12.2019.
 */
@Repository
public interface TransportOrderRepository extends JpaRepository<TransportOrder, Integer> {
}
