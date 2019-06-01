package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    Long findCount(@Param("username")String username,@Param("role_code")String role_code);

    /**
     * 分页查询
     * @param username
     * @param page
     * @param size
     * @return
     */
    List<UserEntity> findList(@Param("username")String username,@Param("role_code")String role_code
            ,@Param("page")int page,@Param("size")int size);

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

    /**
     * 查询所以业务员
     * @return
     */
    @Select("SELECT id,username FROM mall.tab_user where role_code='role_user'")
    List<UserEntity> findAllUser();

    /**
     * 查询组长对应分配的组员编号
     * @param id
     * @return
     */
    @Select("SELECT id FROM mall.tab_user where is_group=#{id};")
    List<String> groupId(@Param("id")int id);

    /**
     * 清空对应的组长的组员分配
     * @param group_id
     * @return
     */
    @Update("update tab_user set is_group = 0 where is_group = #{group_id}")
    int editGroup(@Param("group_id")int group_id);

    /**
     * 分配组员
     * @param id
     * @return
     */
    @Update("update tab_user set is_group = #{group_id} where id = #{id};")
    int editGroupById(@Param("id")int id,@Param("group_id")int group_id);

    /**
     * 管理员查询所以用户用于下拉列表
     * @return
     */
    @Select("SELECT id,username FROM mall.tab_user order by id desc;")
    List<UserEntity> findAll();

    /**
     * 组长查询所以组员用于下拉列表
     * @param group_id
     * @return
     */
    @Select("SELECT id,username FROM mall.tab_user where is_group = #{group_id} order by id desc;")
    List<UserEntity> findAllByGroupId(@Param("group_id")int group_id);
}
