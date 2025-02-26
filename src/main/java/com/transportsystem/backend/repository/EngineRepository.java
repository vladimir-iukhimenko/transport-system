package com.transportsystem.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.transportsystem.backend.model.Engine;


/**
 * Created by DZCKJS0 on 28.11.2019.
 */
@Repository
public interface EngineRepository extends JpaRepository<Engine, Integer> {
}
