package com.shiyu.domain.auth.service.impl;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.query.UserQueryCondition;
import com.shiyu.domain.auth.repository.UserRepository;
import com.shiyu.domain.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User selectById(Long id) {
        return userRepository.selectById(id);
    }

    @Override
    public ResultPage<User> selectPage(UserQueryCondition condition, Integer pageNo, Integer pageSize) {
        return userRepository.selectPage(condition, pageNo, pageSize);
    }

    @Override
    public List<User> selectBatchIds(List<Long> userIdList) {
        return userRepository.selectBatchIds(userIdList);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User selectByNameAndPasswd(String username, String password) {
        return userRepository.selectByNameAndPasswd(username, password);
    }

    @Override
    public boolean checkUserName(String username) {
        return userRepository.checkUserName(username);
    }
}
