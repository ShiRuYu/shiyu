package com.shiyu.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.query.UserQueryCondition;
import com.shiyu.domain.auth.repository.UserRepository;
import com.shiyu.infrastructure.datasource.mapper.UserMapper;
import com.shiyu.infrastructure.datasource.mapstruct.UserDBConvertMapper;
import com.shiyu.infrastructure.datasource.model.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public User save(User user){
        UserPO userPO = UserDBConvertMapper.INSTANCE.detailToPo(user);
        userMapper.insert(userPO);
        return UserDBConvertMapper.INSTANCE.poToDetail(userPO);
    }

    @Override
    public User update(User user){
        int insert = userMapper.updateById(UserDBConvertMapper.INSTANCE.detailToPo(user));
        UserPO userPO = userMapper.selectById(user.getId());
        return UserDBConvertMapper.INSTANCE.poToDetail(userPO);
    }

    @Override
    public User selectById(Long id){
        UserPO userPO = userMapper.selectById(id);
        return UserDBConvertMapper.INSTANCE.poToDetail(userPO);
    }


    @Override
    public ResultPage<User> selectPage(UserQueryCondition condition, Integer pageNo, Integer pageSize) {
        // queryWrapper组装查询where条件
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(condition.getUsername() != null, UserPO::getUsername, condition.getUsername())
                .eq(condition.getGender() != null, UserPO::getGender, condition.getGender())
                .eq(condition.getStatus() != null, UserPO::getStatus, condition.getStatus());
        // 分页参数
        PageDTO<UserPO> userPOPageDTO = userMapper.selectPage(new PageDTO<>(pageNo, pageSize), queryWrapper);
        return UserDBConvertMapper.INSTANCE.poPageToDetailPage(userPOPageDTO);
    }

    @Override
    public List<User> selectBatchIds(List<Long> userIdList) {
        List<UserPO> userPOList = userMapper.selectBatchIds(userIdList);
        return UserDBConvertMapper.INSTANCE.listPoToDetail(userPOList);
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
        return UserDBConvertMapper.INSTANCE.poToDetail(userPO);
    }

    @Override
    public boolean checkUserName(String username) {
        return userMapper.selectCount(new LambdaQueryWrapper<UserPO>().eq(UserPO::getUsername, username)) > 0;
    }
}
