package com.shiyu.core.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.core.domain.auth.model.User;
import com.shiyu.core.domain.auth.repository.UserRepository;
import com.shiyu.core.infrastructure.datasource.mapper.UserMapper;
import com.shiyu.core.infrastructure.datasource.mapstruct.UserConvertMapper;
import com.shiyu.core.infrastructure.datasource.model.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public User save(User user){
        UserPO userPO = UserConvertMapper.INSTANCE.detailToPo(user);
        userMapper.insert(userPO);
        return UserConvertMapper.INSTANCE.poToDetail(userPO);
    }

    @Override
    public User update(User user){
        int insert = userMapper.updateById(UserConvertMapper.INSTANCE.detailToPo(user));
        UserPO userPO = userMapper.selectById(user.getId());
        return UserConvertMapper.INSTANCE.poToDetail(userPO);
    }

    @Override
    public User selectById(Long id){
        UserPO userPO = userMapper.selectById(id);
        return UserConvertMapper.INSTANCE.poToDetail(userPO);
    }


    @Override
    public ResultPage<User> selectPage(Integer pageNo, Integer pageSize) {
        // queryWrapper组装查询where条件
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>();
        // 分页参数
        PageDTO<UserPO> userPOPageDTO = userMapper.selectPage(new PageDTO<>(pageNo, pageSize), queryWrapper);
        return UserConvertMapper.INSTANCE.poPageToDetailPage(userPOPageDTO);
    }

    @Override
    public List<User> selectBatchIds(List<Long> userIdList) {
        List<UserPO> userPOList = userMapper.selectBatchIds(userIdList);
        return UserConvertMapper.INSTANCE.listPoToDetail(userPOList);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public User selectByNameAndPasswd(String username, String password) {
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPO::getUsername,username)
                .eq(UserPO::getPassword,password);
        UserPO userPO = userMapper.selectOne(queryWrapper);
        return UserConvertMapper.INSTANCE.poToDetail(userPO);
    }
}
