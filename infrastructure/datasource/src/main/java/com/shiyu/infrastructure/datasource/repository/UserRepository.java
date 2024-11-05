package com.shiyu.infrastructure.datasource.repository;

import com.shiyu.infrastructure.datasource.model.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {
}
