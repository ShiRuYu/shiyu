package com.shiyu.service.core;

import com.shiyu.infrastructure.datasource.model.UserPO;
import com.shiyu.infrastructure.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {
    private final UserRepository userRepository;

    @Override
    public UserPO save(UserPO userPO){
        return userRepository.save(userPO);
    }
    @Override
    public UserPO findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
