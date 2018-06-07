package com.learn.mapperMaster;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by k on 2018/6/7.
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select count(*) from user")
    int selectUserInfo();
}
