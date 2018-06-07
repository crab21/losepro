package com.example.combinerabbit.mapper;

import com.example.combinerabbit.config.mysql.TargetSource;
import com.example.combinerabbit.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by k on 2018/6/7.
 */
@Repository
@Mapper
@Transactional

public interface StudentMapper {
    @Transactional
    @TargetSource("db2")
    @Select("select count(*) from user")
    int findAllUsers();
}
