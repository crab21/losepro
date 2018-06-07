package com.example.combinerabbit.mapper;

import com.example.combinerabbit.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShiroMapper {

    User findByUserName(@Param("username") String username);


}
