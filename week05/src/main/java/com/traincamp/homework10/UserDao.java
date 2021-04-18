package com.traincamp.homework10;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@Transactional(propagation=Propagation.REQUIRED)
public interface UserDao extends BaseMapper<User>{

}
