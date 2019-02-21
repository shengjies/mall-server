package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 添加
     * @param entity
     * @return
     */
    int add(UserEntity entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    int edit(UserEntity entity);

    /**
     * 分页统计总数
     * @param username
     * @return
     */
    Long findCount(@Param("username")String username);

    /**
     * 分页查询
     * @param username
     * @param page
     * @param size
     * @return
     */
    List<UserEntity> findList(@Param("username")String username,@Param("page")int page,@Param("size")int size);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserEntity findLogin(@Param("username")String username,@Param("password")String password);

    /**
     * 配置FB
     * @return
     */
    int addUserFb(@Param("user_id")int user_id,@Param("fb_id")int fb_id);

    /**
     * 删除用户FB
     * @param user_id
     * @return
     */
    int delUserFb(@Param("user_id")int user_id);
    /**
     * 配置域名
     * @param user_id
     * @param domain_id
     * @return
     */
    int addUserDomain(@Param("user_id")int user_id,@Param("domain_id")int domain_id);

    /**
     * 删除用户域名
     * @param user_id
     * @return
     */
    int delUserDomain(@Param("user_id")int user_id);
}
