package com.cqttx.blog.domain.mapper;

import com.cqttx.blog.domain.entities.User;
import org.apache.ibatis.annotations.*;

/**
 * @author stc
 * @see Mybatis提供查询数据
 */
@Mapper
public interface UserMapper {

	// 根据ID查询User
	@Select("select * from User where id = #{id}")
	public User findUserById(Integer id);

	// 根据用户名查询用户
	@Select("select * from User where userName = #{userName}")
	public User findUserByUserName(String userName);

	// 根据用户和密码查询用户
	@Select("select * from User where userName = #{0} and password = #{1}")
	public User findUserByUserNameAndPassword(String userName, String password);

	
	
	
	// 根据用户和密码查询，返回ID
	@Results({ @Result(property = "id", column = "id") })
	@Select("select id from User where name = #{0} and password = #{1}")
	Integer findByNameAndPassword(String name, String password);

	User findById(@Param("id") Integer id);
}
