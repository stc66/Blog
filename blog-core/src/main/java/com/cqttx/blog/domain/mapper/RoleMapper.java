package com.cqttx.blog.domain.mapper;

import com.cqttx.blog.domain.entities.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author stc
 * @see Mybatis提供查询数据
 */
@Mapper
public interface RoleMapper {

	// 根据ID查询Role
	@Select("select * from Role where id = #{id}")
	public Role findRoleById(Integer id);
}
