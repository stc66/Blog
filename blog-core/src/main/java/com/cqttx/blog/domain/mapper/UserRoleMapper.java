package com.cqttx.blog.domain.mapper;

import com.cqttx.blog.domain.entities.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author stc
 * @see Mybatis提供查询数据
 */
@Mapper
public interface UserRoleMapper {

	// 根据用户ID查询所有的用户角色
	@Select("select * from UserRole where userId = #{userId}")
	public List<UserRole> findUserRoleByUserId(Integer userId);
}
