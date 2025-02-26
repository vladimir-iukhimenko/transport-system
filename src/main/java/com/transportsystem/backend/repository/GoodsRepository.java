package com.transportsystem.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.transportsystem.backend.model.Goods;


/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
