package com.shiyu.service.biz.service.Impl;

import com.shiyu.service.biz.mapstruct.UserBizMapper;
import com.shiyu.service.biz.model.web.UserDTO;
import com.shiyu.service.biz.model.web.UserVO;
import com.shiyu.service.biz.service.UserService;
import com.shiyu.service.core.manager.RoleManager;
import com.shiyu.service.core.manager.UserManager;
import com.shiyu.service.core.model.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserManager userManager;
    private final RoleManager roleManager;

    @Override
    public UserVO save(UserDTO userDTO){
        UserBO userBO = UserBizMapper.INSTANCE.dtoToBO(userDTO);
        UserBO save = userManager.save(userBO);
        return UserBizMapper.INSTANCE.boToVO(save);
    }

    @Override
    public UserVO detail(Long id) {
        UserBO userBO = userManager.findById(id);
        UserVO userVO = UserBizMapper.INSTANCE.boToVO(userBO);
        return userVO;
    }
}
