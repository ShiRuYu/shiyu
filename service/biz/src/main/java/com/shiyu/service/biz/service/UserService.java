package com.shiyu.service.biz.service;

import com.shiyu.service.biz.model.web.UserDTO;
import com.shiyu.service.biz.model.web.UserVO;

public interface UserService {
    UserVO save(UserDTO userDTO);

    UserVO detail(Long id);
}
