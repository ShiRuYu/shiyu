package com.shiyu.infrastructure.datasource.repository;

import com.shiyu.infrastructure.datasource.model.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RolePO,Long> {
}
