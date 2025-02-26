package com.transportsystem.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.transportsystem.backend.model.Transport;


/**
 * Created by Владимир on 09.11.2019.
 */
@Repository
public interface TransportRepository extends JpaRepository<Transport, Integer> { }
