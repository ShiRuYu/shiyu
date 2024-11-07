package com.shiyu.service.core.manager.Impl;

import com.shiyu.infrastructure.datasource.model.UserPO;
import com.shiyu.infrastructure.datasource.repository.UserRepository;
import com.shiyu.infrastructure.datasource.repository.UserRoleRepository;
import com.shiyu.service.core.manager.UserManager;
import com.shiyu.service.core.mapstruct.UserCoreMapper;
import com.shiyu.service.core.model.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserBO save(UserBO userBO){
        UserPO save = userRepository.save(UserCoreMapper.INSTANCE.boToPo(userBO));
        return UserCoreMapper.INSTANCE.poToBO(save);
    }

    @Override
    public UserBO findById(Long id){
        UserPO userPO = userRepository.findById(id).orElse(null);
        UserBO userBO = UserCoreMapper.INSTANCE.poToBO(userPO);
        return userBO;
    }
}
