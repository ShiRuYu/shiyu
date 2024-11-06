package com.shiyu.infrastructure.datasource.repository;

import com.shiyu.infrastructure.datasource.model.UserRolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRolePO, Long> {
}
