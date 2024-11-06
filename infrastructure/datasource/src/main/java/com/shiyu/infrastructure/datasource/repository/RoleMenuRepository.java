package com.shiyu.infrastructure.datasource.repository;

import com.shiyu.infrastructure.datasource.model.RoleMenuPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenuPO,Long> {
}
