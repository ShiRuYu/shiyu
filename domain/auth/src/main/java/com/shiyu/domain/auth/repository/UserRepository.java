package com.shiyu.domain.auth.repository;


import com.shiyu.common.utils.ResultPage;
import com.shiyu.domain.auth.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    User update(User user);

    User selectById(Long id);

    ResultPage<User> selectPage(Integer pageNo, Integer pageSize);

    List<User> selectBatchIds(List<Long> userIdList);

    void delete(Long id);

    User selectByNameAndPasswd(String username, String password);
}
