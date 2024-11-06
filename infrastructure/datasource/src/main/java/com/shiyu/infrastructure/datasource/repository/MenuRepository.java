package com.shiyu.infrastructure.datasource.repository;

import com.shiyu.infrastructure.datasource.model.MenuPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuPO,Long> {
}
