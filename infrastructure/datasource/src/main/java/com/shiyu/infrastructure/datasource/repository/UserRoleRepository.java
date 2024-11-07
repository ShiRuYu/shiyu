package com.shiyu.infrastructure.datasource.repository;

import com.shiyu.infrastructure.datasource.model.UserRolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRolePO, Long> {

    List<Long> findRoleIdByUserId(Long userId);

}
