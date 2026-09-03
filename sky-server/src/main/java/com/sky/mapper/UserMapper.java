package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User findByOpenid(String openid);

    /**
     * 插入用户
     * @param user
     */
    void insert(User user);

    /**
     * 根据时间范围查询用户总数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
