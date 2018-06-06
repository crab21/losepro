package com.example.combinerabbit.mapper;

import com.example.combinerabbit.config.mysql.TargetSource;
import com.example.combinerabbit.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
@Repository
public interface ShiroMapper {

    User findByUserName(@Param("username") String username);



}
