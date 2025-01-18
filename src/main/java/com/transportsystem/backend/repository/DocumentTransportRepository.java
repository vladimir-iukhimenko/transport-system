package com.transportsystem.backend.repository;

import com.transportsystem.backend.model.DocumentTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by DZCKJS0 on 18.11.2019.
 */
@Repository
public interface DocumentTransportRepository extends JpaRepository<DocumentTransport, Integer> { }
