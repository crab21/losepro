package com.example.combinerabbit.mapper;

import com.example.combinerabbit.config.mysql.TargetSource;
import com.example.combinerabbit.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShiroMapper {
//    @Select("select * from user limit 1")
    @TargetSource("primary")
    User findByUserName(@Param("username") String username);
//    @Select("select count(*) from user")
    int countId();
}
