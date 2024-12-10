package com.shiyu.core.domain.auth.service;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.core.domain.auth.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User update(User user);

    User selectById(Long id);

    ResultPage<User> selectPage(Integer pageNo, Integer pageSize);

    List<User> selectBatchIds(List<Long> userIdList);

    void delete(Long id);

    User selectByNameAndPasswd(String username, String password);
}
