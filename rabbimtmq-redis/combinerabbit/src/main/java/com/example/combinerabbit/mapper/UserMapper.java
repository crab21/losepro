package com.example.combinerabbit.mapper;

import com.example.combinerabbit.config.mysql.TargetSource;
import com.example.combinerabbit.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
@Transactional
public interface UserMapper {
    @TargetSource("primary")
    @Select("select * from rabb")
    List<Users> findAllUser();

    @Insert("insert into rabb values(#{i}, #{name})")
    void insert(@Param("i") int i, @Param("name") String name1);

    @Insert("insert into rabb_second values(#{i}, #{name})")
    void inserts(@Param("i") int i, @Param("name") String name1, boolean b);

/*
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "rid", property = "roles",
                    many = @Many(select = "com.example.combinerabbit.mapper.selectRoles"))
    })
    @Select("select u.*,r.*,m.* from user u inner join user_role ur on ur.id=uid" +
            "inner join role r on r.rid=ur.rid" +
            "inner join module_role mr on mr.rid=r.rid" +
            "inner join module m on mr.mid=m.mid" +
            "where username=#{username}")
    User findUser(@Param("username") String username);


    @Select("select * from role")
    List<Role> selectRoles();*/

}
